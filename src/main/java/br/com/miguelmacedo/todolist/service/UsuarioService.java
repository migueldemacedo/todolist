package br.com.miguelmacedo.todolist.service;

import org.springframework.stereotype.Service;

import br.com.miguelmacedo.todolist.dto.UsuarioRequest;
import br.com.miguelmacedo.todolist.dto.UsuarioResponse;
import br.com.miguelmacedo.todolist.entity.Usuario;
import br.com.miguelmacedo.todolist.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponse criar(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        Usuario novo = usuarioRepository.save(usuario);
        return new UsuarioResponse(novo.getId(), novo.getEmail());
    }
}
