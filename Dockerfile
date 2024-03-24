# Verwenden Sie das offizielle Jenkins-Image als Basis
FROM jenkins/jenkins:lts as jenkins-base

# Wechseln Sie zum Root-Benutzer, um Installationen durchzuführen
USER root

# Installieren Sie Maven und Git
RUN apt-get update && \
    apt-get install -y maven git

# Setzen Sie das Arbeitsverzeichnis
WORKDIR /app

# Kopieren Sie die Maven-Dateien
COPY pom.xml .
COPY src ./src

# Bauen Sie die Anwendung
RUN mvn clean package -DskipTests

# Fügen Sie weitere Schritte hinzu, die Sie benötigen, bevor Sie zu einem schlankeren Image wechseln

# Start with a clean image for the runtime
FROM openjdk:11-jre-slim

# Setzen Sie das Arbeitsverzeichnis
WORKDIR /app

# Kopieren Sie das gebaute Artefakt vom Jenkins-Build-Image
COPY --from=jenkins-base /app/target/*.jar /app/app.jar

# Legen Sie den Port fest, den Ihre Anwendung benutzen wird
EXPOSE 8080

# Definieren Sie den Entry Point für die Anwendung
ENTRYPOINT ["java","-jar","app.jar"]
