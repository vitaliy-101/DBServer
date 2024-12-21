package com.example.datamanagementserver.exceptions;

public class NotFoundByFilterFieldException extends RuntimeException {
    public NotFoundByFilterFieldException() {
        super("Not found by filter field");
    }
}
