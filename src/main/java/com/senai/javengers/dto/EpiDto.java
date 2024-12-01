package com.senai.javengers.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EpiDto {
    
    private Long codigo;
    
    @NotNull
    private String descricao;
    
    @NotNull
    private String tipo;

    @NotNull
    private Long tipoId;

    private String imagem;
}
