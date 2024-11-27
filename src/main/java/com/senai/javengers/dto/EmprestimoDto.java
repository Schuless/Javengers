package com.senai.javengers.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import lombok.Data;

@Data
public class EmprestimoDto {

    private Long codigo;

    @NotNull
    //campo tem que ser obrigatorio (required) no html
    private Long colaboradorId;
    @NotNull
    //campo tem que ser obrigatorio (required) no html
    private Long epiId;

    //campo deve ser preenchido automaticamente com a data atual (Date.now)
    private LocalDate data;

    // campo devolução deve armazenar a data de devolução e não deve ser preenchido ao realizar um registro de empréstimo
    private LocalDate devolucao;

}
