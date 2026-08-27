package br.com.miguelmacedo.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.miguelmacedo.todolist.dto.CategoriaResponseDTO;
import br.com.miguelmacedo.todolist.dto.TarefaRequestDTO;
import br.com.miguelmacedo.todolist.dto.TarefaResponseDTO;
import br.com.miguelmacedo.todolist.entity.Categoria;
import br.com.miguelmacedo.todolist.entity.Tarefa;
import br.com.miguelmacedo.todolist.repository.CategoriaRepository;
import br.com.miguelmacedo.todolist.repository.TarefaRepository;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final CategoriaRepository categoriaRepository;

    public TarefaService(TarefaRepository tarefaRepository, CategoriaRepository categoriaRepository) {
        this.tarefaRepository = tarefaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public TarefaResponseDTO criar(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        Categoria categoria;

        tarefa.setTitulo(dto.titulo());
        tarefa.setConcluida(dto.concluida());

        if (dto.categoriaId() != null) {
            categoria = categoriaRepository.findById(dto.categoriaId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
            tarefa.setCategoria(categoria);
        }

        tarefa = tarefaRepository.save(tarefa);
        categoria = tarefa.getCategoria();

        CategoriaResponseDTO categoriaDTO = new CategoriaResponseDTO(categoria.getId(), categoria.getTitulo());

        return new TarefaResponseDTO(
            tarefa.getId(),
            tarefa.getTitulo(),
            tarefa.getConcluida(),
            categoriaDTO
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

        if (tarefa.getCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(tarefa.getCategoria().getId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
            tarefaNova.setCategoria(categoria);
        }

        return tarefaRepository.save(tarefaNova);
    }
}
