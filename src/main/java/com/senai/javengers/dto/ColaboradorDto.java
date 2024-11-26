package com.senai.javengers.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

@Data
//No literal, ele fala que o email nao pode ser nulo, o colaborador nao pode ter mais de 100 anos e que colaboradores nao podem ter o mesmo email (validar pelo service)
public class ColaboradorDto {
    
    private Long codigo;
    
    private String nome;
    
    private String email;
    
    @NotNull
    private long cargoId;
    
    private LocalDate nascimento;
    
    //Metodo que eu pequisei para validar uma data, a gente insire uma data para comparar com a data de requisição do usuario, para validar se a idade é menor do que 100 anos
    //public static boolean comparaDatas(String psDate1, String psDate2) throws ParseException{
    //    SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
    //    Date date1 = dateFormat.parse(psDate1);
    //    Date date2 = dateFormat.parse(psDate2);
    //    if(date2.after(date1)) {
    //        return true;
    //    } else {
    //        return false;
    //    }
    //}
    
}
