package Eventos;

import pt.ipleiria.estg.dei.ei.esoft.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.Evento;
import pt.ipleiria.estg.dei.ei.esoft.Pais;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditarEvento extends JFrame{
    private JList listEventos;
    private JTextField tfNome;
    private JTextField tfDataInicio;
    private JTextField tfDataFim;
    private JTextField tfHora;
    private JComboBox cbPais;
    private JTextField tfLocal;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JPanel editarPanel;
    private LinkedList<Evento> eventos;
    private DefaultListModel dlEventos;
    private ArrayList<Integer> eventPos = new ArrayList<>();


    public EditarEvento() {
        super("Editar atletas");
        eventos = new LinkedList<>();

        readEvento();

        dlEventos = new DefaultListModel();

        updateList();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(editarPanel);
        pack();
        loadComboBox();

        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
        guardarButton.addActionListener(this::guardarButtonActionPerformed);
        listEventos.addListSelectionListener(this::eventoListSelectionEvent);
        guardarButton.setEnabled(false);
        listEventos.setSelectedIndex(0);
    }

    private void guardarButtonActionPerformed(ActionEvent e) {
        int numEvento = listEventos.getSelectedIndex();

        if (numEvento>=0) {
            Evento evento = eventos.get(eventPos.get(numEvento)-1);

            evento.setNome(tfNome.getText());
            evento.setDataInicio(tfDataInicio.getText());
            evento.setDataFim(tfDataFim.getText());
            evento.setPais(cbPais.getSelectedItem().toString());
            evento.setHora(tfHora.getText());
            evento.setLocal(tfLocal.getText());

            eventos.set(eventPos.get(numEvento)-1,evento);

            saveEvento();

            updateList();
            listEventos.setSelectedIndex(numEvento);
            JOptionPane.showMessageDialog(this,"Evento " + evento.getNome() + " editado com sucesso.");
        }
    }

    private void cancelarButtonActionPerformed(ActionEvent e) {
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

    private void eventoListSelectionEvent(ListSelectionEvent e) {
        int numEvento = listEventos.getSelectedIndex();

        if (numEvento>=0) {
            Evento evento = eventos.get(eventPos.get(numEvento)-1);
            tfNome.setText(evento.getNome());
            cbPais.getModel().setSelectedItem(evento.getPais());
            tfDataInicio.setText(evento.getDataFim());
            tfDataFim.setText(evento.getDataFim());
            tfHora.setText(evento.getDataFim());
            tfLocal.setText(evento.getLocal());
            guardarButton.setEnabled(true);
        } else {
            guardarButton.setEnabled(false);
        }
    }

    private void loadComboBox() {
        cbPais.setModel(new DefaultComboBoxModel(Pais.values()));
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
