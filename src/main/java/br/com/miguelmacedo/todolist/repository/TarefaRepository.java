package br.com.miguelmacedo.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.miguelmacedo.todolist.entity.StatusTarefa;
import br.com.miguelmacedo.todolist.entity.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

    @Query("""
            select t from Tarefa t
            where (:status is null or t.status = :status)
            and (:categoriaId is null or t.categoria.id = :categoriaId)
            and (:titulo is null or lower(t.titulo) like lower(concat('%', :titulo, '%')))
            """)
    @EntityGraph(attributePaths = "categoria")
    List<Tarefa> buscarComFiltros(
        @Param("status") StatusTarefa status,
        @Param("categoriaId") Long categoriaId,
        @Param("titulo") String titulo
    );

    
}
