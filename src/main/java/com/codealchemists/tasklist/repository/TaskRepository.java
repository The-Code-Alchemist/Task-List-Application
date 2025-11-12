package com.codealchemists.tasklist.repository;

import com.codealchemists.tasklist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}