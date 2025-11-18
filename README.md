# 🌾 Farm Registry App

A Spring Boot web application for managing rural farms and their geographic locations (departments and municipalities).  
This project demonstrates clean architecture using **Spring MVC**, **JPA/Hibernate**, **Thymeleaf**, and **MySQL**, following clean design practices and separation of concerns.

---

## 📌 Features

### ✅ Farm Management (CRUD)
- Register new farms with:
  - Owner name  
  - Phone number  
  - Address  
  - Department  
  - Municipality  
- List all registered farms  
- Delete farms with confirmation messages  

### ✅ Dynamic Geographic Loading
- Municipalities automatically load based on the selected department  
- AJAX-based dynamic loading using `fetch()`  
- No page reloads required  

### ✅ Validation & Error Handling
- Server-side validation  
- Success and error alerts  
- Foreign key validation for department and municipality  

### ✅ Database Included
- MySQL schema included: `farm_registry.sql`  
- Contains:
  - Colombian departments  
  - Municipalities for each department  

### ✅ User-Friendly UI
- Built with **Bootstrap 5**  
- Responsive layout for desktop and mobile  
- Clean visual design  

---

## 🧱 Architecture (Design Logic)

The project follows a traditional **Spring MVC layered architecture**:

Controller → Service → Repository → Entity → Database
Front-End (Thymeleaf + Bootstrap + JS)


### 🔹 Controller Layer
Handles routing, form submissions, and REST endpoints.

Example: dynamic municipality loading:

```java
@GetMapping("/municipios/{idDepartamento}")
@ResponseBody
public List<Municipio> obtenerMunicipiosPorDepartamento(@PathVariable Long idDepartamento) {
    return municipioRepository.findByDepartamento_Id(idDepartamento);
}


🔹 Service Layer

Contains business logic and validation.

🔹 Repository Layer

Uses Spring Data JPA to interact with the database.

🔹 View Layer

Uses Thymeleaf + Bootstrap for a clean and responsive UI.

🛠️ Technologies Used

Java 17

Spring Boot 3

Spring MVC

Spring Data JPA

Thymeleaf

MySQL 8


src/main/java/com/farmregistry
│
├── controller/      → MVC controllers
├── entity/          → JPA entities
├── repository/      → Spring Data JPA interfaces
├── service/         → Business logic
└── FarmRegistryApp  → Main application


src/main/resources
│
├── templates/       → Thymeleaf HTML views
├── static/          → CSS, JS
└── application.properties


🚀 How to Run the Project
1️⃣ Clone the repository
git clone https://github.com/sauloZzz/farm-registry-app.git
cd farm-registry-app

2️⃣ Create the MySQL database
CREATE DATABASE farm_registry;

3️⃣ Import the SQL file

Use phpMyAdmin, MySQL Workbench or terminal:

mysql -u root -p farm_registry < farm_registry.sql

4️⃣ Configure MySQL credentials

Edit the file:

src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/farm_registry
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

5️⃣ Run the app
mvn spring-boot:run

6️⃣ Open in browser
http://localhost:8080
Hibernate

Bootstrap 5

Fetch API (JavaScript)
