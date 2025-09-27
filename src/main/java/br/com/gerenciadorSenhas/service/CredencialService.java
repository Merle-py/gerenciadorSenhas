package br.com.gerenciadorSenhas.service;

import br.com.gerenciadorSenhas.dao.CategoriaDAO;
import br.com.gerenciadorSenhas.dao.ItemCredencialDAO;
import br.com.gerenciadorSenhas.model.Categoria;
import br.com.gerenciadorSenhas.model.ItemCredencial;

public class CredencialService {

    private final ItemCredencialDAO itemCredencialDAO = new ItemCredencialDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public void associarCredencialACategoria(Integer idItem, Integer idCategoria) {
        ItemCredencial item = itemCredencialDAO.buscarPorId(idItem);
        Categoria categoria = categoriaDAO.buscarPorId(idCategoria);

        if (item != null && categoria != null) {
            item.getCategorias().add(categoria);
            itemCredencialDAO.atualizar(item);
            System.out.println("SUCESSO: Credencial '" + item.getTitulo() + "' associada à categoria '" + categoria.getNomeCategoria() + "'.");
        } else {
            System.out.println("ERRO: Item ou Categoria não encontrados para associação.");
        }
    }

    public void desassociarCredencialDeCategoria(Integer idItem, Integer idCategoria) {
        ItemCredencial item = itemCredencialDAO.buscarPorId(idItem);

        if (item != null) {
            // A busca pela categoria é feita para garantir que o objeto Categoria a ser removido seja o correto
            Categoria categoriaParaRemover = item.getCategorias().stream()
                    .filter(c -> c.getId_categoria().equals(idCategoria))
                    .findFirst()
                    .orElse(null);

            if (categoriaParaRemover != null) {
                item.getCategorias().remove(categoriaParaRemover);
                itemCredencialDAO.atualizar(item);
                System.out.println("SUCESSO: Credencial '" + item.getTitulo() + "' desassociada da categoria '" + categoriaParaRemover.getNomeCategoria() + "'.");
            } else {
                System.out.println("ERRO: A credencial não estava associada a esta categoria.");
            }
        } else {
            System.out.println("ERRO: Item não encontrado para desassociação.");
        }
    }
}