#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn spring-boot:build-info -Dspring.profiles.active=prod

#
# Package stage
#
FROM openjdk:17
COPY --from=build /target/newspaper-0.0.1-SNAPSHOT.jar news-api.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","news-api.jar"]