# Sistema de Controle de Estoque (Desafio Java)

Este projeto é uma solução completa para gerenciamento de estoque, desenvolvida como um desafio técnico. A arquitetura foca em boas práticas, separação de responsabilidades e solidez no domínio.

## 🚀 Tecnologias Utilizadas

- **Java**: Compatível com Java 8+, desenvolvido em ambiente Java 21.
- **Maven**: Gerenciamento de dependências e estrutura multi-módulo.
- **JPA / Hibernate**: Camada de persistência robusta.
- **PostgreSQL**: Banco de dados relacional.
- **Swing**: Interface Desktop para operações diárias.
- **JSF (JavaServer Faces)**: Interface Web para relatórios e dashboards.
- **Jetty**: Plugin Maven para execução fácil da versão Web.

## 🏗️ Arquitetura do Projeto

O projeto é dividido em módulos Maven para melhor organização:

1.  **estoque-core**:
    -   Contém as Entidades (`Produto`, `MovimentoEstoque`).
    -   Regras de Negócio e Serviços (`ProdutoService`, `MovimentoService`).
    -   Acesso a Dados (DAOs).
    -   *Destaque*: O estoque atual é calculado dinamicamente (`Soma Entradas - Soma Saídas`), garantindo integridade dos dados.

2.  **estoque-desktop**:
    -   Aplicação GUI Swing.
    -   **Visão Geral (Home)**: Dashboard com saldos em tempo real e histórico de movimentações.
    -   Cadastro de Produtos e Registro de Movimentações (Entrada/Saída).

3.  **estoque-web**:
    -   Aplicação Web Rodando em Jetty.
    -   Busca de produtos e visualização de status.

## ⚙️ Configuração e Execução

### Pré-requisitos
-   Java JDK 8+
-   Maven 3+
-   PostgreSQL

### 1. Banco de Dados
Certifique-se de ter um banco PostgreSQL rodando. O projeto espera:
- **Banco**: `estoque_db`
- **Usuário**: `postgres`
- **Senha**: `123` (Configurável em `estoque-core/src/main/resources/META-INF/persistence.xml`)

Crie o banco e as tabelas:
```sql
CREATE DATABASE estoque_db;
-- O Hibernate (hbm2ddl) irá criar as tabelas automaticamente na primeira execução
```

### 2. Build do Projeto
Na raiz do projeto, execute para baixar dependências e compilar:
```bash
mvn clean install
```

### 3. Executando a Aplicação Desktop 🖥️
A aplicação desktop inicia com um dashboard de visão geral.
```bash
java -jar estoque-desktop/target/estoque-desktop-1.0-SNAPSHOT.jar
```

### 4. Executando a Aplicação Web 🌐
Para rodar a versão web facilmante com Jetty:
```bash
mvn jetty:run -pl estoque-web
```
Acesse no navegador:
-   **URL Principal**: [http://localhost:8080/estoque](http://localhost:8080/estoque)

## 📋 Funcionalidades Implementadas

-   **Persistência**: Mapeamento JPA completo.
-   **Regra de Negócio Dinâmica**: Saldo de estoque é sempre uma projeção das movimentações, evitando inconsistências.
-   **Desktop Rico**: Interface Swing com abas para visualização rápida de saldos e histórico.
-   **Web Integrada**: Módulo web acessível via navegador.

---
Desenvolvido por [Rodrigo](https://github.com/rodrigod3v)
