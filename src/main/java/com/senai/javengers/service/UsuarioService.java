package com.senai.javengers.service;

import com.senai.javengers.dto.LoginDto;
import com.senai.javengers.model.UsuarioModel;
import com.senai.javengers.repositorio.UsuarioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepositorio repositorio;
        
    public boolean validarLogin(LoginDto loginDto){
              
        Optional<UsuarioModel> optionalUsuario = repositorio.findByEmail(loginDto.getEmail());
        
        //--Verifica se o email do usuario existe, senão, retorna falso
        if (!optionalUsuario.isPresent()){
            return false;
        }
        
        //--Caso o email exista, verifica a senha, em caso de incorreta, retorna falso
        if (!optionalUsuario.get().getSenha().equals(loginDto.getSenha())){           
            return false;
        }        
        
        return true;
    }
}
