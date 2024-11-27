package com.senai.javengers.controller;

import com.senai.javengers.dto.UsuarioDto;
import com.senai.javengers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/usuarios")
@Controller

public class UsuarioViewController {

    @Autowired
    UsuarioService usuarioService;


    @GetMapping("/lista")
    public String exibirUsuarioView(Model model) {
        if (usuarioService.login) {
            model.addAttribute("usuarios", usuarioService.obterListaUsuarios());

            return "usuarios/lista";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/cadastrar")
    public String exibirUsuarioListasView(Model model) {
        if (usuarioService.login) {
            UsuarioDto usuario = new UsuarioDto();

            model.addAttribute("usuarioDto", usuario);

            return "usuarios/cadastrar";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/visualizar/{id}")
    public String exibirUsuarioVisualizarView(Model model, @PathVariable Long id) {
        if (usuarioService.login) {
            UsuarioDto usuario = usuarioService.obterUsuario(id);

            model.addAttribute("usuarioDto", usuario);

            if (usuario.getCodigo() > 0) {
                return "usuarios/visualizar";
            }

            return "redirect:/usuarios/lista";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/atualizar/{id}")
    public String exibirUsuarioAtualizarView(Model model, @PathVariable Long id) {
        if (usuarioService.login) {
            UsuarioDto usuario = usuarioService.obterUsuario(id);

            model.addAttribute("usuarioDto", usuario);

            if (usuario.getCodigo() > 0) {
                return "usuarios/atualizar";
            }

            return "redirect:/usuarios";
        }
        return "redirect:/login?erro";
    }
}

