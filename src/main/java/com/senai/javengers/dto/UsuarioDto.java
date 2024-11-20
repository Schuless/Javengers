package com.senai.javengers.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class UsuarioDto {

    private long codigo;
    
    @NotNull
    private String nome;
    
    @NotNull
    private String email;
    
    @NotNull
    @Size(min = 5)
    private String senha;
    
}

