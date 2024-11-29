package com.senai.javengers.service;

import com.senai.javengers.dto.EmprestimoDto;
import com.senai.javengers.model.ColaboradorModel;
import com.senai.javengers.model.EmprestimoModel;
import com.senai.javengers.model.EpiModel;
import com.senai.javengers.repositorio.ColaboradorRepositorio;
import com.senai.javengers.repositorio.EmprestimoRepositorio;
import com.senai.javengers.repositorio.EpiRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    EmprestimoRepositorio emprestimoRepositorio;
    @Autowired
    private ColaboradorRepositorio colaboradorRepositorio;
    @Autowired
    private EpiRepositorio epiRepositorio;

    public EmprestimoDto obterEmprestimo(Long codigo) {
        Optional<EmprestimoModel> optionalEmprestimo = emprestimoRepositorio.findById(codigo);

        if (optionalEmprestimo.isEmpty())
            return null;

        Optional<ColaboradorModel> colaborador = colaboradorRepositorio.findById(optionalEmprestimo.get().getColaboradorId());
        Optional<EpiModel> epi = epiRepositorio.findById(optionalEmprestimo.get().getEpiId());

        if (colaborador.isEmpty() || epi.isEmpty())
            return null;

        EmprestimoDto emprestimos = new EmprestimoDto();

        emprestimos.setCodigo(optionalEmprestimo.get().getCodigo());
        emprestimos.setColaboradorNome(colaborador.get().getNome());
        emprestimos.setEpiNome(epi.get().getDescricao());
        emprestimos.setData(optionalEmprestimo.get().getData());
        emprestimos.setDevolucao(optionalEmprestimo.get().getDevolucao());
        emprestimos.setStatus(optionalEmprestimo.get().getStatus());

        return emprestimos;

    }

    public List<EmprestimoDto> obterListaEmprestimos() {

        List<EmprestimoModel> emprestimos = emprestimoRepositorio.findAll();
        List<EmprestimoDto> emprestimoDtos = new ArrayList<>();

        for (EmprestimoModel emprestimo : emprestimos) {
            EmprestimoDto dto = new EmprestimoDto();
            Optional<ColaboradorModel> colaborador = colaboradorRepositorio.findById(emprestimo.getColaboradorId());
            Optional<EpiModel> epi = epiRepositorio.findById(emprestimo.getEpiId());

            // Atribuir valores padrão ao invés de interromper a execução
            if (colaborador.isPresent()) {
                dto.setColaboradorId(colaborador.get().getCodigo());
                dto.setColaboradorNome(colaborador.get().getNome());
            } else {
                dto.setColaboradorId(null);  // Ou algum valor padrão como -1
                dto.setColaboradorNome("Colaborador não encontrado");
            }

            if (epi.isPresent()) {
                dto.setEpiId(epi.get().getCodigo());
                dto.setEpiNome(epi.get().getDescricao());
            } else {
                dto.setEpiId(null);  // Ou algum valor padrão como -1
                dto.setEpiNome("EPI não encontrado");
            }
            dto.setCodigo(emprestimo.getCodigo());
            dto.setColaboradorId(colaborador.get().getCodigo());
            dto.setColaboradorNome(colaborador.get().getNome());
            dto.setEpiId(epi.get().getCodigo());
            dto.setEpiNome(epi.get().getDescricao());
            dto.setData(emprestimo.getData());
            dto.setDevolucao(emprestimo.getDevolucao());
            dto.setStatus(emprestimo.getStatus());

            emprestimoDtos.add(dto);
        }
        return emprestimoDtos;
    }

    public boolean excluirEmprestimo(Long id) {

        Optional<EmprestimoModel> optionalEmprestimo = emprestimoRepositorio.findById(id);

        if (optionalEmprestimo.isEmpty()){
            return false;
        }

        emprestimoRepositorio.delete(optionalEmprestimo.get());

        return true;
    }

    public boolean finalizarEmprestimo(Long id) {

        Optional<EmprestimoModel> optionalEmprestimo = emprestimoRepositorio.findById(id);

        if (optionalEmprestimo.isEmpty()){
            return false;
        }

        EmprestimoModel model = optionalEmprestimo.get();

        model.setDevolucao(LocalDate.now());
        model.setStatus("Finalizado");
        emprestimoRepositorio.save(model);

        return true;
    }

    public boolean cadastrarEmprestimo(EmprestimoDto emprestimo) {

        Optional<ColaboradorModel> optionalColaborador = colaboradorRepositorio.findById(emprestimo.getColaboradorId());
        Optional<EpiModel> optionalEpi = epiRepositorio.findById(emprestimo.getEpiId());

        if (optionalColaborador.isEmpty() || optionalEpi.isEmpty())
            return false;

        EmprestimoModel model = new EmprestimoModel();
        model.setCodigo(emprestimo.getCodigo());
        model.setEpiId(optionalEpi.get().getCodigo());
        model.setColaboradorId(optionalColaborador.get().getCodigo());
        model.setData(LocalDate.now());
        model.setStatus("Ativo");

        emprestimoRepositorio.save(model);

        return true;

    }
}
