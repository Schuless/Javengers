package com.senai.javengers.service;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.dto.EpiDto;
import com.senai.javengers.model.ColaboradorModel;
import com.senai.javengers.model.EmprestimoModel;
import com.senai.javengers.model.EpiModel;
import com.senai.javengers.repositorio.EpiRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EpiService {

    @Autowired
    EpiRepositorio epiRepositorio;

    public EpiDto obterEpi(Long codigo) {

        Optional<EpiModel> optionalEpi = epiRepositorio.findById(codigo);

        EpiDto epi = new EpiDto();

        if (optionalEpi.isEmpty()) {
            epi.setCodigo(0L);
            return epi;
        }
        epi.setCodigo(optionalEpi.get().getCodigo());
        epi.setDescricao(optionalEpi.get().getDescricao());
        epi.setTipo(optionalEpi.get().getTipo());
        return epi;
    }

    public List<EpiModel> obterListaEpis() {

        List<EpiModel> lista = epiRepositorio.findAll();

        return lista;
    }

    public boolean excluirEpi(Long id) {

        Optional<EpiModel> optionalEpi = epiRepositorio.findById(id);

        if (optionalEpi.isEmpty()) {
            return false;
        }

        epiRepositorio.delete(optionalEpi.get());

        return true;
    }

    public boolean cadastrarEpi(EpiDto epi) {

        Optional<EpiModel> optionalEpiModel = epiRepositorio.findByDescricao(epi.getDescricao());

        if (optionalEpiModel.isPresent()) {
            return false;
        }

        EpiModel model = new EpiModel();
        model.setCodigo(epi.getCodigo());
        model.setDescricao(epi.getDescricao());
        model.setTipo(epi.getTipo());

        epiRepositorio.save(model);

        return true;

    }

    public boolean atualizarEpi(EpiDto epi, Long id) {

        Optional<EpiModel> optionalEpi = epiRepositorio.findById(id);

        if (optionalEpi.isEmpty()) {
            return false;
        }

        EpiModel model = optionalEpi.get();
        model.setDescricao(epi.getDescricao());
        model.setTipo(epi.getTipo());

        epiRepositorio.save(model);
        return true;


    }
}
