import pt.ipleiria.estg.dei.ei.esoft.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EliminarEvento extends JFrame{

    private JList listEventos;
    private JPanel eliminarPanel;
    private JButton eliminarEventoButton;
    private JButton voltarButton;
    private LinkedList<Evento> eventos;
    private DefaultListModel dlEventos;
    private ArrayList<Integer> eventPos = new ArrayList<Integer>();

    public EliminarEvento() {
        super("Eliminar evento");
        eventos = new LinkedList<>();

        readEvento();
        dlEventos = new DefaultListModel();
        updateList();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(eliminarPanel);
        eliminarEventoButton.addActionListener(this::eliminarButtonActionPerformed);
        voltarButton.addActionListener(this::voltarButtonActionPerformed);
        pack();
    }

    public static void main(String[] args) {
        new CriarEvento().setVisible(true);
    }

    private void eliminarButtonActionPerformed(ActionEvent e) {
        int numEvento = listEventos.getSelectedIndex();

        if (numEvento>=0) {
            eliminarEventoButton.setEnabled(true);
            Evento evento = eventos.get(eventPos.get(numEvento)-1);
            evento.setEliminado(true);
            saveEvento();
            updateList();
        } else {
            eliminarEventoButton.setEnabled(false);
        }

        if (eventPos.size() == 0) {
            eliminarEventoButton.setEnabled(false);
        }
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

    private void saveEvento() {
        ObjectOutputStream oos = null;

        try {
            File f = new
                    File(System.getProperty("user.home")+File.separator+"eventos.dat");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(eventos);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Atleta.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
}
