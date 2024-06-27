FROM openjdk:11-jdk-slim
WORKDIR /app
# CMD ["./mvnw", "spring-boot:run"]
COPY target/pa.com.pagayaa.security.app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]