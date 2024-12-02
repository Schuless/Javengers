package com.senai.javengers.controller;

import com.senai.javengers.service.EpiService;
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

    @Autowired
    private EpiService epiService;

    @GetMapping("/index")
    public String homeView(Model model) {
        if (usuarioService.login) {

            model.addAttribute("epis", epiService.obterListaEpis());
            return "home";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/error")
    public String erroView() {
        return "error";
    }

}
