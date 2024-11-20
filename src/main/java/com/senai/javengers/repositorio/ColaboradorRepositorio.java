/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.senai.javengers.repositorio;

import com.senai.javengers.model.ColaboradorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColaboradorRepositorio extends JpaRepository<ColaboradorModel,Long> {

    //-- permitir a busca pelo email do colaborador no service
    public Optional<ColaboradorModel> findByEmail(String email);
}
