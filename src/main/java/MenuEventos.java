import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuEventos extends JFrame{
    private JButton voltarButton;
    private JButton criarEventoButton;
    private JButton editarEventoButton;
    private JButton criarProvaButton;
    private JButton editarProvaButton;
    private JButton eliminarEventoButton;
    private JButton eliminarProvaButton;
    private JButton listarPaisesMaisMedalhadosButton;
    private JButton importarDadosDeProvaButton;
    private JButton gestãoDeEventosButton;
    private JPanel gestaoPanel;

    public MenuEventos() {
        super("Janela Principal");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(gestaoPanel);
        pack();
        criarEventoButton.addActionListener(this::criarEventoButtonActionPerformed);
        editarEventoButton.addActionListener(this::editarEventoButtonActionPerformed);
        eliminarEventoButton.addActionListener(this::eliminarEventoButtonActionPerformed);
        voltarButton.addActionListener(this::voltarButtonActionPerformed);
    }

    public static void main(String[] args) {
        new GestaoAtletas().setVisible(true);
    }

    private void voltarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
    }

    private void criarEventoButtonActionPerformed(ActionEvent e) {
        var criarEvento = new CriarEvento();
        criarEvento.setVisible(true);
    }

    private void editarEventoButtonActionPerformed(ActionEvent e) {
        var editarEvento = new EditarEvento();
        editarEvento.setVisible(true);
    }

    private void eliminarEventoButtonActionPerformed(ActionEvent e) {
        var EliminarEvento = new EliminarEvento();
        EliminarEvento.setVisible(true);
    }
}
