package com.senai.javengers.controller;


import com.senai.javengers.dto.UsuarioDto;
import com.senai.javengers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuarios")
@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public String cadastrarUsuario(UsuarioDto usuario) {

        boolean sucesso = usuarioService.cadastrarUsuario(usuario);

        if(sucesso) {
            return "redirect:usuario";
        } else {
            return "redirect:usuario?erro";
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Long id){

        boolean sucesso = usuarioService.excluirUsuario(id);

        if (sucesso){
            return ResponseEntity.ok("Usuario excluído com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir usuario.");

    }

    @PostMapping("/atualizar/{id}")
    public String finalizarUsuario(UsuarioDto usuario, @PathVariable Long id) {

        boolean sucesso = usuarioService.atualizarUsuario(usuario, id);

        if(sucesso) {
            return "redirect:usuario";
        } else {
            return "redirect:usuario?erro";
        }

    }

    @GetMapping("/visualizar/{id}")
    public String visualizarUsuario(Model model, @PathVariable Long id) {

        UsuarioDto usuario = usuarioService.obterUsuario(id);
        model.addAttribute("usuario", usuario);

        if (usuario.getCodigo() > 0){
            return "atualizarusuario";
        }

        return "redirect:/listausuario";
    }
}

