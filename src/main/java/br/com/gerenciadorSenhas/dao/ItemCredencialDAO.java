package br.com.gerenciadorSenhas.dao;

import br.com.gerenciadorSenhas.model.ItemCredencial;
import br.com.gerenciadorSenhas.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ItemCredencialDAO {

    public void salvar(ItemCredencial item) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public ItemCredencial buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(ItemCredencial.class, id);
        } finally {
            em.close();
        }
    }

    public List<ItemCredencial> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT i FROM ItemCredencial i";
            return em.createQuery(jpql, ItemCredencial.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(ItemCredencial item) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void remover(ItemCredencial item) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (!em.contains(item)) {
                item = em.merge(item);
            }
            em.remove(item);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}