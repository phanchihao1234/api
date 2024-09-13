package com.example.spring_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/admin")
public class AdminController {
    @GetMapping("/")
    public String getAll(){
        return "Nguyen hay Ngu?";
    }
    @PostMapping("/create")
    public String create(){
        return "Nguyen hay Ngu?";
    }
}
