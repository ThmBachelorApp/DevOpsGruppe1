# Starte von der offiziellen Base Image von Java 11
FROM openjdk:11-jdk-slim as build

# Arbeitsverzeichnis im Container setzen
WORKDIR /app

# Kopiere die Maven Definition in das Arbeitsverzeichnis
COPY pom.xml .

# Kopiere den Quellcode
COPY main/src main/src

# Baue die Anwendung mit Maven
RUN mvn -B clean package -DskipTests

# Starte von einem neuen, sauberen Image
FROM openjdk:11-jre-slim

# Arbeitsverzeichnis im Container setzen
WORKDIR /app

# Kopiere das gebaute Artefakt aus dem Build-Image
COPY --from=build /app/target/smartshop-1.0-SNAPSHOT.jar /app/smartshop.jar

# Der Port, auf dem die Anwendung läuft (anpassen falls nötig)
EXPOSE 8080

# Starte die Spring Boot-Anwendung
CMD ["java", "-jar", "smartshop.jar"]
