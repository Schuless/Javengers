package com.senai.javengers.controller;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.model.CargoModel;
import com.senai.javengers.service.CargoService;
import com.senai.javengers.service.ColaboradorService;
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

public class ColaboradorViewController {

    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/colaboradores/lista")
    public String exibirColaboradorView(Model model) {
        if (usuarioService.login) {
            model.addAttribute("colaboradores", colaboradorService.obterListaColaboradores());

            return "colaboradores/lista";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/colaboradores/cadastrar")
    public String exibirColaboradorListasView(Model model) {
        if (usuarioService.login) {
            List<CargoModel> cargosAtivos = cargoService.obterListaCargosAtivos();

            model.addAttribute("cargos", cargosAtivos);
            model.addAttribute("colaboradorDto", new ColaboradorDto());

            return "colaboradores/cadastro";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/colaboradores/visualizar/{id}")
    public String exibirColaboradorVisualizarView(Model model, @PathVariable Long id) {
        if (usuarioService.login) {
            ColaboradorDto colaborador = colaboradorService.obterColaborador(id);

            model.addAttribute("colaboradorDto", colaborador);

            if (colaborador.getCodigo() > 0) {
                return "colaboradores/visualizar";
            }

            return "redirect:/colaboradores/lista?erro";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/colaboradores/atualizar/{id}")
    public String exibirColaboradorAtualizarView(Model model, @PathVariable Long id) {
        if (usuarioService.login) {
            ColaboradorDto colaborador = colaboradorService.obterColaborador(id);
            List<CargoModel> cargosAtivos = cargoService.obterListaCargosAtivos();

            model.addAttribute("cargos", cargosAtivos);
            model.addAttribute("colaboradorDto", colaborador);

            if (colaborador.getCodigo() > 0) {
                return "colaboradores/atualizar";
            }

            return "redirect:/colaboradores/lista?erro";
        }
        return "redirect:/login?erro";
    }
}
