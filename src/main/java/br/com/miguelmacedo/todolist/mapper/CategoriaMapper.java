package br.com.miguelmacedo.todolist.mapper;

import org.springframework.stereotype.Component;

import br.com.miguelmacedo.todolist.dto.CategoriaRequest;
import br.com.miguelmacedo.todolist.dto.CategoriaResponse;
import br.com.miguelmacedo.todolist.entity.Categoria;

@Component
public class CategoriaMapper {
    public Categoria toEntity(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.nome());
        return categoria;
    }

    public void copyToEntity(CategoriaRequest request, Categoria destino) {
        destino.setNome(request.nome());
    }

    public CategoriaResponse toResponse(Categoria categoria) {

        return new CategoriaResponse(categoria.getId(), categoria.getNome());
    }
}
