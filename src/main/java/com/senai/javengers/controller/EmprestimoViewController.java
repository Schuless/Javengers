package com.senai.javengers.controller;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.dto.EmprestimoDto;
import com.senai.javengers.dto.EpiDto;
import com.senai.javengers.service.ColaboradorService;
import com.senai.javengers.service.EmprestimoService;
import com.senai.javengers.service.EpiService;
import com.senai.javengers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
@Controller

public class EmprestimoViewController {

    @Autowired
    EmprestimoService emprestimoService;
    @Autowired
    private EpiService epiService;
    @Autowired
    private ColaboradorService colaboradorService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/emprestimos/lista")
    public String exibirEmprestimosView(Model model) {
        if (usuarioService.login) {
            model.addAttribute("emprestimos", emprestimoService.obterListaEmprestimos());

            return "emprestimos/lista";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/emprestimos/cadastrar")
    public String exibirEmprestimoListaView(Model model) {
        if (usuarioService.login) {
            EmprestimoDto emprestimo = new EmprestimoDto();
            List<EpiDto> equipamentosCadastrados = epiService.obterListaEpis();
            List<ColaboradorDto> colaboradoresCadastrados = colaboradorService.obterListaColaboradores();

            model.addAttribute("epis", equipamentosCadastrados);
            model.addAttribute("colaboradores", colaboradoresCadastrados);
            model.addAttribute("emprestimoDto", emprestimo);

            return "emprestimos/cadastrar";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/emprestimos/visualizar/{id}")
    public String exibirEmprestimoVisualizarView(Model model, @PathVariable Long id) {
        if (usuarioService.login) {
            EmprestimoDto emprestimo = emprestimoService.obterEmprestimo(id);

            model.addAttribute("emprestimoDto", emprestimo);

            if (emprestimo.getCodigo() > 0) {
                return "emprestimos/visualizar";
            }

            return "redirect:/emprestimos/lista";
        }
        return "redirect:/login?erro";
    }

}
