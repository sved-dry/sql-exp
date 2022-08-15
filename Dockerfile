FROM openjdk:17-alpine
VOLUME /app
ADD target/sql-exp-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]