package com.exercise.todolist;

import com.exercise.todolist.exception.TodoListNotFoundException;
import com.exercise.todolist.repository.TodoList;
import com.exercise.todolist.repository.TodoListRepository;
import com.exercise.todolist.service.TodoListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class intended for testing service methods
 */
@SpringBootTest
@Transactional
class TodoListServiceTest {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private TodoListService todoListService;

    @BeforeEach
    public void setUp() {
        todoListRepository.deleteAll();
    }

    /**
     * Tests the retrieval of TodoLists.
     */
    @Test
    void getTodoListsTest() {
        TodoList todo = new TodoList("mrkva");
        todoListService.addTodoList(todo);

        List<TodoList> todoList = todoListService.getTodoLists();

        assertEquals(1, todoList.size());
        assertEquals("mrkva", todoList.get(0).getTitle());
    }

    /**
     * Tests marking a task as done.
     */
    @Test
    void markTaskAsDoneTest() {
        TodoList todo = new TodoList("mrkva");
        todoListService.addTodoList(todo);

        todoListService.markTaskAsDone(todo.getId());
        TodoList updatedTodoList = todoListRepository.findById(todo.getId()).get();

        assertTrue(updatedTodoList.isCompleted());
    }

    /**
     * Tests handling of task not found when marking it as done.
     */
    @Test
    void testMarkTaskAsDoneNotFound() {
        UUID randomId = UUID.randomUUID();

        Executable executable = new Executable() {
            @Override
            public void execute() throws Throwable {
                todoListService.markTaskAsDone(randomId);
            }
        };

        TodoListNotFoundException exception = assertThrows(TodoListNotFoundException.class, executable);

        String expectedMessage = "Todo list with id " + randomId + " not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests deleting a task.
     */
    @Test
    void deleteTaskTest(){
        TodoList todo = new TodoList("mrkva");
        todoListService.addTodoList(todo);

        todoListService.deleteTask(todo.getId());
        int listSize = todoListService.getTodoLists().size();

        assertFalse(todoListRepository.findById(todo.getId()).isPresent());
    }

    /**
     * Tests handling of task not found when deleting it.
     */
    @Test
    void testDeleteTaskNotFound(){
        UUID randomId = UUID.randomUUID();

        Executable executable = new Executable() {
            @Override
            public void execute() throws Throwable {
                todoListService.deleteTask(randomId);
            }
        };
        TodoListNotFoundException exception = assertThrows(TodoListNotFoundException.class, executable);
        String expectedMessage = "Todo list with id " + randomId + " not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
