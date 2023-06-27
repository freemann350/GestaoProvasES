package Provas;

import pt.ipleiria.estg.dei.ei.esoft.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EliminarProvaEventoSelecao extends JFrame{
    private JList listEventos;
    private JPanel eventosPanel;
    private JButton eliminarProvasButton;
    private JButton voltarButton;
    private LinkedList<Evento> eventos;
    private DefaultListModel dlEventos;
    private ArrayList<Integer> eventPos = new ArrayList<>();

    public EliminarProvaEventoSelecao() {
        super("Seleção de evento");
        eventos = new LinkedList<>();

        readEvento();
        dlEventos = new DefaultListModel();

        updateList();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(eventosPanel);
        eliminarProvasButton.addActionListener(this::editarProvaButtonActionPerformed);
        voltarButton.addActionListener(this::voltarButtonActionPerformed);
        listEventos.setSelectedIndex(0);
        pack();
    }

    private void editarProvaButtonActionPerformed(ActionEvent e) {
        int numEvento = listEventos.getSelectedIndex();

        if (numEvento>=0) {
            Evento evento = eventos.get(eventPos.get(numEvento)-1);

            var eliminarProva = new EliminarProva(evento);
            eliminarProva.setVisible(true);
        }

        this.dispose();
        this.setVisible(false);
    }
    private void voltarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
    }

    private void updateList() {
        dlEventos.removeAllElements();
        eventPos.clear();
        listEventos.setModel(dlEventos);

        int i=0;
        for (Evento evento : eventos) {
            i++;
            if (!evento.isEliminado()) {
                eventPos.add(i);
                dlEventos.addElement(evento.getNome());
            }
        }
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
