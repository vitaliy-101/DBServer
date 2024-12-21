package com.example.datamanagementserver.exceptions;

public class NotFoundByIdException extends RuntimeException {
    public NotFoundByIdException(String entityName, Long id) {
        super(String.format("%s is not found by id = %d", entityName, id));
    }
}
