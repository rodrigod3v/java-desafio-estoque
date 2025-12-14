package com.estoque.web.bean;

import com.estoque.model.Produto;
import com.estoque.service.ProdutoService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BuscaBean implements Serializable {

    private ProdutoService produtoService = new ProdutoService();
    private String termoBusca;
    private List<Produto> resultados;

    public void buscar() {
        if (termoBusca != null && !termoBusca.isEmpty()) {
            resultados = produtoService.buscarPorDescricao(termoBusca);
        } else {
            resultados = produtoService.listarTodos();
        }
    }
    
    public BigDecimal getSaldoAtual(Produto p) {
        return produtoService.getSaldoAtual(p);
    }

    // Getters and Setters
    public String getTermoBusca() { return termoBusca; }
    public void setTermoBusca(String termoBusca) { this.termoBusca = termoBusca; }
    
    public List<Produto> getResultados() { return resultados; }
}
