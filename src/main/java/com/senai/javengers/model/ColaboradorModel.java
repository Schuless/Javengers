package com.senai.javengers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name = "colaboradores")
@Data
public class ColaboradorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "cargo")
    private long cargoId;
    
    @Column(name = "nascimento")
    private LocalDate nascimento;

    @Column(name = "dataDeCadastro")
    private LocalDate dataDeCadastro;

    @Column(name = "dataDeUpdate")
    private LocalDate DataDeUpdate;

            
}
