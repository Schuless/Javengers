package com.senai.javengers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FuncaoDto {
    
    private Long codigo;
    
    @NotNull
    private String nome;
    
    private boolean ativo; 
}
