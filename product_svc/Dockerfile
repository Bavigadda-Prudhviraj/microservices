# Use Maven base image with JDK
FROM maven:3.9.4-eclipse-temurin-21-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your local machine to the container
COPY target/product_svc-0.0.1-SNAPSHOT.jar /app/product_svc-0.0.1-SNAPSHOT.jar

EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "product_svc-0.0.1-SNAPSHOT.jar"]
