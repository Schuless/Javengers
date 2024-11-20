package com.senai.javengers.service;

import com.senai.javengers.dto.EpiDto;
import com.senai.javengers.model.EpiModel;
import com.senai.javengers.repositorio.EpiRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return epi;
    }
}
