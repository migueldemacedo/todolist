package br.com.miguelmacedo.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.miguelmacedo.todolist.dto.TarefaRequest;
import br.com.miguelmacedo.todolist.dto.TarefaResponse;
import br.com.miguelmacedo.todolist.entity.Tarefa;
import br.com.miguelmacedo.todolist.repository.TarefaRepository;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public TarefaResponse criar(TarefaRequest dto) {
        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo(dto.titulo());
        tarefa.setConcluida(dto.concluida());

        tarefa = tarefaRepository.save(tarefa);

        return new TarefaResponse(
            tarefa.getId(),
            tarefa.getTitulo(),
            tarefa.getConcluida()
        );
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
