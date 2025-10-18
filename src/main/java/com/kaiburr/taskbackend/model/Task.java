package com.kaiburr.taskbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.*;
//import com.kaiburr.taskbackend.model.TaskExecution;

@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String name;
    private String command;
    private List<TaskExecution> taskExecution = new ArrayList<>();
    private String owner;

    public Task() {
    }

    public Task(String name, String command) {
        this.name = name;
        this.command = command;
    }

    // getters & setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcommand() {
        return command;
    }

    public void setcommand(String command) {
        this.command = command;
    }

    public List<TaskExecution> getTaskExecutions() {
        return taskExecution;
    }

    public void setTaskExecutions(List<TaskExecution> taskExecution) {
        this.taskExecution = taskExecution;
    }

    public String getowner() {
        return owner;
    }

    public void setowner(String owner) {
        this.owner = owner;
    }
}
