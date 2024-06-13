import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criando um fundo ao noss projeto
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {

                    Image backgroundImage = ImageIO.read(new File("Image//login.png"));


                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        };// fim do fundo

        panel.setLayout(null); // manter a posição dos componetes

        frame.add(panel);

        JTextField loginField = new JTextField();
        loginField.setBounds(400, 200, 200, 30);
        panel.add(loginField);

        JPasswordField senhaField = new JPasswordField();
        senhaField.setBounds(400, 250, 200, 30);
        panel.add(senhaField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(400, 300, 200, 30);
        panel.add(loginButton);

        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String senha = new String(senhaField.getPassword());

                if (login.equals("gerente@1") || login.equals("gerente@2") || login.equals("01DONO")) {
                    if (senha.equals("ADM_EMPRESA")) {
                        JOptionPane.showMessageDialog(frame, "Login realizado com sucesso!");
                        frame.dispose();
                        MenuPrincipal.main(new String[]{});
                    } else {
                        JOptionPane.showMessageDialog(frame, "Senha incorreta. Por favor, tente novamente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário não encontrado. Por favor, tente novamente.");
                }
            }
        });
    }
}
