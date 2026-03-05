package com.todo.controller;

import com.todo.model.Status;
import com.todo.model.Task;
import com.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task API", description = "Operations related to Task Management")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }


    @PostMapping
    @Operation(summary = "Add a new task")
    public Task addTask(@RequestBody Task task) {
        return service.addTask(task);
    }


    @GetMapping
    @Operation(summary = "Get all tasks")
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @PutMapping("/{id}")
    public Task updateStatus(@PathVariable Long id,
                             @RequestParam("status") Status status) {
        return service.updateStatus(id, status);
    }

    @GetMapping("/report")
    @Operation(summary = "Generate task report")
    public String generateReport() {
        return service.generateReport();
    }

}