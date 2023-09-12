FROM openjdk:17-alpine
COPY target/newspaper-0.0.1-SNAPSHOT.jar news-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","news-api.jar"]