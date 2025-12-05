# ==========================================================
# STAGE 1: BUILDER (Compila el codigo Java y genera el JAR)
# CORRECCIÓN 1: Imagen de Maven válida (usando Temurin JDK)
# ==========================================================
FROM eclipse-temurin:17-jdk-alpine AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos del proyecto (pom.xml y codigo fuente)
COPY pom.xml .
COPY src /app/src

# Ejecuta la compilacion de Maven. Esto genera el JAR en /app/target/
RUN mvn clean package -DskipTests

# ==========================================================
# STAGE 2: RUNNER (Crea la imagen final, mas pequena)
# CORRECCIÓN 2: Imagen JRE válida (usando Temurin JRE)
# ==========================================================
FROM eclipse-temurin:17-jre-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR SOLAMENTE desde la etapa 'builder' a la imagen final
COPY --from=builder /app/target/*.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Define el punto de entrada para ejecutar el JAR con el perfil 'render' activo
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=render", "/app.jar"]