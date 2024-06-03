package com.exercise.todolist.service;

import com.exercise.todolist.controller.TodoList;
import com.exercise.todolist.exception.TodoListNotFoundException;
import com.exercise.todolist.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoListService {

    @Autowired
    TodoListRepository todoListRepository;

    public List<TodoList> getTodoLists() {
        return todoListRepository.findAll();
    }

    public void addTodoList(TodoList todoList) {
        todoListRepository.save(todoList);
    }

    public void markTaskAsDone(UUID id) {
        if (todoListRepository.findById(id).isPresent()) {
            TodoList foundTodoList = todoListRepository.findById(id).get();
            foundTodoList.setCompleted(true);
            todoListRepository.save(foundTodoList);
        } else {
            throw new TodoListNotFoundException("Todo list with id " + id + " not found");
        }
    }

    public void deleteTask(UUID id) {
        if (todoListRepository.findById(id).isPresent()) {
            TodoList task = todoListRepository.findById(id).get();
            todoListRepository.delete(task);
        } else {
            throw new TodoListNotFoundException("Todo list with id " + id + " not found");
        }
    }

}
