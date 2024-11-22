package com.senai.javengers.controller;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.dto.MensagemDto;
import com.senai.javengers.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/colaborador")
@Controller
public class ColaboradorController {

    @Autowired
    ColaboradorService colaboradorService;
    @PostMapping("/cadastrar")
    public String cadastrarCargo(ColaboradorDto colaborador) {

        boolean sucesso = colaboradorService.cadastrarColaborador(colaborador);

        if(sucesso) {
            return "redirect:colaborador";
        } else {
            return "redirect:colaborador?erro";
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirColaborador(@PathVariable Long id){

        boolean sucesso = colaboradorService.excluirColaborador(id);

        if (sucesso){
            return ResponseEntity.ok("Colaborador excluído com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir Colaborador.");

    }

    @PostMapping("/atualizar")
    public String atualizarCargo(@PathVariable ColaboradorDto colaborador) {

        boolean sucesso = colaboradorService.atualizarColaborador(colaborador);

        if(sucesso) {
            return "redirect:colaborador";
        } else {
            return "redirect:colaborador?erro";
        }

    }

    @GetMapping("/visualizar/{codigo}")
    public String visualizarCargo(Model model, @PathVariable Long codigo) {

        ColaboradorDto colaborador = colaboradorService.obterColaborador(codigo);
        model.addAttribute("colaborador", colaborador);

        if (colaborador.getCodigo() > 0){
            return "atualizarcontato";
        }

        return "redirect:/listacontato";
    }

}
