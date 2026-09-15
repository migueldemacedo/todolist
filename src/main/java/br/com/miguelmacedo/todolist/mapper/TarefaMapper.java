package br.com.miguelmacedo.todolist.mapper;

import org.springframework.stereotype.Component;

import br.com.miguelmacedo.todolist.dto.TarefaRequest;
import br.com.miguelmacedo.todolist.dto.TarefaResponse;
import br.com.miguelmacedo.todolist.entity.Categoria;
import br.com.miguelmacedo.todolist.entity.Tarefa;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class TarefaMapper {

    private final CategoriaMapper categoriaMapper;

    public Tarefa toEntity(TarefaRequest request, Categoria categoria) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(request.titulo());
        tarefa.setStatus(request.status());
        tarefa.setCategoria(categoria);
        return tarefa;
    }

    public void copyToEntity(TarefaRequest request, Tarefa destino, Categoria categoria) {
        destino.setTitulo(request.titulo());
        destino.setStatus(request.status());
        destino.setCategoria(categoria);
    }

    public TarefaResponse toResponse(Tarefa tarefa) {

        return new TarefaResponse(
            tarefa.getId(), 
            tarefa.getTitulo(), 
            tarefa.getStatus(), 
            categoriaMapper.toResponse(tarefa.getCategoria()));
    }
}
