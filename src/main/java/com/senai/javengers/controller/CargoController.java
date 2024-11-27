package com.senai.javengers.controller;

import com.senai.javengers.dto.CargoDto;
import com.senai.javengers.dto.LoginDto;
import com.senai.javengers.service.CargoService;
import com.senai.javengers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping()
@Controller
public class CargoController {

    @Autowired
    CargoService cargoService;
    
    @PostMapping("/cargos/cadastrar")
    public String cadastrarCargo(CargoDto cargo) {

            boolean sucesso = cargoService.cadastrarCargo(cargo);

            if (sucesso) {
                return "redirect:cargos/lista";
            } else {
                return "redirect:cargos/lista?erro";
            }
    }

    @DeleteMapping("/cargos/{id}")
    public ResponseEntity<String> excluirCargo(@PathVariable Long id) {

        boolean sucesso = cargoService.excluirCargo(id);

        if (sucesso) {
            return ResponseEntity.ok("Cargo excluído com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir cargo.");

    }

    @PostMapping("/cargos/atualizar/{codigo}")
    public String atualizarCargo(CargoDto cargo, @PathVariable Long codigo) {

        boolean sucesso = cargoService.atualizarCargo(cargo, codigo);

        if (sucesso) {
            return "redirect:cargos/lista";
        } else {
            return "redirect:cargos/lista?erro";
        }

    }

}
