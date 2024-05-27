package com.exercise.todolist.repository;

import com.exercise.todolist.controller.TodoList;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TodoListRepository {

    private List<TodoList> todoLists = new ArrayList<>(Arrays.asList(new TodoList("milka"), new TodoList("sladoled")));

    public List<TodoList> getTodoLists(){
        return todoLists;
    }

    public void addTodoList(TodoList todoList){
        todoLists.add(todoList);
    }
}
