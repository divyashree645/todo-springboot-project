package com.todo.service;

import com.todo.model.Status;
import com.todo.model.Task;
import com.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task addTask(Task task) {
        task.setStatus(Status.YET_TO_DO);
        return repository.save(task);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task updateStatus(Long id, Status status) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(status);
        return repository.save(task);
    }

    public String generateReport() {
        long done = repository.findAll().stream()
                .filter(t -> t.getStatus() == Status.DONE).count();

        long doing = repository.findAll().stream()
                .filter(t -> t.getStatus() == Status.DOING).count();

        long yet = repository.findAll().stream()
                .filter(t -> t.getStatus() == Status.YET_TO_DO).count();

        return "Done: " + done +
                ", Doing: " + doing +
                ", Yet To Do: " + yet;
    }
}