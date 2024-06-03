package com.exercise.todolist.service;

import com.exercise.todolist.repository.TodoList;
import com.exercise.todolist.exception.TodoListNotFoundException;
import com.exercise.todolist.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class for managing TodoList entities
 */
@Service
public class TodoListService {

    @Autowired
    TodoListRepository todoListRepository;

    /**
     * Retrieves all TodoList entities
     *
     * @return A list of all TodoList entities
     */
    public List<TodoList> getTodoLists() {
        return todoListRepository.findAll();
    }

    /**
     * Adds a new TodoList entity
     *
     * @param todoList The TodoList entity to add
     */
    public void addTodoList(TodoList todoList) {
        todoListRepository.save(todoList);
    }

    /**
     * Marks a task within a TodoList entity as done
     *
     * @param id The unique identifier of the TodoList entity
     * @throws TodoListNotFoundException If the TodoList with the given id is not found
     */
    public void markTaskAsDone(UUID id) {
        if (todoListRepository.findById(id).isPresent()) {
            TodoList foundTodoList = todoListRepository.findById(id).get();
            foundTodoList.setCompleted(true);
            todoListRepository.save(foundTodoList);
        } else {
            throw new TodoListNotFoundException("Todo list with id " + id + " not found");
        }
    }

    /**
     * Deletes a task within a TodoList entity
     *
     * @param id The unique identifier of the TodoList entity
     * @throws TodoListNotFoundException If the TodoList with the given id is not found
     */
    public void deleteTask(UUID id) {
        if (todoListRepository.findById(id).isPresent()) {
            TodoList task = todoListRepository.findById(id).get();
            todoListRepository.delete(task);
        } else {
            throw new TodoListNotFoundException("Todo list with id " + id + " not found");
        }
    }

}
