package br.com.miguelmacedo.todolist.dto;

public record TarefaResponseDTO(Long id, String titulo, boolean concluida, CategoriaResponseDTO categoria) {

}
