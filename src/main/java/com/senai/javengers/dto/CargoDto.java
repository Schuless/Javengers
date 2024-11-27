package com.senai.javengers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CargoDto {
    
    private Long codigo;
    
    @NotNull
    private String nome;
    
    private boolean ativo;
}
