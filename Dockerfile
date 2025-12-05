# Usa una imagen base de Java optimizada
FROM eclipse-temurin:21-jdk-jammy AS build

# Argumento para especificar el nombre del archivo JAR construido por Maven/Gradle
ARG JAR_FILE=target/*.jar

# Copia el JAR del proyecto a la imagen
COPY ${JAR_FILE} app.jar

# Define el punto de entrada para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=render", "/app.jar"]

