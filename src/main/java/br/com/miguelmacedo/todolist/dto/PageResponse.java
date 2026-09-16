package br.com.miguelmacedo.todolist.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public record PageResponse<T>(
    List<T> conteudo,
    int pagina,
    int tamanho,
    long totalElementos,
    int totalPaginas,
    boolean primeira,
    boolean ultima
) {
    public static <E, T> PageResponse<T> de(Page<E> pagina, List<T> conteudo) {
        return new PageResponse<>(
            conteudo,
            pagina.getNumber(),
            pagina.getSize(),
            pagina.getTotalElements(),
            pagina.getTotalPages(),
            pagina.isFirst(),
            pagina.isLast()
        );
    }
}
