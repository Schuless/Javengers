package com.senai.javengers.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import lombok.Data;

@Data
//No literal, ele fala que o email nao pode ser nulo, o colaborador nao pode ter mais de 100 anos e que colaboradores nao podem ter o mesmo email (validar pelo service)
public class ColaboradorDto {
    
    private Long codigo;
    
    private String nome;
    
    private String email;
    
    @NotNull
    private long cargoId;
    
    private String cargoNome;

    private LocalDate nascimento;

}
