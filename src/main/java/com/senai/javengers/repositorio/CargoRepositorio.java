package com.senai.javengers.repositorio;

import com.senai.javengers.model.CargoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepositorio extends JpaRepository<CargoModel, Long> {

}
