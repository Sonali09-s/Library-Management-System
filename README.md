Library Management System (Backend)
A robust Spring Boot 3.x RESTful API designed to manage library operations, featuring secure user authentication and advanced reporting.

Key Features
JWT Security: Full authentication and authorization flow using io.jsonwebtoken.

Book Management: CRUD operations for books, categories, and authors.

Issuance Tracking: Track book issuance with detailed IssueBookDetails.

Analytics: Percentage-based reporting for book categories and availability.

User Roles: Support for Guest and Normal user permissions.

Tech Stack
Java 17

Spring Boot 3.3.4

Spring Security (Stateless JWT)

Hibernate / Spring Data JPA

MySQL (Database)

Maven (Dependency Management)

Setup & Installation
1. Prerequisites
JDK 17 or higher

MySQL Server

Maven

2. Database Configuration
Create a database named library_db and update your src/main/resources/application.properties:
spring.datasource.url=jdbc:mysql://localhost:33067/user_mgmt
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

3. API Keys (Security Note)
Currently not in the file

4. Running the App
mvn clean install
mvn spring-boot:run

Method	   Endpoint	      Description	             Auth Required
POST	  /api/auth/login	  Authenticate & get JWT	   No
GET	    /api/books	      Get all books	             No
POST	 /api/issue	        Issue book to user	       Yes (Admin/Staff)

Why I Built This
I developed this project to master Spring Boot 3 migration challenges and implement a secure, stateless authentication architecture. It handles complex entity mappings (DTOs) and provides clean analytics for library inventory.
