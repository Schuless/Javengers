package com.senai.javengers.repositorio;

import com.senai.javengers.model.EmprestimoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepositorio extends JpaRepository<EmprestimoModel,Long> {

}
