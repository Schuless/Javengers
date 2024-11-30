package com.senai.javengers.controller;

import com.senai.javengers.dto.EpiDto;
import com.senai.javengers.model.CargoModel;
import com.senai.javengers.model.TipoEpiModel;
import com.senai.javengers.service.EpiService;
import com.senai.javengers.service.TipoEpiService;
import com.senai.javengers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping()
@Controller

public class EpiViewController {

    @Autowired
    EpiService epiService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    TipoEpiService tipoEpiService;


    @GetMapping("/epis/lista")
    public String exibirEpiView(Model model) {
        if (usuarioService.login) {
            model.addAttribute("epis", epiService.obterListaEpis());

            return "epi/lista";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/epis/cadastrar")
    public String exibirEpiListasView(Model model) {
        if (usuarioService.login) {
            EpiDto epi = new EpiDto();
            List<TipoEpiModel> tiposAtivos = tipoEpiService.obterListaTipoEpiAtivo();

            model.addAttribute("tipos", tiposAtivos);
            model.addAttribute("epiDto", epi);

            return "epi/cadastrar";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/epis/visualizar/{id}")
    public String exibirEpiVisualizarView(Model model, @PathVariable Long id) {
        if (usuarioService.login) {
            EpiDto epi = epiService.obterEpi(id);

            model.addAttribute("epiDto", epi);

            if (epi.getCodigo() > 0) {
                return "/epi/visualizar";
            }

            return "redirect:/epi/lista";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/epis/atualizar/{id}")
    public String exibirEpiAtualizarView(Model model, @PathVariable Long id) {
        if (usuarioService.login) {
            EpiDto epi = epiService.obterEpi(id);
            List<TipoEpiModel> tiposAtivos = tipoEpiService.obterListaTipoEpiAtivo();

            model.addAttribute("tipos", tiposAtivos);
            model.addAttribute("epiDto", epi);

            if (epi.getCodigo() > 0) {
                return "epi/atualizar";
            }

            return "redirect:/epi/lista";
        }
        return "redirect:/login?erro";
    }
}

