import Eventos.CriarEvento;
import Eventos.EditarEvento;
import Eventos.EliminarEvento;
import Provas.CriarProvaEventoSelecao;
import Provas.EditarProvaEventoSelecao;
import Provas.EliminarProvaEventoSelecao;

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
        criarProvaButton.addActionListener(this::criarProvaButtonActionPerformed);
        editarProvaButton.addActionListener(this::editarProvaButtonActionPerformed);
        eliminarProvaButton.addActionListener(this::eliminarProvaButtonActionPerformed);
        voltarButton.addActionListener(this::voltarButtonActionPerformed);
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

    private void criarProvaButtonActionPerformed(ActionEvent e) {
        var criarProva = new CriarProvaEventoSelecao();
        criarProva.setVisible(true);
    }

    private void editarProvaButtonActionPerformed(ActionEvent e) {
        var editarProva = new EditarProvaEventoSelecao();
        editarProva.setVisible(true);
    }

    private void eliminarProvaButtonActionPerformed(ActionEvent e) {
        var eliminarProva = new EliminarProvaEventoSelecao();
        eliminarProva.setVisible(true);
    }
}
