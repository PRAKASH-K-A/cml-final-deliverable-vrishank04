## Group 3 - Member M5 Contribution
**Name:** Vrishank V Umrani - 230958008
**Role:** G3-M5 (Reference Data + DB Init Service)

For my contribution to the Trading Platform, I implemented the Reference Data services, the underlying Database Initialization loader, and the lookup APIs required for Order validation and UI filters. 

### 🚀 Key Requirements Delivered:
1. **DB-Init Loader:** Implemented automated database seeding using `@PostConstruct` to safely populate the `Security Master` and `Customer Master` tables if they are empty upon application startup.
2. **Cached Lookup APIs:** Created RESTful controller endpoints to expose the Security Master and Customer master lists to the frontend and other microservices.
3. **Reference Data Models:** Designed the JPA Data Entities and schema configurations for both `Customer` and `Security`.

### 📂 Files to Evaluate for Grading:
Please review my core logic in the following files:

*   **Service & DB Init:** `backend/src/main/java/com/trading/platform/service/ReferenceDataService.java`
*   **REST API Controller:** `backend/src/main/java/com/trading/platform/controller/ReferenceDataController.java`
*   **Data Models (Entities):** 
    *   `backend/src/main/java/com/trading/platform/entity/Security.java`
    *   `backend/src/main/java/com/trading/platform/entity/Customer.java`
*   **Data Transfer Objects (DTO):**       `backend/src/main/java/com/trading/platform/dto/SecurityDTO.java`
*   **Repositories:** 
    *   `backend/src/main/java/com/trading/platform/repository/SecurityRepository.java`
    *   `backend/src/main/java/com/trading/platform/repository/CustomerRepository.java`

### 💻 How to Verify My Work:
1. Start the Spring Boot backend. 
2. In the console logs, you will immediately see the DB-loader executing successfully:
   * `"Security master seeded."`
   * `"Customer master seeded."`
3. You can test the cached lookup APIs by sending a GET request to:
   * `http://localhost:8080/api/reference/securities`
   * `http://localhost:8080/api/reference/customers`
