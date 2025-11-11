package com.codealchemists.tasklist.service;

import com.codealchemists.tasklist.exception.ResourceNotFoundException;
import com.codealchemists.tasklist.model.Task;
import com.codealchemists.tasklist.model.TaskStatus;
import com.codealchemists.tasklist.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Create task
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

    // Read all tasks
    public List<Task> listAllTasks() {
        return taskRepository.findAll();
    }

    // Read task (by ID)
    public Task getTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }


    // Update existing task
    public Task updateTask(UUID id, Task updatedTask) {
        Task existingTask = getTaskById(id);

        Task mergedTask = new Task(
                existingTask.id(),
                updatedTask.shortDescription() != null ? updatedTask.shortDescription() : existingTask.shortDescription(),
                updatedTask.longDescription() != null ? updatedTask.longDescription() : existingTask.longDescription(),
                updatedTask.status() != null ? updatedTask.status() : existingTask.status()
        );

        return taskRepository.save(mergedTask);
    }

    // Delete existing task
    public void deleteTask(UUID id) { taskRepository.delete(getTaskById(id)); }
}