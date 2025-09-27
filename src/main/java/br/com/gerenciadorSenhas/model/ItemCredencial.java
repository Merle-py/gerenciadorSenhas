package br.com.gerenciadorSenhas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ITEM_CREDENCIAL")
public class ItemCredencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_item;

    @Column(nullable = false, length = 20)
    private String tipo;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(name = "login_servico", length = 100)
    private String loginServico;

    @Column(name = "senha_criptografada", nullable = false, columnDefinition = "TEXT")
    private String senhaCriptografada;

    @Column(length = 255)
    private String url;

    @Column(name = "data_ultima_modificacao", nullable = false)
    private LocalDateTime dataUltimaModificacao = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "CRED_CATEGORIA",
            joinColumns = @JoinColumn(name = "id_item"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias = new HashSet<>();

    // --- Construtores ---
    public ItemCredencial() {
    }

    public ItemCredencial(String tipo, String titulo, String senhaCriptografada, Usuario usuario) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.senhaCriptografada = senhaCriptografada;
        this.usuario = usuario;
    }

    // --- Getters e Setters ---
    public Integer getId_item() {
        return id_item;
    }

    public void setId_item(Integer id_item) {
        this.id_item = id_item;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLoginServico() {
        return loginServico;
    }

    public void setLoginServico(String loginServico) {
        this.loginServico = loginServico;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getDataUltimaModificacao() {
        return dataUltimaModificacao;
    }

    public void setDataUltimaModificacao(LocalDateTime dataUltimaModificacao) {
        this.dataUltimaModificacao = dataUltimaModificacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "ItemCredencial{" +
                "id_item=" + id_item +
                ", tipo='" + tipo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", usuario=" + (usuario != null ? usuario.getLogin() : "null") +
                '}';
    }
}