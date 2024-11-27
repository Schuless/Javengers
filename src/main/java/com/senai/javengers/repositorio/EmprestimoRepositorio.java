package com.senai.javengers.repositorio;

import com.senai.javengers.model.CargoModel;
import com.senai.javengers.model.EmprestimoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmprestimoRepositorio extends JpaRepository<EmprestimoModel,Long> {
    Optional<EmprestimoModel> findByEpiId(Long id);
}
