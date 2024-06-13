import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal {
    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Menu");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("Image//MenuPrincipal.png"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        panel.setLayout(null);

        frame.add(panel);

        JButton AgendamentoButton = new JButton("Agendamento");
        AgendamentoButton.setBounds(380, 180, 250, 60);
        AgendamentoButton.setBackground(Color.white);
        AgendamentoButton.setOpaque(true);
        panel.add(AgendamentoButton);

        JButton caixaButton = new JButton("Caixa");
        caixaButton.setBounds(380, 260, 250, 60);
        caixaButton.setBackground(Color.white);
        caixaButton.setOpaque(true);
        panel.add(caixaButton);

        JButton estoquesButton = new JButton("Estoques");
        estoquesButton.setBounds(380, 340, 250, 60);
        estoquesButton.setBackground(Color.WHITE);
        estoquesButton.setOpaque(true);
        panel.add(estoquesButton);

        JButton SiteButton = new JButton("Site");
        SiteButton.setBounds(380, 420, 250, 60);
        SiteButton.setBackground(Color.WHITE);
        SiteButton.setOpaque(true);
        panel.add(SiteButton);

        AgendamentoApp agendamentoApp = new AgendamentoApp(frame);
        AgendamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                agendamentoApp.criarInterface();
            }
        });

        CaixaApp caixaApp = new CaixaApp(frame);
        caixaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                caixaApp.createAndShowGUI();
            }
        });

        EstoqueApp estoqueApp = new EstoqueApp(frame);
        estoquesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                estoqueApp.createAndShowGUI();
            }
        });

        SiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://radiant-tartufo-ad1428.netlify.app"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }
}
