package com.senai.javengers.controller;

import com.senai.javengers.dto.EmprestimoDto;
import com.senai.javengers.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/colaborador")
@Controller
public class EmprestimoController {

    @Autowired
    EmprestimoService emprestimoService;

    @GetMapping
    public String exibirEmprestimoView(Model model) {
        return "emprestimo";
    }

    @GetMapping("/cadastrar")
    public String exibirEmprestimoListasView(Model model) {

       EmprestimoDto emprestimo = new EmprestimoDto();

        model.addAttribute("emprestimoDto", emprestimo);

        return "cadastraremprestimo";
    }

    @GetMapping("/visualizar/{id}")
    public String exibirEmprestimoVisualizarView(Model model, @PathVariable Long id) {

        EmprestimoDto emprestimo = emprestimoService.obterEmprestimo(id);

        model.addAttribute("emprestimoDto", emprestimo);

        if (emprestimo.getCodigo() > 0) {
            return "visualizaremprestimo";
        }

        return "redirect:/emprestimo";
    }

    @GetMapping("/atualizar/{id}")
    public String exibirEmprestimoAtualizarView(Model model, @PathVariable Long id) {

        EmprestimoDto emprestimo= emprestimoService.obterEmprestimo(id);

        model.addAttribute("emprestimoDto", emprestimo);

        if (emprestimo.getCodigo() > 0) {
            return "atualizaremprestimo";
        }

        return "redirect:/emprestimo";
    }
}

