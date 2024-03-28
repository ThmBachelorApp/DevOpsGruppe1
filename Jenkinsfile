pipeline {
    agent any

    tools {
        maven 'DevOpsProject'
    }

    environment {
        DOCKER_IMAGE = 'smartshopdevops/smartshop:latest'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm: [
                    $class: 'GitSCM', 
                    branches: [[name: '*/main']],
                    extensions: [], 
                    userRemoteConfigs: [[
                        credentialsId: 'GitHubToken',
                        url: 'https://github.com/ThmBachelorApp/DevOpsGruppe1.git'
                    ]]
                ]
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    // Führt Maven aus, um das Projekt zu bauen und die Tests auszuführen, inklusive der Erstellung des JaCoCo-Berichts
                    bat 'mvn clean verify'
                }
            }
        }
        
        stage('Publish JUnit Test Results') {
            steps {
                // Veröffentlicht die JUnit-Testergebnisse in Jenkins
                junit '**/target/surefire-reports/*.xml'
            }
        }
        
      stage('SonarQube Scan') {
    steps {
        withCredentials([string(credentialsId: 'SonarLogin', variable: 'SONAR_TOKEN')]) {
            // Definiert den Kontext für SonarQube mit den benötigten Umgebungsvariablen
            withSonarQubeEnv('SQDevOps') {
                // Führt die SonarQube-Analyse aus, einschließlich des Uploads des JaCoCo-Testabdeckungsberichts
                bat "mvn sonar:sonar -Dsonar.projectKey=SmartShopSonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=${SONAR_TOKEN} -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
            }
        }
    }
}

        
       
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        
        stage('Docker Build and Push') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DockerHub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        // Login bei der Docker-Registry
                        bat "docker login --username=${DOCKER_USERNAME} --password=${DOCKER_PASSWORD}"
                        // Bauen und taggen des Docker-Images
                        bat "docker build -t ${env.DOCKER_IMAGE} -f Dockerfile ."
                        // Pushen des Docker-Images zur Registry
                        bat "docker push ${env.DOCKER_IMAGE}"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DockerHub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        bat "docker login --username=${DOCKER_USERNAME} --password=${DOCKER_PASSWORD}"
                        // Stoppt den laufenden Container (falls vorhanden)
                        bat "docker stop smartshop-container || exit 0"
                        bat "docker rm smartshop-container || exit 0"
                        // Startet einen neuen Container aus dem gebauten Image
                        bat "docker run -d --name smartshop-container -p 8081:8080 ${env.DOCKER_IMAGE}"
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution is complete.'
        }
    }
}
