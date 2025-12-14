package com.estoque.desktop;

import com.estoque.model.MovimentoEstoque;
import com.estoque.model.Produto;
import com.estoque.model.TipoMovimentacao;
import com.estoque.service.EstoqueService;
import com.estoque.service.ProdutoService;
import java.awt.*;
import java.math.BigDecimal;
import javax.swing.*;

public class MovimentoForm extends JDialog {

    private JComboBox<Produto> cbProdutos;
    private JTextField txtQuantidade = new JTextField(10);
    private JComboBox<TipoMovimentacao> cbTipo = new JComboBox<>(TipoMovimentacao.values());
    private JButton btnRegistrar = new JButton("Registrar Movimento");
    
    private ProdutoService produtoService = new ProdutoService();
    private EstoqueService estoqueService = new EstoqueService();

    public MovimentoForm(Frame owner) {
        super(owner, "Controle de Estoque", true);
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        // Load products for combo box
        cbProdutos = new JComboBox<>(produtoService.listarTodos().toArray(new Produto[0]));
        // Simple renderer to show description
        cbProdutos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Produto) {
                    setText(((Produto)value).getDescricao());
                }
                return this;
            }
        });
        
        panel.add(new JLabel("Produto:"));
        panel.add(cbProdutos);
        
        panel.add(new JLabel("Tipo:"));
        panel.add(cbTipo);
        
        panel.add(new JLabel("Quantidade:"));
        panel.add(txtQuantidade);
        
        panel.add(new JLabel(""));
        panel.add(btnRegistrar);
        
        add(panel, BorderLayout.CENTER);
        
        btnRegistrar.addActionListener(e -> registrar());
        
        pack();
        setLocationRelativeTo(owner);
    }

    private void registrar() {
        try {
            Produto selectedProd = (Produto) cbProdutos.getSelectedItem();
            BigDecimal qtd = new BigDecimal(txtQuantidade.getText().replace(",", "."));
            TipoMovimentacao tipo = (TipoMovimentacao) cbTipo.getSelectedItem();
            
            MovimentoEstoque mov = new MovimentoEstoque(selectedProd, qtd, tipo);
            estoqueService.registrarMovimento(mov);
            
            JOptionPane.showMessageDialog(this, "Movimento registrado com sucesso!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
