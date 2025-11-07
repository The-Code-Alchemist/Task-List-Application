package com.codealchemists.tasklist.service;

import com.codealchemists.tasklist.exception.ResourceNotFoundException;
import com.codealchemists.tasklist.model.Task;
import com.codealchemists.tasklist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Lombok injects final fields via constructor
public class TaskService {

    private final TaskRepository taskRepository;

    // Create a new task
    public Task createTask(Task task) {
        task.setId(UUID.randomUUID()); // assign UUID
        return taskRepository.save(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get a task by ID
    public Task getTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    // Update a task
    public Task updateTask(UUID id, Task updatedTask) {
        Task existingTask = getTaskById(id);

        existingTask.setShortDescription(updatedTask.getShortDescription());
        existingTask.setLongDescription(updatedTask.getLongDescription());
        existingTask.setStatus(updatedTask.getStatus());

        return taskRepository.save(existingTask);
    }

    // Delete a task
    public void deleteTask(UUID id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}