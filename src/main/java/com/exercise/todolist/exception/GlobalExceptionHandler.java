package com.exercise.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A global exception handler for handling exceptions that occur within the controller.
 * Provides centralized exception handling and return of appropriate HTTP status codes.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Method to handle the TodoListNotFoundException exception
     *
     * @param ex TodoListNotFoundException exception that occurred
     * @return ResponseEntity containing the error message and corresponding HTTP status code
     */
    @ExceptionHandler(TodoListNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleTodoListNotFoundException(TodoListNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
