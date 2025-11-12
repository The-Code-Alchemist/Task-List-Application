package com.codealchemists.tasklist.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public record User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        UUID id,

        @Column(unique = true, nullable = false)
        String username,

        @Column(nullable = false)
        String password
) {
        // convenience constructor (for new users before an id is generated)
        public User(String username, String password) {
                this(null, username, password);
        }
}