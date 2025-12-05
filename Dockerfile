# ==========================================================
# STAGE 1: BUILDER (Compila el codigo Java y genera el JAR)
# Usamos una imagen oficial que incluye Maven 3.9 y Java 17 JDK
# ==========================================================
FROM maven:3.9-eclipse-temurin-17 AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos del proyecto (pom.xml y codigo fuente)
COPY pom.xml .
COPY src /app/src

# Ejecuta la compilacion de Maven. Esto genera el JAR en /app/target/
RUN mvn clean package -DskipTests

# ==========================================================
# STAGE 2: RUNNER (Crea la imagen final, mas pequena y segura)
# Usamos la version mas ligera de Java 17 JRE (solo para ejecutar)
# ==========================================================
FROM eclipse-temurin:17-jre-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR SOLAMENTE desde la etapa 'builder' a la imagen final
COPY --from=builder /app/target/*.jar app.jar

# Expone el puerto 8080 (puerto por defecto de Spring Boot)
EXPOSE 8080

# Define el punto de entrada para ejecutar el JAR con el perfil 'render' activo
# Esto usa las variables de entorno de la BD configuradas en Render.
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=render", "/app.jar"]