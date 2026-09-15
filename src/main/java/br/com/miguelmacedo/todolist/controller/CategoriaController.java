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

import br.com.miguelmacedo.todolist.dto.CategoriaRequest;
import br.com.miguelmacedo.todolist.dto.CategoriaResponse;
import br.com.miguelmacedo.todolist.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponse> criar(@Valid @RequestBody CategoriaRequest dto) {
        CategoriaResponse t = categoriaService.criar(dto);

        return ResponseEntity.created(null).body(t);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarTodas() {
        List<CategoriaResponse> categorias = categoriaService.listarTodas();

        return ResponseEntity.ok().body(categorias);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequest categoria) {
        CategoriaResponse t = categoriaService.atualizar(id, categoria);

        return ResponseEntity.ok().body(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPorId(@PathVariable Long id) {
        categoriaService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long id) {
        CategoriaResponse categoria = categoriaService.buscarPorId(id);

        return ResponseEntity.ok().body(categoria);
    }
}
