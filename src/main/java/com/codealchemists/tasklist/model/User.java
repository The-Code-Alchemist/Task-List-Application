package com.codealchemists.tasklist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;


@Entity
@Table(name = "app_user")
public class User {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();


    @Column(unique = true, nullable = false)
    private String username;


    @Column(nullable = false)
    private String passwordHash;


// Add roles here later


// Add constructors, getters, setters
}