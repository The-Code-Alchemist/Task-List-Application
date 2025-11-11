package com.codealchemists.tasklist.controller;

import com.codealchemists.tasklist.model.Task;
import com.codealchemists.tasklist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService svc) { this.taskService = svc; }

    @GetMapping
    public List<Task> list() { return taskService.listAllTasks(); }

    @GetMapping("/{id}")
    public Task get(@PathVariable UUID id) { return taskService.getTaskById(id); }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) { return ResponseEntity.ok(taskService.createTask(task)); }

    @PutMapping("/{id}")
    public Task update(@PathVariable UUID id, @RequestBody Task task) { return taskService.updateTask(id, task); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) { taskService.deleteTask(id); return ResponseEntity.noContent().build(); }
}