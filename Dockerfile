# Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim as build

# Install maven and git
RUN apt-get update && apt-get install -y maven git

# Set the working directory in Docker
WORKDIR /app

# Copy maven executable to the image
COPY pom.xml .

# Copy the source code
COPY src src

# Package the application
RUN mvn clean package -DskipTests

# Start with a clean image for the runtime
FROM openjdk:11-jre-slim

# Install git in the runtime image
RUN apt-get update && apt-get install -y git

# Set the working directory in Docker
WORKDIR /app

# Copy the built artifact from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file 
ENTRYPOINT ["java","-jar","app.jar"]
