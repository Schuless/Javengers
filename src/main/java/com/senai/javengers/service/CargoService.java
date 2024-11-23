package com.senai.javengers.service;

import com.senai.javengers.dto.CargoDto;
import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.model.CargoModel;
import com.senai.javengers.model.ColaboradorModel;
import com.senai.javengers.repositorio.CargoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    public List<CargoModel> obterListaCargos() {

        List<CargoModel> lista = cargoRepositorio.findAll();

        return lista;
    }

    public boolean excluirCargo(Long id) {

        Optional<CargoModel> optionalCargo = cargoRepositorio.findById(id);

        if (optionalCargo.isEmpty()){
            return false;
        }

        cargoRepositorio.delete(optionalCargo.get());

        return true;
    }

    public boolean cadastrarCargo(CargoDto cargo) {

        Optional<CargoModel> optionalCargo = cargoRepositorio.findById(cargo.getCodigo());
        if (!optionalCargo.isPresent()){
            return false;
        }

        CargoModel model = new CargoModel();
        model.setNome(cargo.getNome());

        cargoRepositorio.save(model);

        return true;

    }

    public boolean atualizarCargo(ColaboradorDto colaborador) {

        Optional<CargoModel> optionalCargo = cargoRepositorio.findById(colaborador.getCodigo());

        CargoModel model = new CargoModel();

        if (optionalCargo.isPresent()){
            model.setNome(colaborador.getNome());

            cargoRepositorio.save(model);
            return true;
        }

        return false;

    }

}