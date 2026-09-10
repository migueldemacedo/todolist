package br.com.miguelmacedo.todolist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarefas")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Setter
    @Column(nullable = false, length = 150)
    private String titulo;
    
    @Setter
    @Column(nullable = false)
    private boolean concluida;
    
    // @ManyToOne
    // @JoinColumn(name = "categoria_id")
    // private Categoria categoria;

    public Tarefa(String titulo, boolean concluida) {
        this.titulo = titulo;
        this.concluida = concluida;
    }

    // public Categoria getCategoria() {
    //     return categoria;
    // }

    // public void setCategoria(Categoria categoria) {
    //     this.categoria = categoria;
    // }
}
