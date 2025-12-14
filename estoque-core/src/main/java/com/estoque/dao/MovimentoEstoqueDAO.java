package com.estoque.dao;

import com.estoque.model.MovimentoEstoque;
import com.estoque.model.Produto;
import com.estoque.model.TipoMovimentacao;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class MovimentoEstoqueDAO extends AbstractDAO<MovimentoEstoque> {

    public MovimentoEstoqueDAO() {
        super(MovimentoEstoque.class);
    }
    
    public BigDecimal getSaldoTotal(Produto produto) {
        EntityManager em = getEntityManager();
        try {
            // Sum Entradas
            TypedQuery<BigDecimal> queryEntrada = em.createQuery(
                "SELECT COALESCE(SUM(m.quantidade), 0) FROM MovimentoEstoque m WHERE m.produto = :produto AND m.tipoMovimentacao = :tipo", BigDecimal.class);
            queryEntrada.setParameter("produto", produto);
            queryEntrada.setParameter("tipo", TipoMovimentacao.ENTRADA);
            BigDecimal totalEntrada = queryEntrada.getSingleResult();

            // Sum Saidas
            TypedQuery<BigDecimal> querySaida = em.createQuery(
                "SELECT COALESCE(SUM(m.quantidade), 0) FROM MovimentoEstoque m WHERE m.produto = :produto AND m.tipoMovimentacao = :tipo", BigDecimal.class);
            querySaida.setParameter("produto", produto);
            querySaida.setParameter("tipo", TipoMovimentacao.SAIDA);
            BigDecimal totalSaida = querySaida.getSingleResult();

            return totalEntrada.subtract(totalSaida);
        } finally {
            em.close();
        }
    }
}
