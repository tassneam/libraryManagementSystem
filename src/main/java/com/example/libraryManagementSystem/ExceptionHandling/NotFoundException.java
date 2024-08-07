package com.example.libraryManagementSystem.ExceptionHandling;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}

