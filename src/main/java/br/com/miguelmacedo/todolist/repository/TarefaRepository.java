package br.com.miguelmacedo.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.miguelmacedo.todolist.entity.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

}
