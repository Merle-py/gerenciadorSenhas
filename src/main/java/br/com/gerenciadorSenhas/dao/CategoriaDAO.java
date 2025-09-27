package br.com.gerenciadorSenhas.dao;

import br.com.gerenciadorSenhas.model.Categoria;
import br.com.gerenciadorSenhas.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CategoriaDAO {

    public void salvar(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(categoria);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Categoria> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Categoria buscarPorId(Integer id, EntityManager em) {
        return em.find(Categoria.class, id);
    }
}