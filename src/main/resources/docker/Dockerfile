# Dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/stock-exchange-app.jar stock-exchange-app.jar
ENTRYPOINT ["java", "-jar", "/stock-exchange-app.jar"]
