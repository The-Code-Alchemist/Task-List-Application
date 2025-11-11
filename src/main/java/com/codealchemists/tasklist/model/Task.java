package com.codealchemists.tasklist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tasks")
public record Task(
        @Id
        @GeneratedValue
        UUID id,

        @Column(nullable = false)
        String shortDescription,

        @Column(length = 2000)
        String longDescription,

        @Enumerated(EnumType.STRING)
        TaskStatus status
) {
    // Compact constructor for defaults and validation
    public Task {
        if (status == null) {
            status = TaskStatus.TO_DO;
        }
    }
}