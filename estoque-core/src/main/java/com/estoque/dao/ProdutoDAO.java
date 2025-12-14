package com.estoque.dao;

import com.estoque.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;

public class ProdutoDAO extends AbstractDAO<Produto> {

    public ProdutoDAO() {
        super(Produto.class);
    }

    public List<Produto> findByDescricao(String descricao) {
         EntityManager em = getEntityManager();
         try {
             return em.createQuery("SELECT p FROM Produto p WHERE LOWER(p.descricao) LIKE :descricao", Produto.class)
                     .setParameter("descricao", "%" + descricao.toLowerCase() + "%")
                     .getResultList();
         } finally {
             em.close();
         }
    }
}
