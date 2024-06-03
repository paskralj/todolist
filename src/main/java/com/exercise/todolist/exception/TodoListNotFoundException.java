package com.exercise.todolist.exception;

public class TodoListNotFoundException extends RuntimeException {
    public TodoListNotFoundException(String message){
        super(message);
    }
}
