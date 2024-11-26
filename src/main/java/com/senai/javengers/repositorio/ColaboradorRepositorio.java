/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.senai.javengers.repositorio;

import com.senai.javengers.model.ColaboradorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ColaboradorRepositorio extends JpaRepository<ColaboradorModel,Long> {
    Optional<ColaboradorModel> findByEmail(String email);
}
