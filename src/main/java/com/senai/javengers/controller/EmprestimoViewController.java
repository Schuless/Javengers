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

@Controller
@RequestMapping("/emprestimo")
public class EmprestimoViewController {

    @Autowired
    EmprestimoService emprestimoService;

    @GetMapping("/lista")
    public String exibirEmprestimosView(Model model) {

        model.addAttribute("colaboradores",emprestimoService.obterListaEmprestimos());

        return "colaboradores/lista";
    }

    @GetMapping("/cadastrar")
    public String exibirEmprestimoListaView(Model model) {

        ColaboradorDto colaborador = new ColaboradorDto();

        model.addAttribute("colaboradorDto", colaborador);

        return "cadastrarcolaborador";
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

    @GetMapping("/atualizar/{id}")
    public String exibirEmprestimoAtualizarView(Model model, @PathVariable Long id) {

        EmprestimoDto emprestimo = emprestimoService.obterEmprestimo(id);

        model.addAttribute("emprestimoDto", emprestimo);

        if (emprestimo.getCodigo() > 0) {
            return "emprestimos/atualizar";
        }

        return "redirect:/emprestimos/lista";
    }

}
