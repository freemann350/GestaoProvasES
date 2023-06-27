import javax.swing.*;
import java.awt.event.ActionEvent;

public class GestaoAtletas extends JFrame {
    private JButton voltarButton;
    private JButton criarAtletaButton;
    private JButton editarAtletaButton;
    private JButton consultarHistóricoDeAtletasButton;
    private JButton gestãoDeInscriçõesButton;
    private JButton importarDadosDeAtletaButton;
    private JPanel atletaPanel;

    public GestaoAtletas() {
        super("Janela Principal");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(atletaPanel);
        pack();
        voltarButton.addActionListener(this::voltarButtonActionPerformed);
        criarAtletaButton.addActionListener(this::criarAtletaButtonActionPerformed);
        editarAtletaButton.addActionListener(this::editarAtletaButtonActionPerformed);
    }

    public static void main(String[] args) {
        new GestaoAtletas().setVisible(true);
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
}
