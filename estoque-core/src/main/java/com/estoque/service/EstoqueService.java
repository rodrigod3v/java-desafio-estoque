package com.estoque.service;

import com.estoque.dao.MovimentoEstoqueDAO;
import com.estoque.model.MovimentoEstoque;

public class EstoqueService {

    private MovimentoEstoqueDAO movimentoDAO = new MovimentoEstoqueDAO();

    public void registrarMovimento(MovimentoEstoque movimento) {
        // Here we could add validation logic (e.g. check if there is enough stock for SAIDA)
        movimentoDAO.save(movimento);
    }
}
