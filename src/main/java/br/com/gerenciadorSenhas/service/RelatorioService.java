package br.com.gerenciadorSenhas.service;

import br.com.gerenciadorSenhas.model.ItemCredencial;
import br.com.gerenciadorSenhas.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public class RelatorioService {

    public List<ItemCredencial> relatorioItensPorUsuario(Integer idUsuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT i FROM ItemCredencial i JOIN FETCH i.usuario u WHERE u.id_usuario = :idUsuario";
            return em.createQuery(jpql, ItemCredencial.class)
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    public List<ItemCredencial> relatorioItensPorNomeCategoria(String nomeCategoria) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT i FROM ItemCredencial i JOIN i.categorias c WHERE c.nomeCategoria = :nomeCategoria";
            return em.createQuery(jpql, ItemCredencial.class)
                    .setParameter("nomeCategoria", nomeCategoria)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> relatorioContagemDeItensPorUsuario() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT u.nome, COUNT(i.id_item) FROM Usuario u LEFT JOIN u.itens i GROUP BY u.nome ORDER BY COUNT(i.id_item) DESC";
            return em.createQuery(jpql, Object[].class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}