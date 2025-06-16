# Spring Security with JWT Authentication

This project is a demonstration of implementing **Spring Security** using **JWT (JSON Web Tokens)** for stateless authentication in a Spring Boot application. It showcases best practices in designing secure login, registration, and authorization flows using Spring Boot 3 and Spring Security 6.

## 🔐 Features

- User registration with email and password
- Login to receive JWT access and refresh tokens
- Secure endpoints with role-based access (e.g., `USER`, `ADMIN`)
- Refresh token endpoint to renew access tokens
- Logout functionality that invalidates tokens (via token revocation)
- Global exception handling with custom error responses
- Clean architecture with service and repository layers
- PostgreSQL database integration via Spring Data JPA

## 📂 Project Structure

```
src
├── config              # JWT and security configuration
├── controller          # REST endpoints
├── model               # Entity and DTO classes
├── repository          # Spring Data repositories
├── service             # Business logic and token services
├── exception           # Custom exceptions and handlers

````

## 🛠️ Tech Stack

- **Spring Boot 3**
- **Spring Security 6**
- **JWT (io.jsonwebtoken)**
- **Spring Data JPA**
- **MYSQL**
- **Lombok**
- **Maven**

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- MYSQL

### Clone and Build

```bash
git clone https://github.com/Devansh-ds/Spring-Security.git
cd Spring-Security/jwtComplete or cd Spring-security/twoFactorAuth
mvn clean install
````

### Configure MySQL

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run the Application

```bash
mvn spring-boot:run
```

## 📫 API Endpoints

| Endpoint                  | Method | Description                | Auth Required |
| ------------------------- | ------ | -------------------------- | ------------- |
| `/auth/register`      | POST   | Register new user          | ❌                |
| `/auth/authenticate`  | POST   | Login and get tokens       | ❌                |
| `/auth/refresh-token` | POST   | Get new access token       | ✅ (Refresh)      |
| `/auth/logout`        | POST   | Logout and revoke token    | ✅                |
| `/auth/verify-otp`    | POST   | to send otp                | ❌                |

## 🔐 Security Highlights

* Stateless JWT-based authentication
* Role-based authorization
* Revoked token management using database
* Custom filters integrated with Spring Security filter chain

## 🧪 Testing

Use tools like **Postman** or **Insomnia** to test the authentication flow:

1. Register a user.
2. Authenticate to receive JWT tokens.
3. Use the access token in the `Authorization` header:
   `Authorization: Bearer <access_token>`
4. Call protected endpoints.
5. Refresh token when access token expires.
