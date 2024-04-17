# Etapa de construcción
FROM maven:latest AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Etapa de ejecución
FROM eclipse-temurin:latest
WORKDIR /app
COPY --from=build /app/target/web-jpa-0.0.1-SNAPSHOT.jar java-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "java-app.jar"]