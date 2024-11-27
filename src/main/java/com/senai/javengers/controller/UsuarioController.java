package com.senai.javengers.controller;


import com.senai.javengers.dto.UsuarioDto;
import com.senai.javengers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/usuarios/cadastrar")
    public String cadastrarUsuario(UsuarioDto usuario) {

        boolean sucesso = usuarioService.cadastrarUsuario(usuario);

        if(sucesso) {
            return "redirect:/usuarios/lista";
        } else {
            return "redirect:/usuarios/lista?erro";
        }

    }

    @DeleteMapping("/usuarios/{codigo}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Long codigo){

        boolean sucesso = usuarioService.excluirUsuario(codigo);

        if (sucesso){
            return ResponseEntity.ok("Usuario excluído com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir usuario.");

    }

    @PostMapping("/usuarios/atualizar/{id}")
    public String atualziarUsuario(UsuarioDto usuario, @PathVariable Long id) {

        boolean sucesso = usuarioService.atualizarUsuario(usuario, id);

        if(sucesso) {
            return "redirect:/usuarios/lista";
        } else {
            return "redirect:/usuarios/lista?erro";
        }

    }
}

