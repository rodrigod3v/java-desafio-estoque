package com.estoque.web.bean;

import com.estoque.model.Produto;
import com.estoque.service.ProdutoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DashboardBean implements Serializable {

    private ProdutoService produtoService = new ProdutoService();
    private List<Produto> produtosEstoqueBaixo;

    @PostConstruct
    public void init() {
        carregarEstoqueBaixo();
    }

    public void carregarEstoqueBaixo() {
        produtosEstoqueBaixo = new ArrayList<>();
        List<Produto> todos = produtoService.listarTodos();
        for (Produto p : todos) {
            if (produtoService.isEstoqueBaixo(p)) {
                produtosEstoqueBaixo.add(p);
            }
        }
    }

    public List<Produto> getProdutosEstoqueBaixo() {
        return produtosEstoqueBaixo;
    }
}
