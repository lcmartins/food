FROM maven:3.9.7-ibm-semeru-21-jammy AS build

WORKDIR /home/app

COPY src src

COPY pom.xml pom.xml

RUN mvn clean package -DskipTests

FROM openjdk:21-ea-23-jdk-bullseye

WORKDIR /app

COPY --from=build /home/app/target/*.jar app.jar

ENV PORT=8089

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]