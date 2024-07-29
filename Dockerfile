# Dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/stockExchangeApp-0.0.1-SNAPSHOT.jar stock-exchange-app.jar
ENTRYPOINT ["java", "-jar", "/stock-exchange-app.jar"]
