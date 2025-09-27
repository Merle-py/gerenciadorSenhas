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

    public Categoria buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public List<Categoria> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM Categoria c";
            return em.createQuery(jpql, Categoria.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(categoria);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void remover(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (!em.contains(categoria)) {
                categoria = em.merge(categoria);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}