package com.senai.javengers.controller;

import com.senai.javengers.dto.LoginDto;
import com.senai.javengers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class LoginController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/login")
    public String exibirLogin(Model model){
        
        LoginDto loginDto = new LoginDto();
        
        model.addAttribute("loginDto", loginDto);
        
        //--template : retorna o nome do arquivo html localizado lá na pasta templates.
        return "login";
    }
    
    @PostMapping("/login")
    public String realizarLogin(@ModelAttribute("loginDto") LoginDto loginDto){           
        
        //--Chamar método da classe UsuarioService passando por parâmetro o Dto
        boolean acesso = usuarioService.validarLogin(loginDto);
        
        if(acesso){            
            return "redirect:home/index";
        }
        
        return "redirect:login?erro";
        
    } 
        
    @PostMapping("/logout")
    public String realizarLogout(){        
        return "redirect:login?logout";
    }
}
