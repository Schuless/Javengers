package com.senai.javengers.repositorio;

import com.senai.javengers.model.UsuarioModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioModel, Long> {

    public Optional<UsuarioModel> findByEmail(String email);
}
