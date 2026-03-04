package com.todo.controller;

import com.todo.model.Status;
import com.todo.model.Task;
import com.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return service.addTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @PutMapping("/{id}")
    public Task updateStatus(@PathVariable Long id,
                             @RequestParam Status status) {
        return service.updateStatus(id, status);
    }

    @GetMapping("/report")
    public String generateReport() {
        return service.generateReport();
    }

}