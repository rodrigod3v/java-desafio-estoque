package com.estoque.desktop;

import com.estoque.model.MovimentoEstoque;
import com.estoque.model.Produto;
import com.estoque.service.MovimentoService;
import com.estoque.service.ProdutoService;
import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VisaoGeralPanel extends JPanel {

    private ProdutoService produtoService = new ProdutoService();
    private MovimentoService movimentoService = new MovimentoService();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public VisaoGeralPanel() {
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Saldos em Estoque", createPanelSaldos());
        tabbedPane.addTab("Histórico de Movimentações", createPanelMovimentacoes());

        add(tabbedPane, BorderLayout.CENTER);
    }

    public void atualizarDados() {
        removeAll();
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Saldos em Estoque", createPanelSaldos());
        tabbedPane.addTab("Histórico de Movimentações", createPanelMovimentacoes());
        add(tabbedPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JPanel createPanelSaldos() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columns = {"ID", "Descrição", "Qtd. Mínima", "Valor", "Saldo Atual"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        List<Produto> produtos = produtoService.listarTodos();
        for (Produto p : produtos) {
            BigDecimal saldo = produtoService.getSaldoAtual(p);
            model.addRow(new Object[]{
                p.getId(),
                p.getDescricao(),
                p.getQuantidadeMinima(),
                p.getValor(),
                saldo
            });
        }
        
        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createPanelMovimentacoes() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columns = {"Data", "Produto", "Tipo", "Quantidade"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        List<MovimentoEstoque> movimentos = movimentoService.listarTodos();
        
        for (MovimentoEstoque m : movimentos) {
            model.addRow(new Object[]{
                sdf.format(m.getDataMovimento()),
                m.getProduto().getDescricao(),
                m.getTipoMovimentacao(),
                m.getQuantidade()
            });
        }
        
        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
}
