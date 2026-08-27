package br.com.miguelmacedo.todolist.dto;

public record TarefaRequestDTO(String titulo, boolean concluida, Long categoriaId) {

}
