package com.exercise.todolist.controller;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class TodoList {

    private UUID id;
    @NotBlank(message = "Cannot be blank")
    private String title;
    private boolean completed;

    public TodoList() {
        this.completed = false;
    }

    public TodoList(String title) {
        this.title = title;
        this.completed = false;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
