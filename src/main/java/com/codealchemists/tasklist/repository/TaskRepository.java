package com.codealchemists.tasklist.repository;

import com.codealchemists.tasklist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    // Spring Data JPA automatically provides:
    // - save(task)
    // - findAll()
    // - findById(id)
    // - delete(task)
}