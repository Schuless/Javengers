package com.senai.javengers.service;

import com.senai.javengers.dto.CargoDto;
import com.senai.javengers.model.CargoModel;
import com.senai.javengers.repositorio.CargoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CargoService {

    @Autowired
    CargoRepositorio cargoRepositorio;

    public CargoDto obterCargo(Long codigo) {

        Optional<CargoModel> optionalCargo = cargoRepositorio.findById(codigo);

        CargoDto cargo = new CargoDto();

        if (optionalCargo.isEmpty()) {
            cargo.setCodigo(0L);
            return cargo;
        }

        return cargo;
    }
}