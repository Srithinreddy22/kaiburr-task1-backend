package com.kaiburr.taskbackend.Controller;

import com.kaiburr.taskbackend.model.Task;
import com.kaiburr.taskbackend.model.TaskExecution;
import com.kaiburr.taskbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final Pattern forbidden = Pattern.compile("[;&|`$<>\\\\*(){}\\[\\]]");
    private static final Set<String> allowed = Set.of("echo", "date", "ls", "pwd");

    @Autowired
    private TaskRepository repo;

    // ✅ Simple endpoint to check service
    @GetMapping("/hello")
    public String hello() {
        return "Hello Kaiburr!";
    }

    // ✅ GET /tasks -> list all
    @GetMapping
    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    // ✅ GET /tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Optional<Task> task = repo.findById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ POST /tasks -> create
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        if (!isValid(task.getcommand())) {
            return ResponseEntity.badRequest().build();
        }
        Task saved = repo.save(task);
        return ResponseEntity.ok(saved);
    }

    // ✅ PUT /tasks/{id} -> execute command by ID
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id) {
        Task executed = executeById(id);
        if (executed == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(executed);
    }

    // ✅ DELETE /tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ GET /tasks/search?name=foo
    @GetMapping("/search")
    public List<Task> searchByName(@RequestParam String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    // ✅ Command validation
    public static boolean isValid(String command) {
        if (command == null || command.trim().isEmpty())
            return false;
        command = command.trim();
        if (forbidden.matcher(command).find())
            return false;
        if (command.contains("&&") || command.contains("||"))
            return false;
        if (command.length() > 1000)
            return false;

        String firstWord = command.split("\\s+")[0];
        return allowed.contains(firstWord);
    }

    // ✅ Execute a task by ID
    public Task executeById(String id) {
        Optional<Task> optionalTask = repo.findById(id);
        if (optionalTask.isEmpty()) {
            return null;
        }

        Task task = optionalTask.get();
        try {
            Date startTime = new Date();

            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder processBuilder;

            if (os.contains("win")) {
                processBuilder = new ProcessBuilder("cmd.exe", "/c", task.getcommand());
            } else {
                processBuilder = new ProcessBuilder("bash", "-c", task.getcommand());
            }

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            String output = new String(process.getInputStream().readAllBytes());
            process.waitFor();

            Date endTime = new Date();
            TaskExecution execution = new TaskExecution(startTime, endTime, output.trim());

            if (task.getTaskExecutions() == null) {
                task.setTaskExecutions(new ArrayList<>());
            }

            task.getTaskExecutions().add(execution);
            repo.save(task);

            return task;
        } catch (Exception e) {
            throw new RuntimeException("Error executing command: " + e.getMessage());
        }
    }
}
