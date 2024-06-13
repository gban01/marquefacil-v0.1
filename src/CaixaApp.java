import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaixaApp {
    private JFrame parentFrame;
    private JFrame frame;
    private JTextField descricaoField, valorField;
    private JComboBox<String> tipoComboBox;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private List<RegistroCaixa> registros;

    public CaixaApp(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        registros = new ArrayList<>();
    }

    public void createAndShowGUI() {
        frame = new JFrame("Caixa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        frame.add(inputPanel, BorderLayout.NORTH);

        descricaoField = new JTextField();
        valorField = new JTextField();
        tipoComboBox = new JComboBox<>(new String[]{"Entrada", "Saída"});

        inputPanel.add(new JLabel("Descrição:"));
        inputPanel.add(descricaoField);
        inputPanel.add(new JLabel("Valor:"));
        inputPanel.add(valorField);
        inputPanel.add(new JLabel("Tipo:"));
        inputPanel.add(tipoComboBox);

        JButton adicionarButton = new JButton("Adicionar");
        inputPanel.add(new JLabel());
        inputPanel.add(adicionarButton);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarRegistro();
            }
        });

        tableModel = new DefaultTableModel(new String[]{"Descrição", "Tipo", "Valor", "Data"}, 0);
        table = new JTable(tableModel);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        frame.add(bottomPanel, BorderLayout.SOUTH);

        totalLabel = new JLabel("Total: 0.00");
        bottomPanel.add(totalLabel, BorderLayout.WEST);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.add(buttonsPanel, BorderLayout.CENTER);

        JButton mostrarEntradaButton = new JButton("Mostrar Entradas");
        JButton mostrarSaidaButton = new JButton("Mostrar Saídas");
        JButton mostrarTotalButton = new JButton("Mostrar Total");
        JButton voltarButton = new JButton("Voltar");

        buttonsPanel.add(mostrarEntradaButton);
        buttonsPanel.add(mostrarSaidaButton);
        buttonsPanel.add(mostrarTotalButton);
        buttonsPanel.add(voltarButton);

        mostrarEntradaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEntradas();
            }
        });

        mostrarSaidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSaidas();
            }
        });

        mostrarTotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTotal();
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

    private void adicionarRegistro() {
        String descricao = descricaoField.getText();
        String tipo = (String) tipoComboBox.getSelectedItem();
        double valor = Double.parseDouble(valorField.getText());
        Date data = new Date();

        RegistroCaixa registro = new RegistroCaixa(descricao, tipo, valor, data);
        registros.add(registro);

        atualizarTabela();
        atualizarTotal();

        descricaoField.setText("");
        valorField.setText("");
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (RegistroCaixa registro : registros) {
            tableModel.addRow(new Object[]{
                    registro.getDescricao(),
                    registro.getTipo(),
                    registro.getValor(),
                    dateFormat.format(registro.getData())
            });
        }
    }

    private void atualizarTotal() {
        double total = 0.0;

        for (RegistroCaixa registro : registros) {
            if (registro.getTipo().equals("Entrada")) {
                total += registro.getValor();
            } else {
                total -= registro.getValor();
            }
        }

        DecimalFormat df = new DecimalFormat("#.00");
        totalLabel.setText("Total: " + df.format(total));
    }

    private void mostrarEntradas() {
        double totalEntradas = 0.0;
        for (RegistroCaixa registro : registros) {
            if (registro.getTipo().equals("Entrada")) {
                totalEntradas += registro.getValor();
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        JOptionPane.showMessageDialog(frame, "Total de Entradas: " + df.format(totalEntradas));
    }

    private void mostrarSaidas() {
        double totalSaidas = 0.0;
        for (RegistroCaixa registro : registros) {
            if (registro.getTipo().equals("Saída")) {
                totalSaidas += registro.getValor();
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        JOptionPane.showMessageDialog(frame, "Total de Saídas: " + df.format(totalSaidas));
    }

    private void mostrarTotal() {
        double total = 0.0;
        for (RegistroCaixa registro : registros) {
            if (registro.getTipo().equals("Entrada")) {
                total += registro.getValor();
            } else {
                total -= registro.getValor();
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        JOptionPane.showMessageDialog(frame, "Total: " + df.format(total));
    }

    private static class RegistroCaixa {
        private String descricao;
        private String tipo;
        private double valor;
        private Date data;

        public RegistroCaixa(String descricao, String tipo, double valor, Date data) {
            this.descricao = descricao;
            this.tipo = tipo;
            this.valor = valor;
            this.data = data;
        }

        public String getDescricao() {
            return descricao;
        }

        public String getTipo() {
            return tipo;
        }

        public double getValor() {
            return valor;
        }

        public Date getData() {
            return data;
        }
    }
}
