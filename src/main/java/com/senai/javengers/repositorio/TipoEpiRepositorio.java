package com.senai.javengers.repositorio;

import com.senai.javengers.model.CargoModel;
import com.senai.javengers.model.TipoEpiModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoEpiRepositorio extends JpaRepository<TipoEpiModel, Long> {
    List<TipoEpiModel> findByAtivo(String ativo);
    Optional<TipoEpiModel> findByNome(String nome);
    Optional<TipoEpiModel> findById(Long id);

}
