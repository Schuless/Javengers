package com.senai.javengers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name = "emprestimos")
@Data
public class EmprestimoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    
    @ManyToOne
    @JoinColumn(name = "colaboradorId")
    private ColaboradorModel colaboradorModel;
    
    @Column(name = "equipamento")
    private String equipamento;
    
    @Column(name = "data")
    private Date data;
    
    
    @Column(name = "devolucao")
    private Date devolucao;
    
}
