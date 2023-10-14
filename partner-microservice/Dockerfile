# Stage 1: Build the application
FROM maven:3.8.4-jdk-11 as builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Create the final runtime image
FROM openjdk:11-jre-slim
WORKDIR /app/naming-server
COPY --from=builder /app/target/partner-microservice-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8100
CMD ["java", "-jar", "app.jar"]