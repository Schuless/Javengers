package com.senai.javengers.service;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.model.ColaboradorModel;
import com.senai.javengers.repositorio.ColaboradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            colaborador.setCodigo(0L);
            return colaborador;
        }

        return colaborador;
    }

    public List<ColaboradorModel> obterListaColaboradores() {

        List<ColaboradorModel> lista = colaboradorRepositorio.findAll();

        return lista;
    }

}
