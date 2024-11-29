package com.senai.javengers.controller;

import com.senai.javengers.dto.EmprestimoDto;
import com.senai.javengers.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@Controller
public class EmprestimoController {

    @Autowired
    EmprestimoService emprestimoService;

    @PostMapping("/emprestimos/cadastrar")
    public String cadastrarEmprestimo(EmprestimoDto emprestimo) {

        boolean sucesso = emprestimoService.cadastrarEmprestimo(emprestimo);

        if(sucesso) {
            return "redirect:/emprestimos/lista";
        } else {
            return "redirect:/emprestimos/cadastrar?erro";
        }

    }

    @DeleteMapping("/emprestimos/{id}")
    public ResponseEntity<String> excluirEmprestimo(@PathVariable Long id){

        boolean sucesso = emprestimoService.excluirEmprestimo(id);

        if (sucesso){
            return ResponseEntity.ok("Emprestimo excluído com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir emprestimo.");

    }

    @PostMapping("/emprestimos/finalizar/{id}")
    public String finalizarEmprestimo(@PathVariable Long id) {

        boolean sucesso = emprestimoService.finalizarEmprestimo(id);

        if(sucesso) {
            return "redirect:/emprestimos/lista";
        } else {
            return "redirect:/emprestimos/finalizar?erro";
        }

    }
}

