package br.com.miguelmacedo.todolist.mapper;

import org.springframework.stereotype.Component;

import br.com.miguelmacedo.todolist.dto.TarefaRequest;
import br.com.miguelmacedo.todolist.dto.TarefaResponse;
import br.com.miguelmacedo.todolist.entity.Tarefa;

@Component
public class TarefaMapper {
    public Tarefa toEntity(TarefaRequest request) {

        return new Tarefa(request.titulo(), request.concluida());
    }

    public void copyToEntity(TarefaRequest request, Tarefa destino) {
        destino.setTitulo(request.titulo());
        destino.setConcluida(request.concluida());
    }

    public TarefaResponse toResponse(Tarefa tarefa) {

        return new TarefaResponse(tarefa.getId(), tarefa.getTitulo(), tarefa.isConcluida());
    }
}
