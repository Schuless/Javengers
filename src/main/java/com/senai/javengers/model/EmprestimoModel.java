package com.senai.javengers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;
import lombok.Data;
import org.springframework.cglib.core.Local;

@Entity
@Table(name = "emprestimos")
@Data
public class EmprestimoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "colaboradorId")
    private Long colaboradorId;
    
    @Column(name = "epiId")
    private Long epiId;
    
    @Column(name = "data")
    private Date data;
    
    @Column(name = "devolucao")
    private LocalDate devolucao;

    @Column(name = "status")
    private String status;
    
}
