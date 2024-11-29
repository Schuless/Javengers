package com.senai.javengers.repositorio;

import com.senai.javengers.model.CargoModel;
import com.senai.javengers.model.EpiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EpiRepositorio extends JpaRepository<EpiModel,Long> {
    Optional<EpiModel> findByDescricao(String descricao);
}
