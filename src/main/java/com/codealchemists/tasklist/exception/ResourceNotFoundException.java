package com.codealchemists.tasklist.exception;

/**
 * Thrown when a requested resource (like a Task or User) cannot be found.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}