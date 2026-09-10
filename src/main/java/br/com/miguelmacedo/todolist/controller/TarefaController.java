package br.com.miguelmacedo.todolist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelmacedo.todolist.dto.TarefaRequest;
import br.com.miguelmacedo.todolist.dto.TarefaResponse;
import br.com.miguelmacedo.todolist.service.TarefaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaResponse> criar(@Valid @RequestBody TarefaRequest dto) {
        TarefaResponse t = tarefaService.criar(dto);

        return ResponseEntity.created(null).body(t);
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> listarTodas() {
        List<TarefaResponse> tarefas = tarefaService.listarTodas();

        return ResponseEntity.ok().body(tarefas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody TarefaRequest tarefa) {
        TarefaResponse t = tarefaService.atualizar(id, tarefa);

        return ResponseEntity.ok().body(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPorId(@PathVariable Long id) {
        tarefaService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> buscarPorId(@PathVariable Long id) {
        TarefaResponse tarefa = tarefaService.buscarPorId(id);

        return ResponseEntity.ok().body(tarefa);
    }
}
