package br.com.gerenciadorSenhas.dao;

import br.com.gerenciadorSenhas.model.Usuario;
import br.com.gerenciadorSenhas.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UsuarioDAO {

    // Métodos que fazem uma única operação podem continuar gerenciando sua própria transação
    public void salvar(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Usuario> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Métodos que farão parte de uma transação maior recebem o EntityManager
    public Usuario buscarPorId(Integer id, EntityManager em) {
        return em.find(Usuario.class, id);
    }
}