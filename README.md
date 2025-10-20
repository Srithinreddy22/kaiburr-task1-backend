# 🧩 Kaiburr Task Management REST API

This project is a **Java Spring Boot** backend application that provides a **REST API** to manage and execute shell-based tasks.  
Each task represents a shell command that can be executed, tracked, and stored along with its execution history in a **MongoDB** database.

---

## 🚀 Features

- Create, view, delete, and execute shell-based **Tasks**
- Each task has multiple **TaskExecutions** (command runs)
- **MongoDB** used for persistence
- Command validation to prevent unsafe/malicious inputs
- Exposes REST API endpoints tested via **Postman/cURL**

---

## 🧱 Technologies Used

| Component | Technology |
|------------|-------------|
| Language | Java 17+ |
| Framework | Spring Boot |
| Database | MongoDB |
| Build Tool | Maven |
| Testing | Postman / cURL |
| Version Control | Git & GitHub |

---

## 📁 Project Structure

```

src/
├── main/java/com/kaiburr/taskbackend/
│    ├── controller/        # REST Controllers
│    ├── model/             # Task and TaskExecution Models
│    ├── repository/        # Mongo Repositories
│    ├── service/           # Business Logic (optional)
│    └── TaskBackendApp.java # Main Application
├── main/resources/
│    ├── application.properties
└── test/java/...           # Unit Tests (if any)

````

---

## ⚙️ Configuration

Update the `application.properties` file with your MongoDB details:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/taskdb
server.port=8080
````

---

## 🧠 Data Model

### Task

```json
{
  "id": "123",
  "name": "Print Hello",
  "owner": "John Smith",
  "command": "echo Hello World again!",
  "taskExecutions": [
    {
      "startTime": "2023-04-21T15:51:42.276Z",
      "endTime": "2023-04-21T15:51:43.276Z",
      "output": "Hello World!"
    }
  ]
}
```

### TaskExecution

| Field       | Type   | Description               |
| ----------- | ------ | ------------------------- |
| `startTime` | Date   | Execution start timestamp |
| `endTime`   | Date   | Execution end timestamp   |
| `output`    | String | Output of the command     |

---

## 🧩 REST API Endpoints

### 1️⃣ **Get all tasks**

**`GET /tasks`**

* Returns all stored tasks.

📸 *Screenshot Placeholder:*
`![GET all tasks](https://github.com/Srithinreddy22/kaiburr-task1-backend/blob/main/Screenshot%202025-10-20%20222459.png)`

---

### 2️⃣ **Get task by ID**

**`GET /tasks/{id}`**

* Returns a single task or 404 if not found.

📸 *Screenshot Placeholder:*
`![GET task by ID](screenshots/get_task_by_id.png)`

---

### 3️⃣ **Create or update a task**

**`PUT /tasks`**

* Accepts JSON body representing a task.
* Validates the command to prevent unsafe shell operations.

📸 *Screenshot Placeholder:*
`![PUT task](screenshots/put_task.png)`

---

### 4️⃣ **Delete a task**

**`DELETE /tasks/{id}`**

* Deletes the task with the given ID.

📸 *Screenshot Placeholder:*
`![DELETE task](screenshots/delete_task.png)`

---

### 5️⃣ **Find task by name**

**`GET /tasks/find?name={query}`**

* Returns all tasks where the name contains the given string.
* Returns 404 if none found.

📸 *Screenshot Placeholder:*
`![Find task by name](screenshots/find_task_by_name.png)`

---

### 6️⃣ **Execute a task command**

**`PUT /tasks/{id}/execute`**

* Executes the shell command associated with the task.
* Saves a new TaskExecution object (with start/end time and output).

📸 *Screenshot Placeholder:*
`![Execute task](screenshots/execute_task.png)`

---

## 🧪 Example Request Body (for PUT /tasks)

```json
{
  "id": "123",
  "name": "Print Hello",
  "owner": "John Smith",
  "command": "echo Hello World!"
}
```

---

## 🧰 Run Locally

1. **Clone the repository**

   ```bash
   git clone https://github.com/<your-username>/<your-repo-name>.git
   cd <your-repo-name>
   ```

2. **Start MongoDB**

   ```bash
   mongod
   ```

3. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

4. **Test the endpoints**

   * Using **Postman**, **cURL**, or your browser at
     👉 `http://localhost:8080/tasks`

---

## 🧾 Example Commands (cURL)

```bash
# Get all tasks
curl -X GET http://localhost:8080/tasks

# Create a task
curl -X PUT http://localhost:8080/tasks \
-H "Content-Type: application/json" \
-d '{"id":"123","name":"Print Hello","owner":"John","command":"echo Hello"}'

# Execute task
curl -X PUT http://localhost:8080/tasks/123/execute

---

Would you like me to include your actual GitHub repo link and your name (so it appears properly in the **Author** and **clone** section)? I can personalize it and add a nice banner or badges (e.g., Java, Spring Boot, MongoDB) too.
```
