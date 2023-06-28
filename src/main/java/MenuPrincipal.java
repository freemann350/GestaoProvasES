import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {
    private JPanel mainPanel;
    private JButton sairButton;
    private JButton eventosCompetiçõesButton;
    private JButton atletasButton;

    public MenuPrincipal(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        eventosCompetiçõesButton.addActionListener(this::eventosCompetiçõesActionPerformed);
        atletasButton.addActionListener(this::atletasButtonActionPerformed);
        sairButton.addActionListener(this::sairButtonActionPerformed);
    }

    public static void main(String[] args) {
        new MenuPrincipal("Menu Principal").setVisible(true);
    }


    private void sairButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void atletasButtonActionPerformed(ActionEvent e) {
        var gestaoAtleta = new GestaoAtletas();
        gestaoAtleta.setVisible(true);
    }

    private void eventosCompetiçõesActionPerformed(ActionEvent e) {
        var menuEventos = new MenuEventos();
        menuEventos.setVisible(true);
    }
}
