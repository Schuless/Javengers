package com.senai.javengers.repositorio;

import com.senai.javengers.model.CargoModel;
import com.senai.javengers.model.ColaboradorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepositorio extends JpaRepository<CargoModel, Long> {
    List<CargoModel> findByAtivoTrue();
    Optional<CargoModel> findById(long id);
    Optional<CargoModel>findByNome(String nome);
}
