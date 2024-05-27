package com.exercise.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    private List<TodoList> emptyLista = new ArrayList<>();
    private List<TodoList> todoLists = new ArrayList<>(Arrays.asList(new TodoList("milka"), new TodoList("sladoled")));
    public List<TodoList> getTodoLists(){
        return todoLists;
    }

    /**
     * Handle the root (/) and return a home page
     *
     * @return homepage
     */
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("todolist",getTodoLists());
        model.addAttribute("newTodo", new TodoList());
        return "homepage";
    }

    @PostMapping("/")
    public String addTodo(@ModelAttribute TodoList newTodo, Model model){
        todoLists.add(newTodo);
        return "redirect:/";
    }

}
