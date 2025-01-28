FROM --platform=$TARGETPLATFORM amazoncorretto:17-alpine-jdk
ADD target/cat-core.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=ci"]

# Expose the default port
EXPOSE 3001