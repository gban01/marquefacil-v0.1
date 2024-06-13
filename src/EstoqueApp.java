import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EstoqueApp {
    private JFrame frame;
    private JTextField descricaoField, quantidadeField, valorField, vendidosField;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Produto> produtos;
    private JFrame parentFrame;

    public EstoqueApp(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        produtos = new ArrayList<>();
    }

    public void createAndShowGUI() {
        frame = new JFrame("Estoque");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        frame.add(inputPanel, BorderLayout.NORTH);

        descricaoField = new JTextField();
        quantidadeField = new JTextField();
        valorField = new JTextField();
        vendidosField = new JTextField();

        inputPanel.add(new JLabel("Descrição:"));
        inputPanel.add(descricaoField);
        inputPanel.add(new JLabel("Quantidade:"));
        inputPanel.add(quantidadeField);
        inputPanel.add(new JLabel("Valor:"));
        inputPanel.add(valorField);
        inputPanel.add(new JLabel("Vendidos:"));
        inputPanel.add(vendidosField);

        JButton adicionarButton = new JButton("Adicionar");
        inputPanel.add(new JLabel());
        inputPanel.add(adicionarButton);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });

        tableModel = new DefaultTableModel(new String[]{"Descrição", "Quantidade", "Valor", "Vendidos"}, 0);
        table = new JTable(tableModel);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        frame.add(bottomPanel, BorderLayout.SOUTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3));
        bottomPanel.add(buttonsPanel, BorderLayout.CENTER);

        JButton mostrarProdutosButton = new JButton("Mostrar Produtos");
        JButton mostrarVendidosButton = new JButton("Mostrar Vendidos");
        JButton voltarButton = new JButton("Voltar");

        buttonsPanel.add(mostrarProdutosButton);
        buttonsPanel.add(mostrarVendidosButton);
        buttonsPanel.add(voltarButton);

        mostrarProdutosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProdutos();
            }
        });

        mostrarVendidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVendidos();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                parentFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }

    private void adicionarProduto() {
        String descricao = descricaoField.getText();
        int quantidade = Integer.parseInt(quantidadeField.getText());
        double valor = Double.parseDouble(valorField.getText());
        int vendidos = Integer.parseInt(vendidosField.getText());

        Produto produto = new Produto(descricao, quantidade, valor, vendidos);
        produtos.add(produto);

        atualizarTabela();

        descricaoField.setText("");
        quantidadeField.setText("");
        valorField.setText("");
        vendidosField.setText("");
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);

        for (Produto produto : produtos) {
            tableModel.addRow(new Object[]{
                    produto.getDescricao(),
                    produto.getQuantidade(),
                    produto.getValor(),
                    produto.getVendidos()
            });
        }
    }

    private void mostrarProdutos() {
        StringBuilder mensagem = new StringBuilder("Produtos em Estoque:\n");
        for (Produto produto : produtos) {
            mensagem.append("Descrição: ").append(produto.getDescricao())
                    .append(", Quantidade: ").append(produto.getQuantidade())
                    .append(", Valor: ").append(produto.getValor()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, mensagem.toString());
    }

    private void mostrarVendidos() {
        StringBuilder mensagem = new StringBuilder("Produtos Vendidos:\n");
        for (Produto produto : produtos) {
            mensagem.append("Descrição: ").append(produto.getDescricao())
                    .append(", Vendidos: ").append(produto.getVendidos()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, mensagem.toString());
    }

    private static class Produto {
        private String descricao;
        private int quantidade;
        private double valor;
        private int vendidos;

        public Produto(String descricao, int quantidade, double valor, int vendidos) {
            this.descricao = descricao;
            this.quantidade = quantidade;
            this.valor = valor;
            this.vendidos = vendidos;
        }

        public String getDescricao() {
            return descricao;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public double getValor() {
            return valor;
        }

        public int getVendidos() {
            return vendidos;
        }
    }
}
