package br.com.miguelmacedo.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.miguelmacedo.todolist.entity.Tarefa;
import br.com.miguelmacedo.todolist.repository.TarefaRepository;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa criar(Tarefa tarefa) {
        tarefa.setConcluida(false);
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public void deletarPorId(Long id) {
        tarefaRepository.deleteById(id);;
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public Tarefa atualizar(Long id, Tarefa tarefa) {
        Tarefa tarefaNova = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefaNova.setTitulo(tarefa.getTitulo());
        tarefaNova.setConcluida(tarefa.getConcluida());
        return tarefaRepository.save(tarefaNova);
    }
}
