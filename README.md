# 💳 Core Payment Integration System  
*A Microservices-based E-commerce Payment Gateway*

## 🧩 Project Overview

The **Core Payment Integration System** is a microservices-based backend solution designed to handle secure, scalable, and efficient payment processing for e-commerce platforms. It emphasizes data validation, transaction lifecycle management, security, and performance.

---

## 🚀 Key Features

- ⚙️ **Microservices Architecture**: Modular services for validation and payment processing.
- ✅ **Payment Validation Rules**:
  - Duplicate payment checks
  - Daily limit enforcement
  - Attempt threshold validations
- 🔐 **Secure Transactions**:
  - HmacSHA256 hashing for request integrity
  - Spring Security for authentication and authorization
-  **REST APIs** for initiating and managing payments
-  **Caching Layer**: Redis integration for real-time validation parameter caching
-  **Database Operations** using Spring JDBC with MySQL
-  **Custom Error Handling** using Spring’s `@ControllerAdvice` and exception mechanisms
-  **Logging & Monitoring** with SLF4J and Logback

---

##  Technologies Used

| Technology       | Purpose                         |
|------------------|----------------------------------|
| Java             | Core application logic           |
| Spring Boot      | Backend framework                |
| Spring Security  | Authentication & Authorization   |
| Spring JDBC      | Database interaction             |
| MySQL            | Relational database              |
| Redis            | Caching layer                    |
| SLF4J + Logback  | Logging and observability        |
| REST API         | Service communication            |
| HmacSHA256       | Secure hashing mechanism         |

---

##  Microservices Overview

### 1. `payment-validation-service`
- Handles all field-level and business rule validations.
- Checks for duplicates, daily limits, and max retry attempts.
- Caches validation rules using Redis for quick access.

### 2. `payment-processing-service`
- Processes validated payments securely.
- Manages payment statuses (Initiated, Validated, Success, Failed).
- Integrates with MySQL and supports secure transaction logic.

---

## 📂 Project Structure
1. payment-validation-service
2. payment-processing-service
3. paypal-provider-service
4. stripe-provider-service
5. trustly-provider-service

