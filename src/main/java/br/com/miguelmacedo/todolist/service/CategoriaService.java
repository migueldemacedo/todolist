package br.com.miguelmacedo.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.miguelmacedo.todolist.entity.Categoria;
import br.com.miguelmacedo.todolist.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria criar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public void deletarPorId(Long id) {
        categoriaRepository.deleteById(id);;
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public Categoria atualizar(Long id, Categoria categoria) {
        Categoria categoriaNova = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoriaNova.setTitulo(categoria.getTitulo());
        return categoriaRepository.save(categoriaNova);
    }
}
