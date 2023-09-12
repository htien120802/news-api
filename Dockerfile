# Fetching latest version of Java
FROM openjdk:17

# Setting up work directory
WORKDIR /app

RUN mvn spring-boot:build-info -Dspring.profiles.active=prod

# Copy the jar file into our app
COPY ./target/newspaper-0.0.1-SNAPSHOT.jar /app/app.jar

# Exposing port 8080
EXPOSE 8080

# Starting the application
CMD ["java", "-jar", "/app/app.jar"]