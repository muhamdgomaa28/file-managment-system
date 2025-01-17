# Use a Java base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR
COPY target/file-management-0.0.1-SNAPSHOT.jar file-management-system.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "file-management-system.jar"]
