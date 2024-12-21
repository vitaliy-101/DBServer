package com.example.datamanagementserver.handler;


import com.example.datamanagementserver.exceptions.AlreadyBookedException;
import com.example.datamanagementserver.exceptions.CapacityException;
import com.example.datamanagementserver.exceptions.NotFoundByFilterFieldException;
import com.example.datamanagementserver.exceptions.NotFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundByIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotFoundByIdException(NotFoundByIdException ex) {
        return new ErrorResponse("Entity not found by id", ex.getMessage());
    }

    @ExceptionHandler(NotFoundByFilterFieldException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundByFilterException(NotFoundByFilterFieldException ex) {
        return new ErrorResponse("Entity not found by filter field", ex.getMessage());
    }

    @ExceptionHandler(CapacityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCapacityException(CapacityException ex) {
        return new ErrorResponse("Capacity exception", ex.getMessage());
    }

    @ExceptionHandler(AlreadyBookedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBookException(AlreadyBookedException ex) {
        return new ErrorResponse("Booking exception", ex.getMessage());
    }

}
