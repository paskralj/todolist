package com.exercise.todolist.controller;

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

@Controller
public class HomeController {

    @Autowired
    TodoListService todoListService;

    /**
     * Handle the root (/) and return a home page
     *
     * @return homepage
     */
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("todolist", todoListService.getTodoLists());
        model.addAttribute("newTodo", new TodoList());
        return "homepage";
    }

    @PostMapping("/")
    public String addTodo(@Valid @ModelAttribute("newTodo") TodoList newTodo, BindingResult result, Model model) {

        if (result.hasErrors()){
            model.addAttribute("todolist",todoListService.getTodoLists());
            return "homepage";
        }
        todoListService.addTodoList(newTodo);
        return "redirect:/";
    }

    @PostMapping("/done/{id}")
    public String taskCompleted(@PathVariable UUID id) {
        todoListService.markTaskAsDone(id);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable UUID id) {
        todoListService.deleteTask(id);
        return "redirect:/";
    }

}
