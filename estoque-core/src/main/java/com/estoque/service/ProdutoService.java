package com.estoque.service;

import com.estoque.dao.MovimentoEstoqueDAO;
import com.estoque.dao.ProdutoDAO;
import com.estoque.model.Produto;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private MovimentoEstoqueDAO movimentoDAO = new MovimentoEstoqueDAO();

    public void salvar(Produto produto) {
        if (produto.getId() == null) {
            produtoDAO.save(produto);
        } else {
            produtoDAO.update(produto);
        }
    }

    public List<Produto> listarTodos() {
        return produtoDAO.findAll();
    }
    
    public List<Produto> buscarPorDescricao(String query) {
        return produtoDAO.findByDescricao(query);
    }

    public BigDecimal getSaldoAtual(Produto produto) {
        return movimentoDAO.getSaldoTotal(produto);
    }

    public boolean isEstoqueBaixo(Produto produto) {
         BigDecimal saldo = getSaldoAtual(produto);
         return saldo.compareTo(new BigDecimal(produto.getQuantidadeMinima())) <= 0;
    }
}
