package br.com.miguelmacedo.todolist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    // @OneToMany(mappedBy = "categoria")
    // @JsonIgnore
    // private List<Tarefa> tarefas = new ArrayList<>();

    public Categoria() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // public List<Tarefa> getTarefas() {
    //     return tarefas;
    // }
}
