# Sistema de Controle de Estoque (Desafio Java)

Este projeto é uma solução completa para gerenciamento de estoque, desenvolvida como um desafio técnico. A arquitetura foca em boas práticas, separação de responsabilidades e solidez no domínio.

## 🚀 Tecnologias Utilizadas

- **Java**: Compatível com Java 8+, desenvolvido em ambiente Java 21.
- **Maven**: Gerenciamento de dependências e estrutura multi-módulo.
- **JPA / Hibernate**: Camada de persistência robusta.
- **PostgreSQL**: Banco de dados relacional.
- **Swing**: Interface Desktop para operações diárias.
- **JSF (JavaServer Faces)**: Interface Web para relatórios e dashboards.

## 🏗️ Arquitetura do Projeto

O projeto é dividido em módulos Maven para melhor organização:

1.  **estoque-core**:
    -   Contém as Entidades (`Produto`, `MovimentoEstoque`).
    -   Regras de Negócio e Serviços (`ProdutoService`, `EstoqueService`).
    -   Acesso a Dados (DAOs).
    -   *Destaque*: O estoque atual é calculado dinamicamente (`Soma Entradas - Soma Saídas`), garantindo integridade dos dados.

2.  **estoque-desktop**:
    -   Aplicação GUI Swing.
    -   Cadastro de Produtos e Registro de Movimentações.

3.  **estoque-web**:
    -   Aplicação Web Rodando em Tomcat/Jetty.
    -   Dashboard de "Estoque Baixo".
    -   Busca de produtos com visualização do saldo atual calculado.

## ⚙️ Como Executar

### Pré-requisitos
-   Java JDK 8+
-   Maven 3+
-   PostgreSQL
-   Tomcat (Opcional, para o módulo Web)

### 1. Banco de Dados
Crie um banco de dados chamado `estoque_db` no PostgreSQL e execute o script de esquema:

```sql
CREATE DATABASE estoque_db;
-- Execute o conteúdo do arquivo schema.sql na raiz do projeto
```

*Nota: A configuração de conexão está em `estoque-core/src/main/resources/META-INF/persistence.xml` (padrão: localhost:5432, user: postgres, pass: postgres).*

### 2. Build do Projeto
Na raiz do projeto, execute:
```bash
mvn clean install
```
Isso irá compilar, testar e gerar os artefatos (`.jar` e `.war`).

### 3. Executando a Aplicação Desktop
O build gera um JAR executável (shaded) com todas as dependências:
```bash
java -jar estoque-desktop/target/estoque-desktop-1.0-SNAPSHOT.jar
```

### 4. Executando a Aplicação Web
Faça o deploy do arquivo WAR gerado no seu servidor de aplicação (ex: Tomcat):
-   Arquivo: `estoque-web/target/estoque-web-1.0-SNAPSHOT.war`
-   Acesse: `http://localhost:8080/estoque-web-1.0-SNAPSHOT/`

## 📋 Funcionalidades Implementadas

-   **Persistência**: Mapeamento JPA completo com Enums para tipos de movimentação.
-   **Regra de Negócio**: Cálculo de saldo em tempo real, sem redundância de dados na tabela de produto.
-   **Interface Desktop**: Formulários para cadastro e controle de fluxo (Entrada/Saída).
-   **Interface Web**: Dashboard proativo exibindo produtos com estoque abaixo do mínimo.

---
Desenvolvido por [Rodrigo](https://github.com/rodrigod3v)
