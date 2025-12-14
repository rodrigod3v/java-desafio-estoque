-- Database Creation (Run this manualy if needed, or ensure DB 'estoque_db' exists)
-- CREATE DATABASE estoque_db;

-- Table: tb_produto
CREATE TABLE IF NOT EXISTS tb_produto (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    quantidade_minima INTEGER DEFAULT 0,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    valor NUMERIC(15, 2)
);

-- Table: tb_movimento_estoque
-- produto: FK for tb_produto
-- tipo_movimentacao: 'ENTRADA' or 'SAIDA'
CREATE TABLE IF NOT EXISTS tb_movimento_estoque (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    data_movimento TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quantidade NUMERIC(15, 2) NOT NULL,
    tipo_movimentacao VARCHAR(20) NOT NULL,
    CONSTRAINT fk_produto
        FOREIGN KEY (produto_id)
        REFERENCES tb_produto(id)
        ON DELETE CASCADE
);
