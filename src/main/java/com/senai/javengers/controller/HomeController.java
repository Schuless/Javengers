package com.senai.javengers.controller;

import com.senai.javengers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequestMapping("/home")
@Controller
public class HomeController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/index")
    public String homeView(Model model) {
        if (usuarioService.login) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String currentDateTime = LocalDateTime.now().format(formatter);
            model.addAttribute("currentDateTime", currentDateTime);
            return "home";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/error")
    public String erroView() {
        return "error";
    }

}
