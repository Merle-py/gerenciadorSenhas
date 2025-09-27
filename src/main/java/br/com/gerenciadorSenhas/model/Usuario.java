package br.com.gerenciadorSenhas.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, nullable = false, length = 50)
    private String login;

    @Column(name = "senha_mestra_hash", nullable = false, length = 64)
    private String senhaMestraHash;

    @Column(nullable = false, length = 32)
    private String salt;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro = LocalDate.now();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemCredencial> itens = new ArrayList<>();

    // --- Construtores ---
    public Usuario() {
    }

    public Usuario(String nome, String login, String senhaMestraHash, String salt) {
        this.nome = nome;
        this.login = login;
        this.senhaMestraHash = senhaMestraHash;
        this.salt = salt;
    }

    // --- Getters e Setters ---
    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenhaMestraHash() {
        return senhaMestraHash;
    }

    public void setSenhaMestraHash(String senhaMestraHash) {
        this.senhaMestraHash = senhaMestraHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<ItemCredencial> getItens() {
        return itens;
    }

    public void setItens(List<ItemCredencial> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}