package com.exercise.todolist;

import com.exercise.todolist.repository.TodoList;
import com.exercise.todolist.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the TodoList application.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TodolistApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoListRepository todoListRepository;

    /**
     * Verifies that the application context loads successfully
     */
    @Test
    void contextLoads() {
        assertNotNull(mockMvc);
    }

    @BeforeEach
    void setUp() {
        todoListRepository.deleteAll();
    }

    /**
     * Tests the behavior of the home page
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testHomePage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("homepage"))
                .andExpect(model().attributeExists("todolist"))
                .andExpect(model().attributeExists("newTodo"));
    }

    /**
     * Tests adding a new todo item.
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testAddTodo() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/")
                .param("title", "New Task");

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        TodoList todo = todoListRepository.findAll().get(0);
        assert (todo.getTitle().equals("New Task"));
        assertFalse(todo.isCompleted(), "Todo is completed");
    }

    /**
     * Tests adding a new todo item with validation error.
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testAddTodoValidationError() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/")
                .param("title", "");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("homepage"))
                .andExpect(model().attributeHasFieldErrors("newTodo","title"));
    }

    /**
     * Tests marking a task as completed.
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testTaskCompleted() throws Exception{
        TodoList todo = new TodoList("milka");
        todoListRepository.save(todo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/done/" + todo.getId());

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        TodoList todoTask = todoListRepository.findById(todo.getId()).get();
        assertTrue(todoTask.isCompleted(), "Task is not done");
    }

    /**
     * Tests deleting a task.
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testDeleteTask() throws Exception{
        TodoList todo = new TodoList("milka");
        todoListRepository.save(todo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/delete/" + todo.getId());
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertTrue(todoListRepository.findById(todo.getId()).isEmpty(),"Error: task is not deleted");
    }
}
