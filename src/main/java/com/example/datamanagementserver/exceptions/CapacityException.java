package com.example.datamanagementserver.exceptions;

public class CapacityException extends RuntimeException {
    public CapacityException() {
        super("Auditorium capacity is less than the number of students");
    }
}
