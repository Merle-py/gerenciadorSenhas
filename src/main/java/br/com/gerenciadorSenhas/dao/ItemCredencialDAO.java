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

    public List<ItemCredencial> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT i FROM ItemCredencial i", ItemCredencial.class).getResultList();
        } finally {
            em.close();
        }
    }

    public ItemCredencial buscarPorId(Integer id, EntityManager em) {
        return em.find(ItemCredencial.class, id);
    }

    // O método de atualização agora também recebe o EntityManager para operar na transação do serviço
    public void atualizar(ItemCredencial item, EntityManager em) {
        em.merge(item);
    }
}