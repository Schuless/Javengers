package com.senai.javengers.controller;


import com.senai.javengers.dto.CargoDto;
import com.senai.javengers.dto.LoginDto;
import com.senai.javengers.service.CargoService;
import com.senai.javengers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping()
@Controller
public class CargoViewController {

    @Autowired
    CargoService cargoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/cargos/lista")
    public String exibirCargoView(Model model) {
        if (usuarioService.login) {
            model.addAttribute("cargos", cargoService.obterListaCargos());

            return "cargos/lista";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/cargos/cadastrar")
    public String exibirCargoListasView(Model model) {
        if (usuarioService.login) {
            CargoDto cargo = new CargoDto();

            model.addAttribute("cargoDto", cargo);

            return "cargos/cadastrar";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/cargos/visualizar/{id}")
    public String exibirCargoVisualizarView(Model model, @PathVariable Long id) {
        if (usuarioService.login) {
            CargoDto cargo = cargoService.obterCargo(id);

            model.addAttribute("cargoDto", cargo);

            if (cargo.getCodigo() > 0) {
                return "cargos/visualizar";
            }

            return "redirect:/cargos/lista";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/cargos/atualizar/{id}")
    public String exibirCargoAtualizarView(Model model, @PathVariable Long id) {
        if (usuarioService.login) {
            CargoDto cargo = cargoService.obterCargo(id);

            model.addAttribute("cargoDto", cargo);

            if (cargo.getCodigo() > 0) {
                return "cargos/atualizar";
            }

            return "redirect:/cargos/lista";
        }
        return "redirect:/login?erro";
    }
}