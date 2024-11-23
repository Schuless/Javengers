package com.senai.javengers.service;

import com.senai.javengers.dto.EpiDto;
import com.senai.javengers.dto.LoginDto;
import com.senai.javengers.dto.UsuarioDto;
import com.senai.javengers.model.EpiModel;
import com.senai.javengers.model.UsuarioModel;
import com.senai.javengers.repositorio.UsuarioRepositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public boolean validarLogin(LoginDto loginDto) {

        Optional<UsuarioModel> optionalUsuario = usuarioRepositorio.findByEmail(loginDto.getEmail());

        //--Verifica se o email do usuario existe, senão, retorna falso
        if (!optionalUsuario.isPresent()) {
            return false;
        }

        //--Caso o email exista, verifica a senha, em caso de incorreta, retorna falso
        if (!optionalUsuario.get().getSenha().equals(loginDto.getSenha())) {
            return false;
        }

        return true;
    }
    public UsuarioDto obterUsuario(Long codigo) {

        Optional<UsuarioModel> optionalUsuario= usuarioRepositorio.findById(codigo);

        UsuarioDto usuario = new UsuarioDto();

        if (optionalUsuario.isEmpty()) {
            usuario.setCodigo(0L);
            return usuario;
        }

        return usuario;
    }

    public List<UsuarioModel> obterListaUsuarios() {

        List<UsuarioModel> lista = usuarioRepositorio.findAll();

        return lista;
    }

    public boolean excluirUsuario(Long id) {

        Optional<UsuarioModel> optionalUsuario = usuarioRepositorio.findById(id);

        if (optionalUsuario.isEmpty()) {
            return false;
        }

        usuarioRepositorio.delete(optionalUsuario.get());

        return true;
    }

    public boolean cadastrarUsuario(UsuarioDto usuario) {

        Optional<UsuarioModel> optionalUsuario = usuarioRepositorio.findByEmail(usuario.getEmail());

        if (optionalUsuario.isEmpty()) {
            return false;
        }

        UsuarioModel model = new UsuarioModel();
        model.setNome(usuario.getNome());
        model.setEmail(usuario.getSenha());
        model.setSenha(usuario.getSenha());

        usuarioRepositorio.save(model);

        return true;

    }

    public boolean atualizarUsuario(UsuarioDto usuario, Long id) {

        Optional<UsuarioModel> optionalUsuario = usuarioRepositorio.findById(id);

        UsuarioModel model = new UsuarioModel();

        if (optionalUsuario.isPresent()) {
            model.setNome(usuario.getNome());
            model.setEmail(usuario.getEmail());
            //verifica se a senha nao está vindo vazia ou sem alteracoes
            if (!usuario.getSenha().isEmpty()) {
               model.setSenha(usuario.getSenha());
            }
            usuarioRepositorio.save(model);
            return true;
        }

        return false;

    }
}
