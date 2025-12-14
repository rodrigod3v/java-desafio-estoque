package com.estoque.desktop;

import com.estoque.model.Produto;
import com.estoque.service.ProdutoService;
import java.awt.*;
import java.math.BigDecimal;
import javax.swing.*;

public class ProdutoForm extends JDialog {

    private JTextField txtDescricao = new JTextField(20);
    private JTextField txtQtdeMinima = new JTextField(10);
    private JTextField txtValor = new JTextField(10);
    private JButton btnSalvar = new JButton("Salvar");
    
    private ProdutoService service = new ProdutoService();

    public ProdutoForm(Frame owner) {
        super(owner, "Cadastro de Produto", true);
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        panel.add(new JLabel("Descrição:"));
        panel.add(txtDescricao);
        
        panel.add(new JLabel("Qtd. Mínima:"));
        panel.add(txtQtdeMinima);
        
        panel.add(new JLabel("Valor (R$):"));
        panel.add(txtValor);
        
        panel.add(new JLabel(""));
        panel.add(btnSalvar);
        
        add(panel, BorderLayout.CENTER);
        
        btnSalvar.addActionListener(e -> salvar());
        
        pack();
        setLocationRelativeTo(owner);
    }

    private void salvar() {
        try {
            Produto p = new Produto();
            p.setDescricao(txtDescricao.getText());
            p.setQuantidadeMinima(Integer.parseInt(txtQtdeMinima.getText()));
            p.setValor(new BigDecimal(txtValor.getText().replace(",", ".")));
            
            service.salvar(p);
            JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
