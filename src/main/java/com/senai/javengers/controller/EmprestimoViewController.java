package com.senai.javengers.controller;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.dto.EmprestimoDto;
import com.senai.javengers.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/emprestimo")
@Controller

public class EmprestimoViewController {

    @Autowired
    EmprestimoService emprestimoService;

    @GetMapping("/lista")
    public String exibirEmprestimosView(Model model) {

        model.addAttribute("emprestimos",emprestimoService.obterListaEmprestimos());

        return "emprestimo/lista";
    }

    @GetMapping("/cadastrar")
    public String exibirEmprestimoListaView(Model model) {

        EmprestimoDto emprestimo = new EmprestimoDto();

        model.addAttribute("emprestimoDto", emprestimo);

        return "emprestimos/cadastrar";
    }

    @GetMapping("/visualizar/{id}")
    public String exibirEmprestimoVisualizarView(Model model, @PathVariable Long id) {

        EmprestimoDto emprestimo = emprestimoService.obterEmprestimo(id);

        model.addAttribute("emprestimoDto", emprestimo);

        if (emprestimo.getCodigo() > 0) {
            return "emprestimos/visualizar";
        }

        return "redirect:/emprestimos/lista";
    }

    @GetMapping("/finalizar/{id}")
    public String exibirEmprestimoFinalizarView(Model model, @PathVariable Long id) {

        EmprestimoDto emprestimo = emprestimoService.obterEmprestimo(id);

        model.addAttribute("emprestimoDto", emprestimo);

        if (emprestimo.getCodigo() > 0) {
            return "emprestimos/finalizar";
        }

        return "redirect:/emprestimos/lista";
    }

}
