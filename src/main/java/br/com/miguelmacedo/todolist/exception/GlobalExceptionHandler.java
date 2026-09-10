package br.com.miguelmacedo.todolist.exception;

import java.time.Instant;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.miguelmacedo.todolist.dto.ErroCampo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    public ProblemDetail handleTarefaNaoEncontrada(TarefaNaoEncontradaException ex) {
        log.info("Tarefa não encontrada: {}", ex.getMessage());

        ProblemDetail problema =  ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problema.setTitle("Tarefa não encontrada");
        problema.setProperty("timestamp", Instant.now());

        return problema;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidacao(MethodArgumentNotValidException ex) {

        List<ErroCampo> campos = ex.getBindingResult().getFieldErrors().stream().map(erro -> new ErroCampo(erro.getField(), erro.getDefaultMessage())).toList();

        ProblemDetail problema =  ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "A requisição contém campos inválidos");
        problema.setTitle("Dados inválidos");
        problema.setProperty("timestamp", Instant.now());
        problema.setProperty("fields", campos);

        return problema;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleErroInesperado(Exception ex) {
        log.error("Error inesperado ao processar a requisição", ex);

        ProblemDetail problema =  ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado. Tente novamente mais tarde.");
        problema.setTitle("Erro interno");
        problema.setProperty("timestamp", Instant.now());

        return problema;
    }
}
