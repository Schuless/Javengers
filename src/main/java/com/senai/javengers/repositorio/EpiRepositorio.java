package com.senai.javengers.repositorio;

import com.senai.javengers.model.EpiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpiRepositorio extends JpaRepository<EpiModel,Long> {

}
