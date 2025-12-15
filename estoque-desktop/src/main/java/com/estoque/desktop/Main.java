package com.estoque.desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

    private VisaoGeralPanel visaoGeralPanel;

    public Main() {
        setTitle("Sistema de Controle de Estoque");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new java.awt.BorderLayout());

        visaoGeralPanel = new VisaoGeralPanel();
        add(visaoGeralPanel, java.awt.BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuCadastro = new JMenu("Cadastros");
        JMenuItem itemProduto = new JMenuItem("Produtos");
        itemProduto.addActionListener(this::abrirProdutoForm);
        menuCadastro.add(itemProduto);

        JMenu menuEstoque = new JMenu("Estoque");
        
        JMenuItem itemVisaoGeral = new JMenuItem("Atualizar Visão Geral");
        itemVisaoGeral.addActionListener(e -> visaoGeralPanel.atualizarDados());
        menuEstoque.add(itemVisaoGeral);
        
        JMenuItem itemMovimentacao = new JMenuItem("Movimentação (Entrada/Saída)");
        itemMovimentacao.addActionListener(this::abrirMovimentoForm);
        menuEstoque.add(itemMovimentacao);

        menuBar.add(menuCadastro);
        menuBar.add(menuEstoque);
        setJMenuBar(menuBar);
    }

    private void abrirProdutoForm(ActionEvent e) {
        new ProdutoForm(this).setVisible(true);
    }
    
    private void abrirMovimentoForm(ActionEvent e) {
        new MovimentoForm(this).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
