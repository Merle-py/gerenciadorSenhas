package br.com.gerenciadorSenhas.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categoria;

    @Column(name = "nome_categoria", unique = true, nullable = false, length = 50)
    private String nomeCategoria;

    @ManyToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    private Set<ItemCredencial> itens = new HashSet<>();

    // --- Construtores ---
    public Categoria() {
    }

    public Categoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    // --- Getters e Setters ---
    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Set<ItemCredencial> getItens() {
        return itens;
    }

    public void setItens(Set<ItemCredencial> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id_categoria=" + id_categoria +
                ", nomeCategoria='" + nomeCategoria + '\'' +
                '}';
    }
}