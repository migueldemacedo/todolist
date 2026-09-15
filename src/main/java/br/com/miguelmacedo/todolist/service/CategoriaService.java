package br.com.miguelmacedo.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.miguelmacedo.todolist.dto.CategoriaRequest;
import br.com.miguelmacedo.todolist.dto.CategoriaResponse;
import br.com.miguelmacedo.todolist.entity.Categoria;
import br.com.miguelmacedo.todolist.mapper.CategoriaMapper;
import br.com.miguelmacedo.todolist.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaResponse criar(CategoriaRequest request) {
        Categoria categoria = categoriaMapper.toEntity(request);
        return categoriaMapper.toResponse(categoriaRepository.save(categoria));
    }

    public List<CategoriaResponse> listarTodas() {
        return categoriaRepository.findAll().stream()
            .map(categoriaMapper::toResponse)
            .toList();
    }

    public void deletarPorId(Long id) {
        categoriaRepository.deleteById(id);;
    }

    public CategoriaResponse buscarPorId(Long id) {
        return categoriaMapper.toResponse(buscarEntidade(id));
    }

    public CategoriaResponse atualizar(Long id, CategoriaRequest request) {
        Categoria categoria= categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoria.setNome(categoria.getNome());
        return categoriaMapper.toResponse(categoriaRepository.save(categoria));
    }

    private Categoria buscarEntidade(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }
}
