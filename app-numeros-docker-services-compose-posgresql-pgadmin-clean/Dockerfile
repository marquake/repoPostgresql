# Etapa de construcción
#   - Se limpia proyecto y se genera el SNAPSHOT
FROM maven:latest AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Etapa de ejecución
#   - Se mueve SNAPSHOT dentro del contenedor
FROM eclipse-temurin:latest
WORKDIR /app
COPY --from=build /app/target/web-jpa-0.0.1-SNAPSHOT.jar java-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "java-app.jar"]
