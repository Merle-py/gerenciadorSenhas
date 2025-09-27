package br.com.gerenciadorSenhas.main;

import br.com.gerenciadorSenhas.dao.CategoriaDAO;
import br.com.gerenciadorSenhas.dao.ItemCredencialDAO;
import br.com.gerenciadorSenhas.dao.UsuarioDAO;
import br.com.gerenciadorSenhas.model.Categoria;
import br.com.gerenciadorSenhas.model.ItemCredencial;
import br.com.gerenciadorSenhas.model.Usuario;
import br.com.gerenciadorSenhas.service.CredencialService;
import br.com.gerenciadorSenhas.service.RelatorioService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // --- Instanciando DAOs e Serviços ---
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ItemCredencialDAO itemCredencialDAO = new ItemCredencialDAO();
        CredencialService credencialService = new CredencialService();
        RelatorioService relatorioService = new RelatorioService();

        // --- 1. OPERAÇÕES DE CRUD ---
        System.out.println("========== 1. DEMONSTRAÇÃO CRUD ==========");

        // CREATE
        Usuario user = new Usuario("Maria Souza", "maria.souza", "hash123", "salt456");
        usuarioDAO.salvar(user);
        System.out.println("Usuário salvo: " + user.getNome());

        Categoria catPessoal = new Categoria("Pessoal");
        categoriaDAO.salvar(catPessoal);
        System.out.println("Categoria salva: " + catPessoal.getNomeCategoria());

        Categoria catTrabalho = new Categoria("Trabalho");
        categoriaDAO.salvar(catTrabalho);

        ItemCredencial itemNetflix = new ItemCredencial("SENHA", "Netflix", "senha_super_secreta", user);
        itemNetflix.setLoginServico("maria.souza@email.com");
        itemCredencialDAO.salvar(itemNetflix);
        System.out.println("Item salvo: " + itemNetflix.getTitulo());

        ItemCredencial itemLinkedin = new ItemCredencial("SENHA", "LinkedIn", "outra_senha", user);
        itemLinkedin.setLoginServico("maria.s@linkedin.com");
        itemCredencialDAO.salvar(itemLinkedin);

        // READ
        Usuario usuarioBuscado = usuarioDAO.buscarPorId(user.getId_usuario());
        System.out.println("Usuário buscado por ID: " + usuarioBuscado.getNome());

        // UPDATE
        itemNetflix.setTitulo("Netflix Família");
        itemCredencialDAO.atualizar(itemNetflix);
        System.out.println("Item atualizado para: " + itemNetflix.getTitulo());

        System.out.println("\n========== 2. PROCESSOS DE NEGÓCIO ==========");
        // --- 2. ASSOCIAR/DESASSOCIAR (Processo de Negócio) ---
        credencialService.associarCredencialACategoria(itemNetflix.getId_item(), catPessoal.getId_categoria());
        credencialService.associarCredencialACategoria(itemLinkedin.getId_item(), catTrabalho.getId_categoria());
        credencialService.associarCredencialACategoria(itemLinkedin.getId_item(), catPessoal.getId_categoria()); // LinkedIn em duas categorias

        System.out.println("\n========== 3. RELATÓRIOS ==========");
        // --- 3. RELATÓRIOS ---
        // Relatório 1: Itens por usuário
        System.out.println("\n--- Relatório 1: Itens do usuário " + user.getNome() + " ---");
        List<ItemCredencial> itensDoUsuario = relatorioService.relatorioItensPorUsuario(user.getId_usuario());
        itensDoUsuario.forEach(it -> System.out.println(" - Título: " + it.getTitulo() + ", Tipo: " + it.getTipo()));

        // Relatório 2: Itens por categoria
        System.out.println("\n--- Relatório 2: Itens da categoria 'Pessoal' ---");
        List<ItemCredencial> itensDaCategoria = relatorioService.relatorioItensPorNomeCategoria("Pessoal");
        itensDaCategoria.forEach(it -> System.out.println(" - Título: " + it.getTitulo()));

        // Relatório 3: Contagem de itens por usuário
        System.out.println("\n--- Relatório 3: Contagem de itens por usuário ---");
        List<Object[]> contagem = relatorioService.relatorioContagemDeItensPorUsuario();
        contagem.forEach(obj -> {
            String nome = (String) obj[0];
            Long total = (Long) obj[1];
            System.out.printf(" - Usuário: %s, Total de Itens: %d\n", nome, total);
        });

        // --- 4. REMOÇÃO (Cleanup) ---
        // System.out.println("\n========== 4. REMOÇÃO ==========");
        // itemCredencialDAO.remover(itemNetflix);
        // System.out.println("Item 'Netflix Família' removido.");
        // usuarioDAO.remover(user); // Graças ao ON DELETE CASCADE, isso removerá o outro item também.
        // System.out.println("Usuário 'Maria Souza' e seus itens restantes foram removidos.");
        // categoriaDAO.remover(catPessoal);
        // categoriaDAO.remover(catTrabalho);
        // System.out.println("Categorias removidas.");

        System.out.println("\nExecução de demonstração finalizada.");
    }
}