# 📝 Journal App (Spring Boot + MongoDB)

A simple RESTful API for managing personal journal entries. Built using **Spring Boot**, integrated with **MongoDB**, and tested using **Postman**.

---

## 🚀 Features

- ✅ Create journal entries
- 📖 View all or a single entry
- ✏️ Update journal entries
- ❌ Delete entries
- ⏰ Stores entry timestamps using `LocalDateTime`

---

## 🛠 Tech Stack

- Java 
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Postman
- IntelliJ IDEA

---

## 📂 API Endpoints

| Method | Endpoint                   | Description                     |
|--------|----------------------------|---------------------------------|
| `GET`  | `/journal`                 | Get all journal entries         |
| `GET`  | `/journal/id/{id}`         | Get journal entry by ID         |
| `POST` | `/journal`                 | Create a new journal entry      |
| `PUT`  | `/journal/id/{id}`         | Update an existing entry        |
| `DELETE` | `/journal/id/{id}`       | Delete journal entry by ID      |

---

## 💡 How to Run Locally

1. Clone the repo:
   ```bash
   git clone https://github.com/tripathiShivendra/journal-App.git
2. Open the project in IntelliJ.
3. MongoDB should be running locally (`mongodb://localhost:27017`).
4. Run the application using IntelliJ or mvn spring-boot:run
5. Use Postman to interact with endpoints.

## Notes

- Further development is in progress such as authentication using spring security and exception handling etc.

