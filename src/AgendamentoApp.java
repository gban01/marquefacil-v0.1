import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoApp {
    public static List<Cliente> clientes = new ArrayList<>();
    public Agendamento agendamento;
    private JFrame mainFrame;

    public AgendamentoApp(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        agendamento = new Agendamento();
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        AgendamentoApp app = new AgendamentoApp(mainFrame);
        app.criarInterface();
    }

    public void criarInterface() {
        JFrame frame = new JFrame("Agendamento");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(7, 2));
        frame.add(panelCampos, BorderLayout.NORTH);

        JTextField nomeField = new JTextField();
        JTextField idadeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField numeroField = new JTextField();
        JTextField dataAgendamentoField = new JTextField();
        JTextField horarioField = new JTextField();
        JTextField sexualidadeField = new JTextField();

        panelCampos.add(new JLabel("Nome:"));
        panelCampos.add(nomeField);
        panelCampos.add(new JLabel("Idade:"));
        panelCampos.add(idadeField);
        panelCampos.add(new JLabel("CPF:"));
        panelCampos.add(cpfField);
        panelCampos.add(new JLabel("Número:"));
        panelCampos.add(numeroField);
        panelCampos.add(new JLabel("Data de Agendamento:"));
        panelCampos.add(dataAgendamentoField);
        panelCampos.add(new JLabel("Horário:"));
        panelCampos.add(horarioField);
        panelCampos.add(new JLabel("Sexualidade:"));
        panelCampos.add(sexualidadeField);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.setBounds(720, 570, 250, 60);
        frame.add(salvarButton);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 570, 250, 60);
        frame.add(voltarButton);

        JButton removerClienteButton = new JButton("Remover Cliente");
        removerClienteButton.setBounds(360, 570, 250, 60);
        frame.add(removerClienteButton);

        JMenuItem menuItem = new JMenuItem("Opção");
        menu.add(menuItem);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                int idade = Integer.parseInt(idadeField.getText());
                String cpf = cpfField.getText();
                String numero = numeroField.getText();
                String dataAgendamento = dataAgendamentoField.getText();
                String horario = horarioField.getText();
                String sexualidade = sexualidadeField.getText();

                Cliente cliente = new Cliente(nome, idade, cpf, numero, dataAgendamento, horario, sexualidade);
                clientes.add(cliente);

                JOptionPane.showMessageDialog(frame, "Cliente salvo com sucesso!");
            }
        });

        JTextArea textAreaClientes = new JTextArea();
        textAreaClientes.setEditable(false);
        JScrollPane scrollPaneClientes = new JScrollPane(textAreaClientes);
        frame.add(scrollPaneClientes, BorderLayout.CENTER);

        JMenuItem menuItemMostrarClientes = new JMenuItem("Mostrar Clientes");
        menu.add(menuItemMostrarClientes);

        menuItemMostrarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaClientes.setText("");
                for (Cliente cliente : clientes) {
                    textAreaClientes.append("Nome: " + cliente.getNome() + "\n");
                    textAreaClientes.append("Idade: " + cliente.getIdade() + "\n");
                    textAreaClientes.append("CPF: " + cliente.getCpf() + "\n");
                    textAreaClientes.append("Número: " + cliente.getNumero() + "\n");
                    textAreaClientes.append("Data de Agendamento: " + cliente.getDataAgendamento() + "\n");
                    textAreaClientes.append("Horário: " + cliente.getHorario() + "\n");
                    textAreaClientes.append("Sexualidade: " + cliente.getSexualidade() + "\n");
                    textAreaClientes.append("-----------------------------\n");
                }
            }
        });

        removerClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeCliente = JOptionPane.showInputDialog(frame, "Digite o nome do cliente a ser removido:");
                clientes.removeIf(cliente -> cliente.getNome().equals(nomeCliente));
                JOptionPane.showMessageDialog(frame, "Cliente removido com sucesso!");
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                mainFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }
}
