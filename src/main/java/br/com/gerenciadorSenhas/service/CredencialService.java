package br.com.gerenciadorSenhas.service;

import br.com.gerenciadorSenhas.dao.CategoriaDAO;
import br.com.gerenciadorSenhas.dao.ItemCredencialDAO;
import br.com.gerenciadorSenhas.model.Categoria;
import br.com.gerenciadorSenhas.model.ItemCredencial;
import br.com.gerenciadorSenhas.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class CredencialService {

    private final ItemCredencialDAO itemCredencialDAO = new ItemCredencialDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public void associarCredencialACategoria(Integer idItem, Integer idCategoria) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            ItemCredencial item = itemCredencialDAO.buscarPorId(idItem, em);
            Categoria categoria = categoriaDAO.buscarPorId(idCategoria, em);

            if (item != null && categoria != null) {
                item.getCategorias().add(categoria);
                itemCredencialDAO.atualizar(item, em);
                System.out.println("SUCESSO: Credencial '" + item.getTitulo() + "' associada à categoria '" + categoria.getNomeCategoria() + "'.");
            } else {
                System.out.println("ERRO: Item ou Categoria não encontrados para associação.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}