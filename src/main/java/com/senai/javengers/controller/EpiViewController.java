package com.senai.javengers.controller;

import com.senai.javengers.dto.EpiDto;
import com.senai.javengers.service.EpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/epis")
@Controller

public class EpiViewController {

    @Autowired
    EpiService epiService;

    @GetMapping
    public String exibirEmprestimoView(Model model) {

        model.addAttribute("epis",epiService.obterListaEpis());

        return "epi/lista";
    }

    @GetMapping("/cadastrar")
    public String exibirEpiListasView(Model model) {

        EpiDto epi = new EpiDto();

        model.addAttribute("epiDto", epi);

        return "epi/cadastrar";
    }

    @GetMapping("/visualizar/{id}")
    public String exibirEpiVisualizarView(Model model, @PathVariable Long id) {

        EpiDto epi = epiService.obterEpi(id);

        model.addAttribute("epiDto", epi);

        if (epi.getCodigo() > 0) {
            return "epi/visualizar";
        }

        return "redirect:/epi";
    }

    @GetMapping("/atualizar/{id}")
    public String exibirEpiAtualizarView(Model model, @PathVariable Long id) {

        EpiDto epi= epiService.obterEpi(id);

        model.addAttribute("epiDto", epi);

        if (epi.getCodigo() > 0) {
            return "epi/atualizar";
        }

        return "redirect:/epi";
    }
}

