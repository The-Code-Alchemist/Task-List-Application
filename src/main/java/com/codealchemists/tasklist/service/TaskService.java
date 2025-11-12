package com.codealchemists.tasklist.service;

import com.codealchemists.tasklist.model.Task;
import com.codealchemists.tasklist.model.TaskStatus;
import com.codealchemists.tasklist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        // new tasks get a generated ID and default status if not provided
        Task newTask = new Task(
                null,
                task.shortDescription(),
                task.longDescription(),
                task.status() != null ? task.status() : TaskStatus.TO_DO
        );
        return taskRepository.save(newTask);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll(); // get all tasks
    }

    public Task getTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(UUID id, Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}