package com.senai.javengers.service;

import com.senai.javengers.dto.EpiDto;
import com.senai.javengers.model.*;
import com.senai.javengers.repositorio.EpiRepositorio;
import com.senai.javengers.repositorio.TipoEpiRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class EpiService {

    @Autowired
    EpiRepositorio epiRepositorio;
    @Autowired
    TipoEpiRepositorio tipoEpiRepositorio;

    public EpiDto obterEpi(Long codigo) {

        Optional<EpiModel> optionalEpi = epiRepositorio.findById(codigo);

        EpiDto epi = new EpiDto();

        if (optionalEpi.isEmpty()) {
            epi.setCodigo(0L);
            return epi;
        }

        Optional<TipoEpiModel> optionalTipo = tipoEpiRepositorio.findById(optionalEpi.get().getTipoId());


        if (optionalTipo.isPresent()) {
            epi.setTipo(optionalTipo.get().getNome());
        }

        epi.setCodigo(optionalEpi.get().getCodigo());
        epi.setDescricao(optionalEpi.get().getDescricao());
        epi.setImagem(optionalEpi.get().getImagem());

        return epi;
    }

    public List<EpiDto> obterListaEpis() {

        List<EpiModel> epiModel = epiRepositorio.findAll();
        List<EpiDto> epiDto = new ArrayList<>();

        for (EpiModel epi : epiModel) {
            EpiDto dto = new EpiDto();
            Optional<TipoEpiModel> tipo = tipoEpiRepositorio.findById(epi.getTipoId());

            if (tipo.isPresent()) {
                dto.setTipo(tipo.get().getNome());
                dto.setTipoId(tipo.get().getCodigo());
            } else {
                dto.setTipo("");
                dto.setTipoId(-1L);
            }
            dto.setCodigo(epi.getCodigo());
            dto.setDescricao(epi.getDescricao());
            epiDto.add(dto);
        }
        return epiDto;
    }

    public List<String> obterListaImagens() {

        List<EpiModel> epiModel = epiRepositorio.findAll();
        List<String> listImg = new ArrayList<>();

        for (EpiModel epi : epiModel) {
            String img = epi.getImagem();

            listImg.add(img);
        }
        return listImg;
    }

    public boolean excluirEpi(Long id) {

        Optional<EpiModel> optionalEpi = epiRepositorio.findById(id);

        if (optionalEpi.isEmpty()) {
            return false;
        }

        epiRepositorio.delete(optionalEpi.get());

        return true;
    }

    public boolean cadastrarEpi(EpiDto epi, MultipartFile file) {

        try {

            byte[] imagemBytes = file.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(imagemBytes);

            Optional<EpiModel> optionalEpiModel = epiRepositorio.findByDescricao(epi.getDescricao());
            Optional<TipoEpiModel> optionalTipo = tipoEpiRepositorio.findById(epi.getTipoId());

            if (optionalEpiModel.isPresent() || optionalTipo.isEmpty()) {
                return false;
            }

            EpiModel model = new EpiModel();
            model.setCodigo(epi.getCodigo());
            model.setDescricao(epi.getDescricao());
            model.setTipoId(epi.getTipoId());
            model.setImagem(base64Image);

            System.out.println("codigo = " + model.getTipoId());
            epiRepositorio.save(model);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean atualizarEpi(EpiDto epi, Long id) {

        Optional<EpiModel> optionalEpi = epiRepositorio.findById(id);

        if (optionalEpi.isEmpty()) {
            return false;
        }

        EpiModel model = optionalEpi.get();
        model.setDescricao(epi.getDescricao());
        model.setTipoId(epi.getTipoId());

        epiRepositorio.save(model);
        return true;


    }
}
