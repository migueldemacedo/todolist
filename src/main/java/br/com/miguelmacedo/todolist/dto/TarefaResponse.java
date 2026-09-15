package br.com.miguelmacedo.todolist.dto;

import br.com.miguelmacedo.todolist.entity.StatusTarefa;

public record TarefaResponse(Long id, String titulo, StatusTarefa status, CategoriaResponse categoria) {

}
