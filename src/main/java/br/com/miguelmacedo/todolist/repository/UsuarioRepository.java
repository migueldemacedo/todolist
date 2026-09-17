package br.com.miguelmacedo.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.miguelmacedo.todolist.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
