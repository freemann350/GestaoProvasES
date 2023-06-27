import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaPrincipal extends JFrame {
    private JPanel mainPanel;
    private JButton sairButton;
    private JButton eventosCompetiçõesButton;
    private JButton atletasButton;

    public JanelaPrincipal(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        eventosCompetiçõesButton.addActionListener(this::eventosCompetiçõesButton);
        atletasButton.addActionListener(this::atletasButtonActionPerformed);
        sairButton.addActionListener(this::sairButtonActionPerformed);
    }

    public static void main(String[] args) {
        new JanelaPrincipal("Janela Principal").setVisible(true);
    }


    private void sairButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void atletasButtonActionPerformed(ActionEvent e) {
        var gestaoAtleta = new GestaoAtletas();
        gestaoAtleta.setVisible(true);
    }

    private void eventosCompetiçõesButton(ActionEvent e) {
        var menuEventos = new MenuEventos();
        menuEventos.setVisible(true);
    }
}
