package com.senai.javengers.dto;

import com.senai.javengers.model.ColaboradorModel;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Data;

@Data
public class EmprestimoDto {

    private Long codigo;

    @NotNull
    //campo tem que ser obrigatorio (required) no html
    private ColaboradorModel colaboradorModel;
    @NotNull
    //campo tem que ser obrigatorio (required) no html
    private String equipamento;

    //campo deve ser preenchido automaticamente com a data atual (Date.now)
    private Date data;

    // campo devolução deve armazenar a data de devolução e não deve ser preenchido ao realizar um registro de empréstimo
    private Date devolucao;

    //5 – O sistema não deve permitir registar um empréstimo para um código de colaborador sem cadastro. 
    //6 – O sistema não deve permitir registrar um empréstimo para um código de equipamento sem cadastro. 
    //7 – Na tela de devolução o sistema deverá exibir os dados do empréstimo como ‘somente leitura’ e exibir um botão de ‘confirmar devolução’. 
    //Quando esta ação foi realizada o sistema irá atualizar o registro de empréstimo com a data atual finalizando o empréstimo. 
    //8 – O sistema não deve permitir a atualização de um empréstimo já registrado, apenas a exclusão ou devolução.
}
