package br.com.miguelmacedo.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequest(@NotBlank(message = "O nome é obrigatório") @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres") String nome) {

}
