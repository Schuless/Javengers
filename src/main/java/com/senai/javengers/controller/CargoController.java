package com.senai.javengers.controller;

import com.senai.javengers.dto.CargoDto;
import com.senai.javengers.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cargos")
@Controller
public class CargoController {

    @Autowired
    CargoService cargoService;

    @PostMapping("/cadastrar")
    public String cadastrarCargo(CargoDto cargo) {

        boolean sucesso = cargoService.cadastrarCargo(cargo);

        if(sucesso) {
            return "redirect:cargos";
        } else {
            return "redirect:cargos?erro";
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCargo(@PathVariable Long id){

        boolean sucesso = cargoService.excluirCargo(id);

        if (sucesso){
            return ResponseEntity.ok("Cargo excluído com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir cargo.");

    }

    @PostMapping("/atualizar/{id}")
    public String atualizarCargo(CargoDto cargo, @PathVariable Long id) {

        boolean sucesso = cargoService.atualizarCargo(cargo, id);

        if(sucesso) {
            return "redirect:cargos";
        } else {
            return "redirect:cargos?erro";
        }

    }


}
