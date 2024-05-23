package com.exercise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

}
