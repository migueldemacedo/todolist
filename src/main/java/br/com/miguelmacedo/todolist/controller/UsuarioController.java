package br.com.miguelmacedo.todolist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelmacedo.todolist.dto.UsuarioRequest;
import br.com.miguelmacedo.todolist.dto.UsuarioResponse;
import br.com.miguelmacedo.todolist.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/usuarios")
@RequiredArgsConstructor 
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.criar(request);

        return ResponseEntity.created(null).body(response);
    }
}
