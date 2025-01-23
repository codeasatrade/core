FROM openjdk:17-jdk-alpine
ADD target/cat-core.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]