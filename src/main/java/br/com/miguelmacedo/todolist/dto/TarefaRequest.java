package br.com.miguelmacedo.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TarefaRequest(@NotBlank(message = "O título é obrigatório") @Size(min = 3, max = 150, message = "O título deve ter entre {min} e {max} caracteres") String titulo, boolean concluida) {

}
