package com.senai.javengers.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Base64;

@Entity
@Table(name = "epis")
@Data
public class EpiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "tipoId")
    private Long tipoId;

    @Lob
    @Column(name = "img", columnDefinition = "LONGTEXT")
    private String imagem;
    
}
