# ==========================================================
# STAGE 1: BUILDER (Compila el codigo Java y genera el JAR)
# Usamos una imagen que tiene Maven y Java 17
# ==========================================================
FROM maven:3.9.6-openjdk-17 AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos del proyecto (pom.xml y codigo fuente)
COPY pom.xml .
COPY src /app/src

# Ejecuta la compilacion de Maven. Esto genera el JAR en /app/target/
# No necesitamos --build-arg JAR_FILE ya que lo compilamos aqui.
RUN mvn clean package -DskipTests

# ==========================================================
# STAGE 2: RUNNER (Crea la imagen final, mas pequena)
# Usamos solo el Java Runtime Environment (JRE) para la ejecucion
# ==========================================================
FROM openjdk:17-jre-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR SOLAMENTE desde la etapa 'builder' a la imagen final
# Usamos un patron comodin (wildcard) para encontrar el JAR.
COPY --from=builder /app/target/*.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Define el punto de entrada para ejecutar el JAR con el perfil 'render' activo
# Esto usa las variables de entorno de la BD que acabamos de configurar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=render", "/app.jar"]