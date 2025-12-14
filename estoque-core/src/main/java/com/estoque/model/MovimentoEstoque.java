package com.estoque.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tb_movimento_estoque")
public class MovimentoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "data_movimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMovimento;

    @Column(nullable = false, scale = 2)
    private BigDecimal quantidade;

    @Enumerated(EnumType.STRING) // Storing as String 'ENTRADA'/'SAIDA'
    @Column(name = "tipo_movimentacao", nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @PrePersist
    public void prePersist() {
        if (this.dataMovimento == null) {
            this.dataMovimento = new Date();
        }
    }

    // Constructors
    public MovimentoEstoque() {}

    public MovimentoEstoque(Produto produto, BigDecimal quantidade, TipoMovimentacao tipoMovimentacao) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public Date getDataMovimento() { return dataMovimento; }
    public void setDataMovimento(Date dataMovimento) { this.dataMovimento = dataMovimento; }

    public BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }

    public TipoMovimentacao getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }
}
