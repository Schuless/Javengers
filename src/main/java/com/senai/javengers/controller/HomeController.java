package com.senai.javengers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
@Controller
public class HomeController {
    
    @GetMapping
    public String homeView(){
       return "home";
    }
    
}
