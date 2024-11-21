package com.senai.javengers.controller;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ColaboradorViewController {

    @Autowired
    ColaboradorService colaboradorService;

    @GetMapping("/colaboradores/index")
    public String exibirColaboradorView(Model model) {

        model.addAttribute("colaboradores",colaboradorService.obterListaColaboradores());

        return "colaboradores/index";
    }

    @GetMapping("/cadastrar")
    public String exibirColaboradorListasView(Model model) {

        ColaboradorDto colaborador = new ColaboradorDto();

        model.addAttribute("colaboradorDto", colaborador);

        return "cadastrarcolaborador";
    }

    @GetMapping("/visualizar/{id}")
    public String exibirColaboradorVisualizarView(Model model, @PathVariable Long id) {

        ColaboradorDto colaborador = colaboradorService.obterColaborador(id);

        model.addAttribute("colaboradorDto", colaborador);

        if (colaborador.getCodigo() > 0) {
            return "visualizarcolaborador";
        }

        return "redirect:/colaboradores";
    }

    @GetMapping("/atualizar/{id}")
    public String exibirColaboradorAtualizarView(Model model, @PathVariable Long id) {

        ColaboradorDto colaborador= colaboradorService.obterColaborador(id);

        model.addAttribute("colaboradorDto", colaborador);

        if (colaborador.getCodigo() > 0) {
            return "atualizarcolaborador";
        }

        return "redirect:/colaboradores";
    }
}