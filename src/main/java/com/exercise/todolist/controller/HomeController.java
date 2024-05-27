package com.exercise.todolist.controller;

import com.exercise.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private List<TodoList> emptyLista = new ArrayList<>();
    @Autowired
    TodoListService todoListService;

    /**
     * Handle the root (/) and return a home page
     *
     * @return homepage
     */
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("todolist",todoListService.getTodoLists());
        model.addAttribute("newTodo", new TodoList());
        return "homepage";
    }

    @PostMapping("/")
    public String addTodo(@ModelAttribute TodoList newTodo){
        todoListService.addTodoList(newTodo);
        return "redirect:/";
    }

}
