package br.com.miguelmacedo.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.miguelmacedo.todolist.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
