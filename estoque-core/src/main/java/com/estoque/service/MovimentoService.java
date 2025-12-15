package com.estoque.service;

import com.estoque.dao.MovimentoEstoqueDAO;
import com.estoque.model.MovimentoEstoque;
import java.util.List;

public class MovimentoService {

    private MovimentoEstoqueDAO dao = new MovimentoEstoqueDAO();

    public void salvar(MovimentoEstoque movimento) {
        dao.save(movimento);
    }

    public List<MovimentoEstoque> listarTodos() {
        return dao.findAll(); // Assuming AbstractDAO has findAll
    }
}
