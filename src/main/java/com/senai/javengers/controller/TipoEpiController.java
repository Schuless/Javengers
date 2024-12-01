package com.senai.javengers.controller;

import com.senai.javengers.dto.CargoDto;
import com.senai.javengers.dto.LoginDto;
import com.senai.javengers.dto.TipoEpiDto;
import com.senai.javengers.service.CargoService;
import com.senai.javengers.service.TipoEpiService;
import com.senai.javengers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping()
@Controller
public class TipoEpiController {

    @Autowired
    TipoEpiService tipoEpiService;

    @PostMapping("/epis/tipo/cadastrar")
    public String cadastrarTipoEpi(TipoEpiDto tipo) {

        boolean sucesso = tipoEpiService.cadastrarTipoEpi(tipo);

        if (sucesso) {
            return "redirect:/epis/tipo/lista";
        } else {
            return "redirect:/epis/tipo/lista?erro";
        }
    }

    @DeleteMapping("/epis/tipo/{id}")
    public ResponseEntity<String> excluirTipoEpi(@PathVariable Long id) {

        boolean sucesso = tipoEpiService.excluirTipoEpi(id);

        if (sucesso) {
            return ResponseEntity.ok("Tipo de equipamento excluído com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir tipo de equipamento.");

    }

    @PostMapping("/epis/tipo/atualizar/{codigo}")
    public String atualizarTipoEpi(TipoEpiDto tipo, @PathVariable Long codigo) {

        boolean sucesso = tipoEpiService.atualizarTipoEpi(tipo, codigo);

        if (sucesso) {
            return "redirect:/epis/tipo/lista";
        } else {
            return "redirect:/epis/tipo/lista?erro";
        }

    }

    @PostMapping("/epis/tipo/desativar/{id}")
    public String desativarTipoEpi(@PathVariable Long id) {

        boolean sucesso = tipoEpiService.desativarTipoEpi(id);

        if(sucesso) {
            return "redirect:/epis/tipo/lista";
        } else {
            return "redirect:/epis/tipos/lista?erro";
        }

    }

    @PostMapping("/epis/tipo/ativar/{id}")
    public String ativarTipoEpi(@PathVariable Long id) {

        boolean sucesso = tipoEpiService.ativarTipoEpi(id);

        if(sucesso) {
            return "redirect:/epis/tipo/lista";
        } else {
            return "redirect:/epis/tipos/lista?erro";
        }

    }

}
