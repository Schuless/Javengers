package com.senai.javengers.service;

import com.senai.javengers.dto.ColaboradorDto;
import com.senai.javengers.dto.EmprestimoDto;
import com.senai.javengers.model.ColaboradorModel;
import com.senai.javengers.model.EmprestimoModel;
import com.senai.javengers.repositorio.EmprestimoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public boolean finalizarEmprestimo(Long id) {

        Optional<EmprestimoModel> optionalColaborador = emprestimoRepositorio.findById(id);

        if (optionalColaborador.isEmpty()){
            return false;
        }

        EmprestimoModel model = new EmprestimoModel();

        model.setDevolucao(LocalDate.now());
        model.setStatus("Finalizado");
        emprestimoRepositorio.save(model);

        return true;
    }

    public boolean cadastrarEmprestimo(EmprestimoDto emprestimo) {

        Optional<EmprestimoModel> optionalEmprestimo = emprestimoRepositorio.findById(emprestimo.getCodigo());

        if (optionalEmprestimo.isEmpty()){
            return false;
        }

        EmprestimoModel model = new EmprestimoModel();
        model.setColaboradorModel(emprestimo.getColaboradorModel());
        model.setData(emprestimo.getData());
        model.setEquipamento(emprestimo.getEquipamento());
        model.setCodigo(emprestimo.getCodigo());
        model.setStatus("Aberto");
        //caso nao venha o valor minimo no teste
        //model.setDevolucao(emprestimo.getDevolucao());

        emprestimoRepositorio.save(model);

        return true;

    }

}
