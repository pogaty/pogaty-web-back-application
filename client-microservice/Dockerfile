# Stage 1: Build the application
FROM maven:3.8.4-jdk-11 as builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Create the final runtime image
FROM openjdk:11-jre-slim
WORKDIR /app/client-microservice
COPY --from=builder /app/target/client-microservice-1.0.0-SNAPSHOT.jar app.jar
COPY /Asset-image /app/client-microservice/Asset-image

EXPOSE 8000
CMD ["java", "-jar", "app.jar"]