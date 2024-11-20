package com.senai.javengers.controller;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/colaborador")
@Controller
public class ColaboradorController {

        @Autowired
        ColaboradorService colaboradorService;

        @GetMapping
        public String exibirColaboradorView(Model model) {
            return "colaborador";
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

            return "redirect:/colaborador";
        }

        @GetMapping("/atualizar/{id}")
        public String exibirColaboradorAtualizarView(Model model, @PathVariable Long id) {

            ColaboradorDto colaborador= colaboradorService.obterColaborador(id);

            model.addAttribute("colaboradorDto", colaborador);

            if (colaborador.getCodigo() > 0) {
                return "atualizarcolaborador";
            }

            return "redirect:/colaborador";
        }
}
