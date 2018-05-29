FROM openjdk:8-jdk-alpine
COPY target/urlshortner-1.0-SNAPSHOT.jar /tmp/docker/
COPY config.yml /tmp/docker/
WORKDIR /tmp/docker/

CMD ["java", "-jar","urlshortner-1.0-SNAPSHOT.jar","server","config.yml"]

EXPOSE 8080
EXPOSE 8081