package com.exercise.todolist.controller;

import com.exercise.todolist.repository.TodoList;
import com.exercise.todolist.service.TodoListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

/**
 * Controller for handling requests related to the home page and todo list operations
 */
@Controller
public class HomeController {

    @Autowired
    private TodoListService todoListService;

    /**
     * Handle the root (/) and return a home page.
     *
     * @param model Model object to add attributes for rendering the view
     * @return The name of the homepage view template
     */
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("todolist", todoListService.getTodoLists());
        model.addAttribute("newTodo", new TodoList());
        return "homepage";
    }

    /**
     * Handle the addition of new Todo tasks
     *
     * @param newTodo new Todo item to be added
     * @param result BindingResult for validation errors
     * @param model Model object to add attributes for rendering the view
     * @return Redirect to the homepage if successful, otherwise stay on the same page
     */
    @PostMapping("/")
    public String addTodo(@Valid @ModelAttribute("newTodo") TodoList newTodo, BindingResult result, Model model) {

        if (result.hasErrors()){
            model.addAttribute("todolist",todoListService.getTodoLists());
            return "homepage";
        }
        todoListService.addTodoList(newTodo);
        return "redirect:/";
    }

    /**
     * Handle the completion of a task
     *
     * @param id The unique identifier of the task to mark as done
     * @return Redirect to the homepage after marking the task as done
     */
    @PostMapping("/done/{id}")
    public String taskCompleted(@PathVariable UUID id) {
        todoListService.markTaskAsDone(id);
        return "redirect:/";
    }

    /**
     * Handle the deletion of a task
     *
     * @param id The unique identifier of the task to delete
     * @return Redirect to the homepage after deleting the task
     */
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable UUID id) {
        todoListService.deleteTask(id);
        return "redirect:/";
    }

}
