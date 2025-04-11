# 🛒 Amazon Clone (In Progress)

This is a **basic Amazon clone project** built using **Java Spring Boot**, focusing on the backend structure and authentication flow.  
The goal is to learn and implement core features of a typical e-commerce application while exploring Spring Boot, Spring Security, JWT, MySQL, and more.

---

## 🚀 Tech Stack

- **Backend:** Java, Spring Boot
- **Authentication:** Spring Security, JWT (JSON Web Token)
- **Database:** MySQL (for product and user data)
- **Frontend:** Basic HTML/CSS + JavaScript (for now)
- **ORM:** Hibernate / Spring Data JPA

---

## ✅ Features Implemented So Far
 
 ✅ Spring Security & JWT
 User registration & login using DTOs
Clean separation of request/response payloads from entity logic.

 JWT token-based authentication
Generates JWT on login and uses it to authenticate protected routes.

 Secure protected endpoints
Used Spring Security to restrict access to certain endpoints based on user roles.

 Address stored as an embedded field in the User entity
Structured the address within the user table using JPA @Embedded.

 Basic validation for forms
Used annotations like @NotBlank, @Email, etc. for clean data validation on the backend.

---

## 🔄 Upcoming Features

- [ ] Product management APIs (CRUD)  
- [ ] Cart functionality  
- [ ] Order placement 
- [ ] Admin panel for product control  
- [ ] Frontend integration  
- [ ] Image storage (MySQL + MongoDB hybrid setup)  
- [ ] Payment gateway integration (maybe later)

---

## 📚 What I’m Learning Through This Project

- Real-world Spring Boot project structure  
- Stateless authentication using JWT  
- Spring Security configuration  
- Entity relationships and embedded objects  
- Basic e-commerce flow & modular code organization  
- Agile-style development (breaking into sprints)

---

## 📂 Folder Structure (Planned)

