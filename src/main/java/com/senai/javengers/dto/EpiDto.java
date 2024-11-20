package com.senai.javengers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EpiDto {
    
    private Long codigo;
    
    @NotNull
    private String descricao;
    
    @NotNull
    private String tipo;
}
