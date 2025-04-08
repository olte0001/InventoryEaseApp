# Sources: https://www.docker.com/blog/kickstart-your-spring-boot-application-development/

FROM openjdk:21-jdk-slim as builder

WORKDIR /InventoryEase

COPY build/libs/InventoryEase-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]