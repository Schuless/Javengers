package com.senai.javengers.service;


import com.senai.javengers.dto.TipoEpiDto;
import com.senai.javengers.model.*;
import com.senai.javengers.repositorio.TipoEpiRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TipoEpiService {

    @Autowired
    TipoEpiRepositorio tipoEpiRepositorio;

    public TipoEpiDto obterTipoEpi(Long codigo) {

        Optional<TipoEpiModel> optionalTipo = tipoEpiRepositorio.findById(codigo);

        TipoEpiDto cargo = new TipoEpiDto();

        if (optionalTipo.isEmpty()) {
            cargo.setCodigo(0L);
            return cargo;
        }
        cargo.setCodigo(optionalTipo.get().getCodigo());
        cargo.setNome(optionalTipo.get().getNome());
        cargo.setAtivo(optionalTipo.get().getAtivo());
        return cargo;
    }

    public List<TipoEpiModel> obterListaTipoEpi() {

        return tipoEpiRepositorio.findAll();
    }

    public List<TipoEpiModel> obterListaTipoEpiAtivo() {

        return tipoEpiRepositorio.findByAtivo("Ativo");
    }

    public boolean excluirTipoEpi(Long id) {

        Optional<TipoEpiModel> optionalTipo = tipoEpiRepositorio.findById(id);

        if (optionalTipo.isEmpty()) {
            return false;
        }

        tipoEpiRepositorio.delete(optionalTipo.get());

        return true;
    }

    public boolean cadastrarTipoEpi(TipoEpiDto tipo) {

        Optional<TipoEpiModel> optionalTipo = tipoEpiRepositorio.findByNome(tipo.getNome());

        if (optionalTipo.isPresent()) {
            return false;
        }

        TipoEpiModel model = new TipoEpiModel();
        model.setCodigo(tipo.getCodigo());
        model.setNome(tipo.getNome());
        model.setAtivo("Ativo");
        tipoEpiRepositorio.save(model);

        return true;

    }

    public boolean atualizarTipoEpi(TipoEpiDto tipo, Long codigo) {

        Optional<TipoEpiModel> optionalTipo = tipoEpiRepositorio.findById(codigo);

        if (optionalTipo.isEmpty()) {
            return false;
        }

        TipoEpiModel model = optionalTipo.get();
        model.setNome(tipo.getNome());
        model.setCodigo(tipo.getCodigo());
        tipoEpiRepositorio.save(model);
        return true;


    }

    public boolean desativarTipoEpi(Long id) {

        Optional<TipoEpiModel> optionalTipo = tipoEpiRepositorio.findById(id);

        if (optionalTipo.isEmpty()){
            return false;
        }

        TipoEpiModel model = optionalTipo.get();

        model.setAtivo("Desativado");
        tipoEpiRepositorio.save(model);

        return true;
    }

    public boolean ativarTipoEpi(Long id) {

        Optional<TipoEpiModel> optionalTipo = tipoEpiRepositorio.findById(id);

        if (optionalTipo.isEmpty()){
            return false;
        }

        TipoEpiModel model = optionalTipo.get();

        model.setAtivo("Ativo");
        tipoEpiRepositorio.save(model);

        return true;
    }

}