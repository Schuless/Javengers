package com.senai.javengers.service;

import com.senai.javengers.dto.EmprestimoDto;
import com.senai.javengers.model.EmprestimoModel;
import com.senai.javengers.repositorio.EmprestimoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    EmprestimoRepositorio emprestimoRepositorio;

    public EmprestimoDto obterEmprestimo(Long codigo) {

        Optional<EmprestimoModel> optionalEmprestimo = emprestimoRepositorio.findById(codigo);

        EmprestimoDto emprestimo = new EmprestimoDto();

        if (optionalEmprestimo.isEmpty()) {
            emprestimo.setCodigo(0L);
            return emprestimo;
        }

        return emprestimo;
    }

    public List<EmprestimoModel> obterListaEmprestimos() {

        List<EmprestimoModel> lista = emprestimoRepositorio.findAll();

        return lista;
    }
}
