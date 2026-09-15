package br.com.miguelmacedo.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.miguelmacedo.todolist.dto.TarefaRequest;
import br.com.miguelmacedo.todolist.dto.TarefaResponse;
import br.com.miguelmacedo.todolist.entity.Categoria;
import br.com.miguelmacedo.todolist.entity.StatusTarefa;
import br.com.miguelmacedo.todolist.entity.Tarefa;
import br.com.miguelmacedo.todolist.exception.TarefaNaoEncontradaException;
import br.com.miguelmacedo.todolist.mapper.TarefaMapper;
import br.com.miguelmacedo.todolist.repository.CategoriaRepository;
import br.com.miguelmacedo.todolist.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TarefaService {

    private final CategoriaRepository categoriaRepository;
    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    @Transactional
    public TarefaResponse criar(TarefaRequest request) {
        Categoria categoria = buscarCategoria(request.categoriaId());
        Tarefa nova = tarefaMapper.toEntity(request, categoria);
        if (request.status() == null) {
            nova.setStatus(StatusTarefa.PENDENTE);
        }
        return tarefaMapper.toResponse(tarefaRepository.save(nova));
    }

    public List<TarefaResponse> listarTodas(StatusTarefa status, Long categoriaId, String titulo) {
        return tarefaRepository.buscarComFiltros(status, categoriaId, titulo).stream()
            .map(tarefaMapper::toResponse)
            .toList();
    }

    @Transactional 
    public void deletarPorId(Long id) {
        tarefaRepository.deleteById(id);;
    }

    public TarefaResponse buscarPorId(Long id) {
        return tarefaMapper.toResponse(buscarEntidade(id));
    }

    @Transactional 
    public TarefaResponse atualizar(Long id, TarefaRequest request) {
        Categoria categoria = buscarCategoria(request.categoriaId());
        Tarefa tarefa = buscarEntidade(id);
        tarefaMapper.copyToEntity(request, tarefa, categoria);

        return tarefaMapper.toResponse(tarefaRepository.save(tarefa));
    }

    private Tarefa buscarEntidade(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new TarefaNaoEncontradaException(id));
    }

    private Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }
}
