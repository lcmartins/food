FROM openjdk:23-jdk

WORKDIR /app

COPY target/*.jar /app/food.jar

EXPOSE 9090

CMD ["java", "-jar", "food.jar"]