package com.senai.javengers.service;

import com.senai.javengers.dto.CargoDto;
import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.model.CargoModel;
import com.senai.javengers.model.ColaboradorModel;
import com.senai.javengers.model.UsuarioModel;
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
        cargo.setCodigo(optionalCargo.get().getCodigo());
        cargo.setNome(optionalCargo.get().getNome());
        return cargo;
    }

    public List<CargoModel> obterListaCargos() {

        return cargoRepositorio.findAll();
    }

    public List<CargoModel> obterListaCargosAtivos() {

        return cargoRepositorio.findByAtivoTrue();
    }

    public boolean excluirCargo(Long id) {

        Optional<CargoModel> optionalCargo = cargoRepositorio.findById(id);

        if (optionalCargo.isEmpty()) {
            return false;
        }

        cargoRepositorio.delete(optionalCargo.get());

        return true;
    }

    public boolean cadastrarCargo(CargoDto cargo) {

        Optional<CargoModel> optionalCargo = cargoRepositorio.findByNome(cargo.getNome());

        if (optionalCargo.isPresent()) {
            return false;
        }

        CargoModel model = new CargoModel();
        model.setNome(cargo.getNome());
        model.setCodigo(cargo.getCodigo());
        model.setAtivo(true);
        cargoRepositorio.save(model);

        return true;

    }

    public boolean atualizarCargo(CargoDto cargo, Long codigo) {

        Optional<CargoModel> optionalCargo = cargoRepositorio.findById(codigo);

        if (optionalCargo.isEmpty()) {
            return false;
        }

        CargoModel model = optionalCargo.get();
        model.setNome(cargo.getNome());
        model.setCodigo(cargo.getCodigo());
        cargoRepositorio.save(model);
        return true;


    }

}