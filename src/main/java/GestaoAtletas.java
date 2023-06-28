import Atletas.CriarAtleta;
import Atletas.EditarAtleta;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GestaoAtletas extends JFrame {
    private JButton voltarButton;
    private JButton criarAtletaButton;
    private JButton editarAtletaButton;
    private JButton gestãoDeInscriçõesButton;
    private JButton consultarHistóricoDeAtletasButton;
    private JButton importarDadosDeAtletaButton;
    private JPanel atletaPanel;

    public GestaoAtletas() {
        super("Gestão de atletas");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(atletaPanel);
        pack();
        voltarButton.addActionListener(this::voltarButtonActionPerformed);
        criarAtletaButton.addActionListener(this::criarAtletaButtonActionPerformed);
        editarAtletaButton.addActionListener(this::editarAtletaButtonActionPerformed);
        gestãoDeInscriçõesButton.addActionListener(this::gestaoDeInscriçõesButtonActionPerformed);
        consultarHistóricoDeAtletasButton.addActionListener(this::consultarHistóricoDeAtletasButtonActionPerformed);
        importarDadosDeAtletaButton.addActionListener(this::importarDadosDeAtletaButtonActionPerformed);
    }

    private void voltarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
    }

    private void criarAtletaButtonActionPerformed(ActionEvent e) {
        var criarAtleta = new CriarAtleta();
        criarAtleta.setVisible(true);
    }

    private void editarAtletaButtonActionPerformed(ActionEvent e) {
        var editarAtleta = new EditarAtleta();
        editarAtleta.setVisible(true);
    }

    private void gestaoDeInscriçõesButtonActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"In progress...");
    }

    private void consultarHistóricoDeAtletasButtonActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"In progress...");
    }

    private void importarDadosDeAtletaButtonActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"In progress...");
    }
}
