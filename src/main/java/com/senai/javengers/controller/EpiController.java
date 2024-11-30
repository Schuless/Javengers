package com.senai.javengers.controller;


import com.senai.javengers.dto.EpiDto;
import com.senai.javengers.service.EpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@Controller
public class EpiController {

    @Autowired
    EpiService epiService;

    @PostMapping("/epis/cadastrar")
    public String cadastrarEpi(EpiDto epi) {

        boolean sucesso = epiService.cadastrarEpi(epi);

        if(sucesso) {
            return "redirect:/epis/lista";
        } else {
            return "redirect:/epis/lista?erro";
        }

    }

    @DeleteMapping("/epis/{id}")
    public ResponseEntity<String> excluirEpi(@PathVariable Long id){

        boolean sucesso = epiService.excluirEpi(id);

        if (sucesso){
            return ResponseEntity.ok("Epi excluído com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir epi.");

    }

    @PostMapping("/epis/atualizar/{id}")
    public String atualizarEpi(EpiDto epi, @PathVariable Long id) {

        boolean sucesso = epiService.atualizarEpi(epi, id);

        if(sucesso) {
            return "redirect:/epis/lista";
        } else {
            return "redirect:/epis/lista?erro";
        }

    }

}

