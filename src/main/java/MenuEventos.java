import pt.ipleiria.estg.dei.ei.esoft.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuEventos extends JFrame{
    private JButton voltarButton;
    private JButton criarEventoButton;
    private JButton editarEventoButton;
    private JButton eliminarEventoButton;
    private JButton criarProvaButton;
    private JButton editarProvaButton;
    private JButton eliminarProvaButton;
    private JButton listarPaisesMaisMedalhadosButton;
    private JButton importarDadosDeProvaButton;
    private JButton gestãoDeEventosButton;
    private JPanel gestaoPanel;
    private LinkedList<Evento> eventos;

    public MenuEventos() {
        super("Menu de eventos e competições");
        eventos = new LinkedList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(gestaoPanel);
        pack();
        criarEventoButton.addActionListener(this::criarEventoButtonActionPerformed);
        editarEventoButton.addActionListener(this::editarEventoButtonActionPerformed);
        eliminarEventoButton.addActionListener(this::eliminarEventoButtonActionPerformed);
        criarProvaButton.addActionListener(this::criarProvaButtonActionPerformed);
        editarProvaButton.addActionListener(this::editarProvaButtonActionPerformed);
        eliminarProvaButton.addActionListener(this::eliminarProvaButtonActionPerformed);
        gestãoDeEventosButton.addActionListener(this::gestaoDeEventosButtonActionPerformed);
        importarDadosDeProvaButton.addActionListener(this::importarDadosDeProvaButtonActionPerformed);
        listarPaisesMaisMedalhadosButton.addActionListener(this::listarPaisesMaisMedalhadosButtonActionPerformed);
        voltarButton.addActionListener(this::voltarButtonActionPerformed);
    }

    private void voltarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
        var menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
    }

    private void criarEventoButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
        var criarEvento = new CriarEvento();
        criarEvento.setVisible(true);
    }

    private void editarEventoButtonActionPerformed(ActionEvent e) {
        if (isEventosActive()) {
            this.dispose();
            this.setVisible(false);
            var editarEvento = new EditarEvento();
            editarEvento.setVisible(true);
        }
    }

    private void eliminarEventoButtonActionPerformed(ActionEvent e) {
        if (isEventosActive()) {
            var EliminarEvento = new EliminarEvento();
            EliminarEvento.setVisible(true);
            this.dispose();
            this.setVisible(false);
        }
    }

    private void criarProvaButtonActionPerformed(ActionEvent e) {
        if (isEventosActive()) {
            var criarProva = new CriarProvaEventoSelecao();
            criarProva.setVisible(true);
            this.dispose();
            this.setVisible(false);
        }
    }

    private void editarProvaButtonActionPerformed(ActionEvent e) {
        if (isEventosActive()) {
            var editarProva = new EditarProvaEventoSelecao();
            editarProva.setVisible(true);
            this.dispose();
            this.setVisible(false);
        }
    }

    private void eliminarProvaButtonActionPerformed(ActionEvent e) {
        if (isEventosActive()) {
            var eliminarProva = new EliminarProvaEventoSelecao();
            eliminarProva.setVisible(true);
            this.dispose();
            this.setVisible(false);
        }
    }
    private void gestaoDeEventosButtonActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"Em desenvolvimento...");
    }
    private void importarDadosDeProvaButtonActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"Em desenvolvimento...");
    }
    private void listarPaisesMaisMedalhadosButtonActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"Em desenvolvimento...");
    }

    private boolean isEventosActive() {
        readEvento();

        for (Evento evento : eventos) {
            if (!evento.isEliminado())
                return true;
        }

        JOptionPane.showMessageDialog(this, "Não existem eventos criados", "ERRO", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    private void readEvento() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home")+ File.separator+"eventos.dat");

        if (f.canRead()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                eventos = (LinkedList<Evento>) ois.readObject();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(Atleta.class.getName()).log(Level.SEVERE,
                        null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Atleta.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }

}
