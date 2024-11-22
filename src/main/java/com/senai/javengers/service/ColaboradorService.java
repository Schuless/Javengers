package com.senai.javengers.service;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.model.ColaboradorModel;
import com.senai.javengers.repositorio.ColaboradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    @Autowired
    ColaboradorRepositorio colaboradorRepositorio;

    public ColaboradorDto obterColaborador(Long codigo) {

        Optional<ColaboradorModel> optionalColaborador = colaboradorRepositorio.findById(codigo);

        ColaboradorDto colaborador = new ColaboradorDto();

        if (optionalColaborador.isEmpty()) {
            colaborador.setCodigo(codigo);
            return colaborador;
        }

        return colaborador;
    }

    public List<ColaboradorModel> obterListaColaboradores() {

        List<ColaboradorModel> lista = colaboradorRepositorio.findAll();

        return lista;
    }

    public boolean excluirColaborador(Long id) {

        Optional<ColaboradorModel> optionalColaborador = colaboradorRepositorio.findById(id);

        if (optionalColaborador.isEmpty()){
            return false;
        }

        colaboradorRepositorio.delete(optionalColaborador.get());

        return true;
    }

    public boolean cadastrarColaborador(ColaboradorDto colaborador) {

        Optional<ColaboradorModel> optionalColaborador = colaboradorRepositorio.findByEmail(colaborador.getEmail());
        Optional<ColaboradorModel> optionalCargo = colaboradorRepositorio.findByCargo(colaborador.getCargo());
        if (!optionalColaborador.isPresent()){
            return false;
        }

        if (optionalCargo.isEmpty()){
            return false;
        }
        //PRECISA CRIAR VALIDAÇÃO PELA IDADE
        //if (optionalIdade.);

        ColaboradorModel model = new ColaboradorModel();
        model.setNome(colaborador.getNome());
        model.setEmail(colaborador.getEmail());
        model.setCargo(colaborador.getCargo());
        model.setNascimento(colaborador.getNascimento());
        model.setDataDeCadastro(LocalDate.now());

        colaboradorRepositorio.save(model);

        return true;

    }

    public boolean atualizarColaborador(ColaboradorDto colaborador) {

        Optional<ColaboradorModel> optionalColaborador = colaboradorRepositorio.findByEmail(colaborador.getEmail());

        ColaboradorModel model = new ColaboradorModel();

        if (optionalColaborador.isPresent()){
            //PRECISA CRIAR VALIDAÇÃO PELA IDADE
            //if (optionalIdade.);
            model.setNome(colaborador.getNome());
            model.setEmail(colaborador.getEmail());
            model.setCargo(colaborador.getCargo());
            model.setNascimento(colaborador.getNascimento());
            model.setDataDeUpdate(LocalDate.now());

            colaboradorRepositorio.save(model);
            return true;
        }

        return false;

    }

}
