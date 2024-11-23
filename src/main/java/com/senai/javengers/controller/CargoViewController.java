package com.senai.javengers.controller;



import com.senai.javengers.dto.CargoDto;
import com.senai.javengers.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cargo")
@Controller
public class CargoViewController {

    @Autowired
    CargoService cargoService;

    @GetMapping("/lista")
    public String exibirCargoView(Model model) {

        model.addAttribute("cargos",cargoService.obterListaCargos());

        return "cargos/lista";
    }

    @GetMapping("/cadastrar")
    public String exibirCargoListasView(Model model) {

        CargoDto cargo = new CargoDto();

        model.addAttribute("cargoDto", cargo);

        return "cadastrarcargo";
    }

    @GetMapping("/visualizar/{id}")
    public String exibirCargoVisualizarView(Model model, @PathVariable Long id) {

        CargoDto cargo = cargoService.obterCargo(id);

        model.addAttribute("cargoDto", cargo);

        if (cargo.getCodigo() > 0) {
            return "visualizarcargo";
        }

        return "redirect:/cargo";
    }

    @GetMapping("/atualizar/{id}")
    public String exibirCargoAtualizarView(Model model, @PathVariable Long id) {

        CargoDto cargo = cargoService.obterCargo(id);

        model.addAttribute("cargoDto", cargo);

        if (cargo.getCodigo() > 0) {
            return "atualizarcargo";
        }

        return "redirect:/cargo";
    }
}