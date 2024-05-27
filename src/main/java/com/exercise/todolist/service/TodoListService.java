package com.exercise.todolist.service;

import com.exercise.todolist.controller.TodoList;
import com.exercise.todolist.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {

    @Autowired
    TodoListRepository todoListRepository;

    public List<TodoList> getTodoLists() {
        return todoListRepository.getTodoLists();
    }

    public void addTodoList(TodoList todoList){
        todoListRepository.addTodoList(todoList);
    }

}
