<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <div class="container">
        <h1>🚀 Gerenciador de Senhas Desktop</h1>
        <p>
            Este é um projeto de um aplicativo de desktop para gerenciamento de credenciais, desenvolvido em Java com interface gráfica Swing. A aplicação permite o cadastro, consulta, atualização e exclusão de usuários, categorias e itens de credenciais, além da geração de relatórios.
        </p>
        <p>
            A persistência de dados é feita através do JPA/Hibernate, conectado a um banco de dados PostgreSQL.
        </p>
        <h2>✨ Funcionalidades</h2>
        <ul>
            <li><strong>CRUD Completo:</strong> Gerenciamento total (Criar, Ler, Atualizar, Excluir) para Usuários, Categorias e Itens de Credenciais.</li>
            <li><strong>Interface Gráfica Intuitiva:</strong> Organizada em abas para cada funcionalidade.</li>
            <li><strong>Relacionamentos:</strong> Associação flexível de credenciais a múltiplas categorias.</li>
            <li><strong>Relatórios:</strong> Geração de 3 relatórios de dados para análise.</li>
        </ul>
        <h2>💻 Tecnologias Utilizadas</h2>
        <ul>
            <li><strong>Linguagem:</strong> Java 17+</li>
            <li><strong>Interface Gráfica:</strong> Java Swing</li>
            <li><strong>Banco de Dados:</strong> PostgreSQL</li>
            <li><strong>ORM:</strong> JPA / Hibernate</li>
            <li><strong>Gerenciador de Dependências:</strong> Apache Maven</li>
            <li><strong>IDE Recomendada:</strong> IntelliJ IDEA</li>
        </ul>
        <hr>
        <h2>🔧 Tutorial de Instalação e Configuração</h2>
        <p>Siga os passos abaixo para executar o projeto em uma nova máquina.</p>
        <h3>1. Pré-requisitos</h3>
        <p>Antes de começar, garanta que você tenha os seguintes softwares instalados:</p>
        <ol>
            <li><strong>Java JDK 17 ou superior:</strong> Essencial para compilar e executar o projeto. <a href="https://adoptium.net/" target="_blank">Download OpenJDK</a>.</li>
            <li><strong>Apache Maven:</strong> Para gerenciar as dependências. <a href="https://maven.apache.org/download.cgi" target="_blank">Download Maven</a>.</li>
            <li><strong>PostgreSQL:</strong> O banco de dados onde as informações serão salvas. <a href="https://www.postgresql.org/download/" target="_blank">Download PostgreSQL</a>.</li>
            <li><strong>Git:</strong> Para clonar o repositório (opcional, mas recomendado).</li>
        </ol>
        <h3>2. Passos para Instalação</h3>
        <ol>
            <li>
                <strong>Clone o Repositório</strong><br>
                Abra um terminal e clone o projeto para a sua máquina.
                <pre><code>git clone https://github.com/Merle-py/gerenciadorSenhas.git;
cd gerenciadorSenhas</code></pre>
            </li>
            <li>
                <strong>Configure o Banco de Dados</strong><br>
                Abra seu cliente de PostgreSQL (pgAdmin, DBeaver, etc.) e crie um novo banco de dados vazio (ex: <code>gerenciador_senhas_db</code>). Anote o nome, usuário e senha.
            </li>
            <li>
                <strong>Abra o Projeto no IntelliJ IDEA</strong><br>
                Vá em <code>File &gt; Open...</code> e selecione a pasta do projeto. O IntelliJ irá baixar as dependências do Maven automaticamente.
            </li>
        </ol>
        <h2>⚙️ O Que Precisa Ser Alterado para Utilizar em Outro PC</h2>
        <div class="highlight">
            <p><strong>Atenção:</strong> Esta é a parte mais importante da configuração. Para que o programa se conecte ao seu banco de dados local, você <strong>obrigatoriamente</strong> precisa editar o seguinte arquivo.</p>
        </div>
        <p>
            Navegue até <code>src/main/resources/META-INF/persistence.xml</code> e altere as 3 propriedades destacadas com os dados do seu banco de dados:
        </p>
        <pre><code>&lt;properties&gt;
    &lt;!-- ... --&gt;
    &lt;!-- 1. ALTERE AQUI o nome do seu banco de dados --&gt;
    &lt;property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/gerenciador_senhas_db" /&gt;
    &lt;!-- 2. ALTERE AQUI para o seu usuário do PostgreSQL --&gt;
    &lt;property name="jakarta.persistence.jdbc.user" value="postgres" /&gt;
    &lt;!-- 3. ALTERE AQUI para a sua senha do PostgreSQL --&gt;
    &lt;property name="jakarta.persistence.jdbc.password" value="sua_senha_secreta" /&gt;
    &lt;!-- ... --&gt;
    &lt;!-- Esta linha cria e apaga as tabelas a cada execução, ideal para testes --&gt;
    &lt;property name="hibernate.hbm2ddl.auto" value="create-drop" /&gt;
&lt;/properties&gt;</code></pre>
        <h2>✅ Executando a Aplicação</h2>
        <p>Depois de configurar o <code>persistence.xml</code>, a aplicação está pronta para rodar.</p>
        <ol>
            <li>No IntelliJ, encontre a classe <code>Main.java</code> em <code>src/main/java/br/com/gerenciadorSenhas/main/</code>.</li>
            <li>Clique com o botão direito no arquivo e selecione <strong><code>Run 'Main.main()'</code></strong>.</li>
        </ol>
        <p>A interface gráfica do Gerenciador de Senhas deverá aparecer na sua tela!</p>
    </div>

</body>
</html>
