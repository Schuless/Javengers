package com.senai.javengers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequestMapping("/home")
@Controller
public class HomeController {

    @GetMapping("/index")
    public String homeView(Model model) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        model.addAttribute("currentDateTime", currentDateTime);
        return "home";

    }

}
