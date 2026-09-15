package br.com.miguelmacedo.todolist.dto;

import br.com.miguelmacedo.todolist.entity.StatusTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TarefaRequest(@NotBlank(message = "O título é obrigatório") @Size(min = 3, max = 150, message = "O título deve ter entre {min} e {max} caracteres") String titulo, StatusTarefa status, @NotNull(message = "A categoria é obrigatório") Long categoriaId) {

}
