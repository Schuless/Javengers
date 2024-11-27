package com.senai.javengers.controller;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/colaborador")
@Controller
public class ColaboradorController {

    @Autowired
    ColaboradorService colaboradorService;

    @PostMapping("/cadastrar")
    public String cadastrarColaborador(@ModelAttribute ColaboradorDto colaborador) {

        boolean sucesso = colaboradorService.cadastrarColaborador(colaborador);

        return sucesso ? "redirect:/colaborador/lista" : "redirect:/colaborador?error";

    }

    @DeleteMapping("/{colaboradorId}")
    public ResponseEntity<String> excluirColaborador(@PathVariable Long colaboradorId){

        boolean sucesso = colaboradorService.excluirColaborador(colaboradorId);

        if (sucesso){
            return ResponseEntity.ok("Colaborador excluído com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir Colaborador.");

    }

    @PostMapping("/atualizar/{id}")
    public String atualizarColaborador(@ModelAttribute ColaboradorDto colaborador, @PathVariable Long id) {

        boolean sucesso = colaboradorService.atualizarColaborador(colaborador, id);

        return sucesso ? "redirect:/colaborador/lista" : "redirect:/colaborador?error";
    }


}
