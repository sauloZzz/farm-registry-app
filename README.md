# 🏡 FincasDpts – Agricultural Property Management System

FincasDpts is a web-based management system designed for registering, editing, searching, and organizing agricultural properties (fincas) across different departments and municipalities.  
The application is built using **Spring Boot, Thymeleaf, JPA/Hibernate, and MySQL** following a clean MVC architecture.

---

## 🚀 Features

### ✅ Property (Finca) Management
- Create new properties  
- Edit existing properties  
- Delete properties  
- View detailed information  
- Validate data before saving  

### 🌎 Geographic Structure
- Each property is associated with:
  - A **Department**
  - A **Municipality** (filtered by department)

### 💾 Database Integration
- Fully connected to **MySQL**
- Uses **JPA relationships**:
  - `Departamento` → `Municipio` (One-to-Many)
  - `Municipio` → `Finca` (One-to-Many)
- Automatic schema handling with Hibernate

### 🎨 Modern UI
- Thymeleaf templates
- Bootstrap styles (optional)
- Clean interface for CRUD operations

---

## 🧱 Technologies Used

| Layer | Technology |
|-------|------------|
| Backend | Spring Boot 3, Java 17 |
| Frontend | Thymeleaf, HTML5, Bootstrap |
| Database | MySQL 8, JPA/Hibernate |
| Build tool | Maven |
| Version control | Git & GitHub |

---

## 📦 Project Structure

src/
├── main/java/edu/unisangil/fincasdpts/
│ ├── controller/ # Controllers for handling HTTP requests
│ ├── entity/ # JPA entities (Finca, Municipio, Departamento)
│ ├── repository/ # Spring Data repositories
│ ├── service/ # Optional service layer
│ └── FinacsDptsApp # Main Spring Boot class
└── main/resources/
├── templates/ # Thymeleaf HTML files
├── static/ # CSS / JS / images
└── application.properties


---

## ⚙️ Installation & Setup

### 1️⃣ Clone the repository
```bash
git clone https://github.com/sauloZzz/fincasdpts.git
cd fincasdpts


2️⃣ Configure the database

Create a MySQL database:
CREATE DATABASE fincas_dpts CHARACTER SET utf8mb4;

Update application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/fincas_dpts
spring.datasource.username=your_user
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3️⃣ Run the application
mvn spring-boot:run


Then open in your browser:

http://localhost:8080/fincas

🧪 Future Improvements

Add pagination and search filters

Implement user authentication

Add Excel/PDF export for reports

Improve responsive UI design

🧑‍💻 Author

Saul Perez
Student – Universisty of Cordoba
GitHub: https://github.com/sauloZzz

📄 License

This project is released under the MIT License.
