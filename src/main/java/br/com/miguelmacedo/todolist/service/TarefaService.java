package br.com.miguelmacedo.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.miguelmacedo.todolist.dto.TarefaRequest;
import br.com.miguelmacedo.todolist.dto.TarefaResponse;
import br.com.miguelmacedo.todolist.entity.Tarefa;
import br.com.miguelmacedo.todolist.exception.TarefaNaoEncontradaException;
import br.com.miguelmacedo.todolist.mapper.TarefaMapper;
import br.com.miguelmacedo.todolist.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public TarefaResponse criar(TarefaRequest request) {
        Tarefa nova = tarefaMapper.toEntity(request);
        return tarefaMapper.toResponse(tarefaRepository.save(nova));
    }

    public List<TarefaResponse> listarTodas() {
        return tarefaRepository.findAll().stream()
            .map(tarefaMapper::toResponse)
            .toList();
    }

    public void deletarPorId(Long id) {
        tarefaRepository.deleteById(id);;
    }

    public TarefaResponse buscarPorId(Long id) {
        return tarefaMapper.toResponse(buscarEntidade(id));
    }

    public TarefaResponse atualizar(Long id, TarefaRequest request) {
        Tarefa existente = buscarEntidade(id);
        tarefaMapper.copyToEntity(request, existente);

        return tarefaMapper.toResponse(tarefaRepository.save(existente));
    }

    private Tarefa buscarEntidade(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new TarefaNaoEncontradaException(id));
    }
}
