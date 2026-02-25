FROM eclipse-temurin:17-jdk

WORKDIR /app

# Make sure this matches the final JAR name
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]