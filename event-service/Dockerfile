FROM openjdk:21-jdk-slim

COPY target/event-service-0.0.1-SNAPSHOT.jar event-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "event-service.jar"]
