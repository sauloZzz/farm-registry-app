<div align="center">

# 🏡 FincasDpts
### Sistema de Gestión de Propiedades Agropecuarias

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)
[![Render](https://img.shields.io/badge/Render-Deployed-purple.svg)](https://render.com/)

<p align="center">
  <a href="#-sobre-el-proyecto">Sobre el Proyecto</a> •
  <a href="#-funcionalidades">Funcionalidades</a> •
  <a href="#-tecnologías">Tecnologías</a> •
  <a href="#-desafíos-y-soluciones-en-el-despliegue">Desafíos de Despliegue</a> •
  <a href="#-instalación-y-ejecución">Instalación</a>
</p>

</div>

---

## 📖 Sobre el Proyecto

**FincasDpts** es un sistema web robusto diseñado para la gestión administrativa de propiedades agrícolas (fincas). La aplicación permite organizar, registrar y editar información sobre fincas, vinculándolas geográficamente a Departamentos y Municipios mediante una base de datos relacional.

El proyecto sigue una arquitectura **MVC (Modelo-Vista-Controlador)** limpia, utiliza contenedores **Docker** para el entorno de desarrollo y está desplegado en la nube a través de **Render**.

### 🔗 Demo en Vivo
> **Visita el proyecto desplegado aquí:**
> 🚀 **[https://farm-registry-app.onrender.com](https://farm-registry-app.onrender.com)**
> *(Nota: Al estar en el plan gratuito de Render, el servicio puede tardar unos 50 segundos en "despertar" si ha estado inactivo. Por favor, ten paciencia en la primera carga).*

---

## 🚀 Funcionalidades

### ✅ Gestión de Fincas (CRUD)
* **Registro Completo:** Creación de nuevas propiedades con datos del propietario, contacto y dirección.
* **Edición Dinámica:** Actualización de registros existentes.
* **Persistencia de Datos:** Uso de JPA/Hibernate para transacciones seguras con la base de datos.

### 🌎 Inteligencia Geográfica
* **Estructura Jerárquica:** Modelo relacional estricto:
    * `Departamento` (1) ➡ (N) `Municipio`
    * `Municipio` (1) ➡ (N) `Finca`
* **Listas en Cascada:** Al seleccionar un departamento, los municipios se filtran automáticamente.

### 🛠 Aspectos Técnicos Destacados
* **Dockerización:** Entorno configurado con `Dockerfile` y `docker-compose` para replicar la base de datos localmente.
* **Carga Automática de Datos:** Script SQL (`data.sql`) que puebla la base de datos con departamentos y municipios iniciales.
* **Configuración Híbrida:** El sistema detecta automáticamente si está corriendo en `localhost` o en la nube (Render).

---

## ☁️ Desafíos y Soluciones en el Despliegue

Llevar esta aplicación a producción en **Render** presentó varios retos técnicos interesantes que fueron resueltos mediante configuración avanzada:

### 1. 🔐 Conexión a Base de Datos y SSL
* **El Desafío:** La aplicación fallaba al conectar con PostgreSQL en la nube, arrojando errores de `EOFException` y `Connection Refused` durante el *handshake*.
* **La Solución:** Se identificó que la infraestructura de Render exige conexiones encriptadas. Se configuró la URL JDBC inyectando el parámetro `?sslmode=require` y se utilizaron las credenciales internas de la red privada de Render.

### 2. 📉 Restricciones de Memoria (OOM Errors)
* **El Desafío:** El proceso de construcción (*Build*) fallaba consistentemente con `Exit Status 1` al descargar dependencias de Maven, debido a que la JVM excedía el límite de 512MB de RAM del plan gratuito.
* **La Solución:** Se optimizó el **Dockerfile** limitando el Heap Size de Maven. Se inyectó la variable de entorno `MAVEN_OPTS="-Xmx300m"` en el comando de construcción, asegurando que el proceso se mantuviera dentro de los límites del contenedor.

### 3. 🔄 Configuración de Entorno (Local vs. Nube)
* **El Desafío:** Tener credenciales "quemadas" (hardcoded) en `application.properties` hacía inseguro y difícil el cambio entre Docker local y la nube.
* **La Solución:** Implementación de **Inyección de Propiedades**. Se utilizó la sintaxis `${VARIABLE_ENTORNO:valor_por_defecto}` en Spring Boot, permitiendo que la app use variables seguras en la nube y valores por defecto (`localhost`) en desarrollo.

### 4. 🗃️ Persistencia y Carga de Datos (Seeding)
* **El Desafío:** Aunque la app desplegaba, las listas desplegables aparecían vacías. Hibernate ignoraba el script de carga inicial porque detectaba tablas ya existentes (pero vacías).
* **La Solución:**
    1. Estandarización del script a `data.sql` (nativo de Spring Boot).
    2. Configuración estratégica de `SPRING_JPA_HIBERNATE_DDL_AUTO=create-drop` en el primer despliegue exitoso para forzar una limpieza de esquema y reinserción de datos limpios.

---

## 💻 Stack Tecnológico

| Capa | Tecnología |
|-------|------------|
| **Backend** | Java 21, Spring Boot 3 (Web, Data JPA) |
| **Frontend** | Thymeleaf, HTML5, Bootstrap 5 |
| **Base de Datos** | PostgreSQL (Producción y Docker Local) |
| **DevOps** | Docker, Docker Compose, Maven |
| **Infraestructura** | Render (PaaS) |

---

## ⚡ Instalación y Ejecución

Puedes correr este proyecto en tu máquina local usando **Docker** (recomendado) o Java directamente.

### Prerrequisitos
* Java JDK 17 o 21
* Maven 3.8+
* Docker Desktop (Opcional)

### Opción 1: Ejecución con Docker (Recomendada)
Este método levanta la base de datos PostgreSQL automáticamente.

```bash
# 1. Clonar el repositorio
git clone [https://github.com/sauloZzz/farm-registry-app.git](https://github.com/sauloZzz/farm-registry-app.git)
cd farm-registry-app

# 2. Levantar la base de datos
docker-compose up -d

# 3. Ejecutar la aplicación (Usando el Wrapper de Maven)
# En Windows (CMD):
mvnw spring-boot:run
# En Mac/Linux/PowerShell:
./mvnw spring-boot:run

📂 Estructura del Proyecto
Plaintext

src/main/
├── java/edu/unisangil/fincasdpts/
│   ├── controller/      # Controladores Web (HTTP Requests)
│   ├── entity/          # Modelos de Datos (JPA Entities)
│   ├── repository/      # Interfaces de Base de Datos (Repositories)
│   └── FincasDptsApplication.java
└── resources/
    ├── templates/       # Vistas HTML (Thymeleaf)
    ├── static/          # Archivos CSS y JS
    ├── application.properties # Configuración dinámica
    └── data.sql         # Datos semilla (Departamentos/Municipios)

Tienes toda la razón. El problema ocurre porque GitHub genera los enlaces automáticamente basándose en el texto exacto del título, y a veces los emojis y las tildes rompen esos enlaces si no son idénticos.

Además, en el código anterior, el enlace del menú decía "Tecnologías" pero el título abajo decía "Stack Tecnológico", por eso no coincidían.

La Solución Definitiva (Enlaces Manuales)
Para que nunca fallen (sin importar emojis o tildes), he agregado etiquetas HTML invisibles (id="nombre") en cada título. Esto asegura que la navegación funcione perfecta.

Copia y pega este código corregido:

Markdown

<div align="center">

# 🏡 FincasDpts
### Sistema de Gestión de Propiedades Agropecuarias

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)
[![Render](https://img.shields.io/badge/Render-Deployed-purple.svg)](https://render.com/)

<p align="center">
  <a href="#sobre">Sobre el Proyecto</a> •
  <a href="#funcionalidades">Funcionalidades</a> •
  <a href="#stack">Tecnologías</a> •
  <a href="#desafios">Desafíos de Despliegue</a> •
  <a href="#instalacion">Instalación</a>
</p>

</div>

---

## <a id="sobre"></a>📖 Sobre el Proyecto

**FincasDpts** es un sistema web robusto diseñado para la gestión administrativa de propiedades agrícolas (fincas). La aplicación permite organizar, registrar y editar información sobre fincas, vinculándolas geográficamente a Departamentos y Municipios mediante una base de datos relacional.

El proyecto sigue una arquitectura **MVC (Modelo-Vista-Controlador)** limpia, utiliza contenedores **Docker** para el entorno de desarrollo y está desplegado en la nube a través de **Render**.

### 🔗 Demo en Vivo
> **Visita el proyecto desplegado aquí:**
> 🚀 **[https://farm-registry-app.onrender.com](https://farm-registry-app.onrender.com)**
> *(Nota: Al estar en el plan gratuito de Render, el servicio puede tardar unos 50 segundos en "despertar" si ha estado inactivo. Por favor, ten paciencia en la primera carga).*

---

## <a id="funcionalidades"></a>🚀 Funcionalidades

### ✅ Gestión de Fincas (CRUD)
* **Registro Completo:** Creación de nuevas propiedades con datos del propietario, contacto y dirección.
* **Edición Dinámica:** Actualización de registros existentes.
* **Persistencia de Datos:** Uso de JPA/Hibernate para transacciones seguras con la base de datos.

### 🌎 Inteligencia Geográfica
* **Estructura Jerárquica:** Modelo relacional estricto:
    * `Departamento` (1) ➡ (N) `Municipio`
    * `Municipio` (1) ➡ (N) `Finca`
* **Listas en Cascada:** Al seleccionar un departamento, los municipios se filtran automáticamente.

### 🛠 Aspectos Técnicos Destacados
* **Dockerización:** Entorno configurado con `Dockerfile` y `docker-compose` para replicar la base de datos localmente.
* **Carga Automática de Datos:** Script SQL (`data.sql`) que puebla la base de datos con departamentos y municipios iniciales.
* **Configuración Híbrida:** El sistema detecta automáticamente si está corriendo en `localhost` o en la nube (Render).

---

## <a id="stack"></a>💻 Stack Tecnológico

| Capa | Tecnología |
|-------|------------|
| **Backend** | Java 21, Spring Boot 3 (Web, Data JPA) |
| **Frontend** | Thymeleaf, HTML5, Bootstrap 5 |
| **Base de Datos** | PostgreSQL (Producción y Docker Local) |
| **DevOps** | Docker, Docker Compose, Maven |
| **Infraestructura** | Render (PaaS) |

---

## <a id="desafios"></a>☁️ Desafíos y Soluciones en el Despliegue

Llevar esta aplicación a producción en **Render** presentó varios retos técnicos interesantes que fueron resueltos mediante configuración avanzada:

### 1. 🔐 Conexión a Base de Datos y SSL
* **El Desafío:** La aplicación fallaba al conectar con PostgreSQL en la nube, arrojando errores de `EOFException` y `Connection Refused` durante el *handshake*.
* **La Solución:** Se identificó que la infraestructura de Render exige conexiones encriptadas. Se configuró la URL JDBC inyectando el parámetro `?sslmode=require` y se utilizaron las credenciales internas de la red privada de Render.

### 2. 📉 Restricciones de Memoria (OOM Errors)
* **El Desafío:** El proceso de construcción (*Build*) fallaba consistentemente con `Exit Status 1` al descargar dependencias de Maven, debido a que la JVM excedía el límite de 512MB de RAM del plan gratuito.
* **La Solución:** Se optimizó el **Dockerfile** limitando el Heap Size de Maven. Se inyectó la variable de entorno `MAVEN_OPTS="-Xmx300m"` en el comando de construcción, asegurando que el proceso se mantuviera dentro de los límites del contenedor.

### 3. 🔄 Configuración de Entorno (Local vs. Nube)
* **El Desafío:** Tener credenciales "quemadas" (hardcoded) en `application.properties` hacía inseguro y difícil el cambio entre Docker local y la nube.
* **La Solución:** Implementación de **Inyección de Propiedades**. Se utilizó la sintaxis `${VARIABLE_ENTORNO:valor_por_defecto}` en Spring Boot, permitiendo que la app use variables seguras en la nube y valores por defecto (`localhost`) en desarrollo.

### 4. 🗃️ Persistencia y Carga de Datos (Seeding)
* **El Desafío:** Aunque la app desplegaba, las listas desplegables aparecían vacías. Hibernate ignoraba el script de carga inicial porque detectaba tablas ya existentes (pero vacías).
* **La Solución:**
    1. Estandarización del script a `data.sql` (nativo de Spring Boot).
    2. Configuración estratégica de `SPRING_JPA_HIBERNATE_DDL_AUTO=create-drop` en el primer despliegue exitoso para forzar una limpieza de esquema y reinserción de datos limpios.

---

## <a id="instalacion"></a>⚡ Instalación y Ejecución

Puedes correr este proyecto en tu máquina local usando **Docker** (recomendado) o Java directamente.

### Prerrequisitos
* Java JDK 17 o 21
* Maven 3.8+
* Docker Desktop (Opcional)

### Opción 1: Ejecución con Docker (Recomendada)
Este método levanta la base de datos PostgreSQL automáticamente.

```bash
# 1. Clonar el repositorio
git clone [https://github.com/sauloZzz/farm-registry-app.git](https://github.com/sauloZzz/farm-registry-app.git)
cd farm-registry-app

# 2. Levantar la base de datos
docker-compose up -d

# 3. Ejecutar la aplicación (Usando el Wrapper de Maven)
# En Windows (CMD):
mvnw spring-boot:run
# En Mac/Linux/PowerShell:
./mvnw spring-boot:run
Opción 2: Ejecución Manual
Asegúrate de tener un servidor PostgreSQL corriendo en el puerto 5432.

Crea una base de datos llamada fincasdpts.

Configura tu usuario y contraseña en src/main/resources/application.properties.

Ejecuta el comando mvnw spring-boot:run.

Una vez iniciada, abre tu navegador en: http://localhost:8080/fincas

🧑‍💻 Autor
Saul Perez Estudiante – Universidad de Córdoba



