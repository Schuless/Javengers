package com.senai.javengers.service;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.model.CargoModel;
import com.senai.javengers.model.ColaboradorModel;
import com.senai.javengers.repositorio.CargoRepositorio;
import com.senai.javengers.repositorio.ColaboradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepositorio colaboradorRepositorio;

    @Autowired
    private CargoRepositorio cargoRepositorio;

    public ColaboradorDto obterColaborador(Long codigo) {

        Optional<ColaboradorModel> optionalColaborador = colaboradorRepositorio.findById(codigo);

        ColaboradorDto colaborador = new ColaboradorDto();

        if (optionalColaborador.isEmpty()) {
            colaborador.setCodigo(codigo);
            return colaborador;
        }

        return colaborador;
    }

    public List<ColaboradorDto> obterListaColaboradores() {

        List<ColaboradorModel> colaboradores = colaboradorRepositorio.findAll();
        List<ColaboradorDto> colaboradoresDto = new ArrayList<>();

        for (ColaboradorModel colaborador : colaboradores) {
            ColaboradorDto dto = new ColaboradorDto();
            Optional<CargoModel> cargo = cargoRepositorio.findById(colaborador.getCargoId());

            if (cargo.isEmpty())
                return null;

            dto.setCodigo(colaborador.getCodigo());
            dto.setNome(colaborador.getNome());
            dto.setEmail(colaborador.getEmail());
            dto.setNascimento(colaborador.getNascimento());
            dto.setCargoId(cargo.get().getCodigo());
            dto.setCargoNome(cargo.get().getNome());

            colaboradoresDto.add(dto);
        }
        return colaboradoresDto;
    }

    public boolean excluirColaborador(Long id) {

        Optional<ColaboradorModel> optionalColaborador = colaboradorRepositorio.findById(id);

        if (optionalColaborador.isEmpty()) {
            return false;
        }

        colaboradorRepositorio.delete(optionalColaborador.get());

        return true;
    }

    public boolean cadastrarColaborador(ColaboradorDto colaborador) {

        Optional<ColaboradorModel> optionalColaborador = colaboradorRepositorio.findByEmail(colaborador.getEmail());
        Optional<CargoModel> optionalCargo = cargoRepositorio.findById(colaborador.getCargoId());

        if (optionalColaborador.isPresent() || optionalCargo.isEmpty())
            return false;

        // Adicione a validação pela idade aqui
        // if (optionalIdade...);

        ColaboradorModel model = new ColaboradorModel();
        model.setNome(colaborador.getNome());
        model.setEmail(colaborador.getEmail());
        model.setCargoId(optionalCargo.get().getCodigo()); // Utilize o valor do Optional validado
        model.setNascimento(colaborador.getNascimento());
        model.setDataDeCadastro(LocalDate.now());

        colaboradorRepositorio.save(model);

        return true;
    }

    public boolean atualizarColaborador(ColaboradorDto colaborador, Long id) {

        Optional<ColaboradorModel> optionalColaborador = colaboradorRepositorio.findById(id);

        ColaboradorModel model = new ColaboradorModel();

        if (optionalColaborador.isPresent()) {
            //PRECISA CRIAR VALIDAÇÃO PELA IDADE
            //if (optionalIdade.);
            model.setNome(colaborador.getNome());
            model.setEmail(colaborador.getEmail());
            model.setCargoId(colaborador.getCargoId());
            model.setNascimento(colaborador.getNascimento());
            model.setDataDeUpdate(LocalDate.now());

            colaboradorRepositorio.save(model);
            return true;
        }

        return false;

    }

}
