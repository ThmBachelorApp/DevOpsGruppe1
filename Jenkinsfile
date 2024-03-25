pipeline {
    agent any

    tools {
        maven 'DevOpsProject'
    }

    environment {
        DOCKER_IMAGE = 'DevOpsTool/smartshop:latest'
        DOCKERHUB_CREDENTIALS = credentials('DockerHub')
        // SonarQube Server URL
        SONARQUBE_SERVER = 'http://localhost:9000'
        // Der Name des SonarQube-Scanners, der in Jenkins konfiguriert ist
        SONARQUBE_SCANNER = 'DevOpsSQ'
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

        stage('Build') {
            steps {
                script {
                    // Nutzt Maven zum Bauen des Projekts
                   sh 'mvn clean package'
                }
            }
        }
        
     stage('Scan') {
    steps {
        withSonarQubeEnv('DevOpsSQ') {
            script {
                // Verwendet die von Jenkins gesetzten Umgebungsvariablen
               sh 'mvn sonar:sonar'
            }
        }
    }
}


    
        stage('Test') {
            steps {
                script {
                    // Nutzt Maven zum Ausführen der Tests
                   sh 'mvn test'
                }
            }
        }

        stage('Docker Build and Push') {
            steps {
                script {
                        // Login bei der Docker-Registry
                        'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                        // Bauen und taggen des Docker-Images
                         "docker build -t ${env.DOCKER_IMAGE} -f Dockerfile ."
                        // Pushen des Docker-Images zur Registry
                         "docker push ${env.DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                    // Stoppt den laufenden Container (falls vorhanden)
                     "docker stop blissful_hawking || true"
                     "docker rm blissful_hawking || true"
                    // Startet einen neuen Container aus dem gebauten Image
                     "docker run -d --name blissful_hawking -p 8081:8080 ${env.DOCKER_IMAGE}"
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
