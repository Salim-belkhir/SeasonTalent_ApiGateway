#Compilation of the jar to have the last version of the code
FROM maven:3.8.7-eclipse-temurin-19 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

#Creation of the image with the jar
FROM openjdk:19-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/ApiGateway-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]