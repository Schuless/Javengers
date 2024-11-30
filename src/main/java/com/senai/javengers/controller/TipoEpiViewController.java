package com.senai.javengers.controller;


import com.senai.javengers.dto.CargoDto;
import com.senai.javengers.dto.LoginDto;
import com.senai.javengers.dto.TipoEpiDto;
import com.senai.javengers.service.CargoService;
import com.senai.javengers.service.TipoEpiService;
import com.senai.javengers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping()
@Controller
public class TipoEpiViewController {

    @Autowired
    TipoEpiService tipoEpiService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/epis/tipo/lista")
    public String exibirTipoEpiView(Model model) {
        if (usuarioService.login) {
            model.addAttribute("tipos", tipoEpiService.obterListaTipoEpi());

            return "tipos/lista";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/epis/tipo/cadastrar")
    public String exibirTipoEpiListasView(Model model) {
        if (usuarioService.login) {
            TipoEpiDto tipo = new TipoEpiDto();

            model.addAttribute("tipoEpiDto", tipo);

            return "tipos/cadastrar";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/epis/tipo/visualizar/{id}")
    public String exibirTipoEpiVisualizarView(Model model, @PathVariable Long id) {
        if (usuarioService.login) {
            TipoEpiDto tipo = tipoEpiService.obterTipoEpi(id);

            model.addAttribute("tipoEpiDto", tipo);

            if (tipo.getCodigo() > 0) {
                return "tipos/visualizar";
            }

            return "redirect:/epis/tipo/lista";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/epis/tipo/atualizar/{id}")
    public String exibirTipoEpiAtualizarView(Model model, @PathVariable Long id) {
        if (usuarioService.login) {
            TipoEpiDto tipo = tipoEpiService.obterTipoEpi(id);

            model.addAttribute("tipoEpiDto", tipo);

            if (tipo.getCodigo() > 0) {
                return "tipos/atualizar";
            }

            return "redirect:/epis/tipo/lista";
        }
        return "redirect:/login?erro";
    }
}