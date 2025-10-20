#  Kaiburr Task Management REST API

This project is a **Java Spring Boot** backend application that provides a **REST API** to manage and execute shell-based tasks.  
Each task represents a shell command that can be executed, tracked, and stored along with its execution history in a **MongoDB** database.

---

##  Features

- Create, view, delete, and execute shell-based **Tasks**
- Each task has multiple **TaskExecutions** (command runs)
- **MongoDB** used for persistence
- Command validation to prevent unsafe/malicious inputs
- Exposes REST API endpoints tested via **Postman/cURL**

---

##  Technologies Used

| Component | Technology |
|------------|-------------|
| Language | Java 17+ |
| Framework | Spring Boot |
| Database | MongoDB |
| Build Tool | Maven |
| Testing | Postman / cURL |
| Version Control | Git & GitHub |

---

##  Project Structure

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

##  Configuration

Update the `application.properties` file with your MongoDB details:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/taskdb
server.port=8080
````

---

##  Data Model

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

##  REST API Endpoints

###  **Get all tasks**

**`GET /tasks`**

* Returns all stored tasks.


![GET all tasks](https://raw.githubusercontent.com/Srithinreddy22/kaiburr-task1-backend/main/getall.png)


---

###  **Get task by ID**

**`GET /tasks/{id}`**

* Returns a single task or 404 if not found.


![GET task by ID](https://raw.githubusercontent.com/Srithinreddy22/kaiburr-task1-backend/main/get%20by%20id.png)



![GET task by ID](https://raw.githubusercontent.com/Srithinreddy22/kaiburr-task1-backend/main/404%20not%20found.png)


---

###  **Create or update a task**

**`PUT /tasks`**

* Accepts JSON body representing a task.
* Validates the command to prevent unsafe shell operations.


![PUT task](https://raw.githubusercontent.com/Srithinreddy22/kaiburr-task1-backend/main/post.png)


---

###  **Delete a task**

**`DELETE /tasks/{id}`**

* Deletes the task with the given ID.


![DELETE task](https://raw.githubusercontent.com/Srithinreddy22/kaiburr-task1-backend/main/Delete.png)


---

###  **Find task by name**

**`GET /tasks/find?name={query}`**

* Returns all tasks where the name contains the given string.
* Returns 404 if none found.


![Find task by name](https://raw.githubusercontent.com/Srithinreddy22/kaiburr-task1-backend/main/Search.png)

![Find task by name](https://raw.githubusercontent.com/Srithinreddy22/kaiburr-task1-backend/main/404%20not%20found.png)

---

###  **Execute a task command**

**`PUT /tasks/{id}/execute`**

* Executes the shell command associated with the task.
* Saves a new TaskExecution object (with start/end time and output).


![Execute task](https://raw.githubusercontent.com/Srithinreddy22/kaiburr-task1-backend/main/Execute.png)


---

##  Example Request Body (for PUT /tasks)

```json
{
  "id": "123",
  "name": "Print Hello",
  "owner": "John Smith",
  "command": "echo Hello World!"
}
```

---

##  Run Locally

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
      `http://localhost:8080/tasks`

---

##  Example Commands (cURL)

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
