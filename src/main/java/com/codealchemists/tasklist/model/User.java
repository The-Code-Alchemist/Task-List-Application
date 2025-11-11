package com.codealchemists.tasklist.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        @Column(unique = true, nullable = false)
        String username,

        @Column(nullable = false)
        String passwordHash
) {
}