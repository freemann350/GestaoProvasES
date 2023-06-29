import pt.ipleiria.estg.dei.ei.esoft.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.Evento;
import pt.ipleiria.estg.dei.ei.esoft.Prova;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EliminarProva extends JFrame {
    private JList listProvas;
    private JButton eliminarProvaButton;
    private JButton voltarButton;
    private JPanel eliminarPanel;
    private Evento evento;
    private LinkedList<Prova> provas;
    private DefaultListModel dlProvas;
    private ArrayList<Integer> provaPos = new ArrayList<>();

    public EliminarProva(Evento evento) {
        super("Eliminar provas");
        provas = new LinkedList<>();
        this.evento = evento;

        readProva();
        dlProvas = new DefaultListModel();
        updateList();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(eliminarPanel);
        eliminarProvaButton.addActionListener(this::eliminarButtonActionPerformed);
        voltarButton.addActionListener(this::voltarButtonActionPerformed);
        pack();
        listProvas.setSelectedIndex(0);
    }

    private void eliminarButtonActionPerformed(ActionEvent e) {
        int numEvento = listProvas.getSelectedIndex();

        if (numEvento>=0) {
            eliminarProvaButton.setEnabled(true);
            Prova prova = provas.get(provaPos.get(numEvento)-1);
            int opt = JOptionPane.showConfirmDialog(this,"Tem a certeza que quer eliminar a " + prova.getNome() +"?","Erro",JOptionPane.WARNING_MESSAGE);

            if (opt == JOptionPane.YES_OPTION) {
                prova.setEliminado(true);
                saveProva();
                updateList();
                JOptionPane.showMessageDialog(this,"Prova eliminada com sucesso");
            }
        } else {
            eliminarProvaButton.setEnabled(false);
        }

        if (provaPos.size() == 0) {
            eliminarProvaButton.setEnabled(false);
        }
    }

    private void voltarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
        new MenuEventos().setVisible(true);
    }

    private void updateList() {
        dlProvas.removeAllElements();
        provaPos.clear();
        listProvas.setModel(dlProvas);

        int i=0;
        for (Prova prova : provas) {
            i++;
            if (!prova.isEliminado() && prova.getEvento().equals(this.evento)) {
                provaPos.add(i);
                dlProvas.addElement(prova.getNome());
            }
        }
    }

    private void readProva() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home")+ File.separator+"provas.dat");

        if (f.canRead()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                provas = (LinkedList<Prova>) ois.readObject();
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

    private void saveProva() {
        ObjectOutputStream oos = null;

        try {
            File f = new
                    File(System.getProperty("user.home")+File.separator+"provas.dat");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(provas);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Atleta.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
}
