package com.example.datamanagementserver.exceptions;

public class AlreadyBookedException extends RuntimeException {
    public AlreadyBookedException() {
        super("The auditorium is already booked at this time");
    }
}
