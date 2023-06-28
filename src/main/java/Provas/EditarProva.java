package Provas;

import pt.ipleiria.estg.dei.ei.esoft.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditarProva extends JFrame {
    private JPanel editarPanel;
    private JList listProvas;
    private JTextField tfEscalaoPeso;
    private JTextField tfTapetes;
    private JComboBox cbGenero;
    private JComboBox cbFaixaEtaria;
    private JButton guardarButton;
    private JButton cancelarButton;
    private static Evento evento;
    private LinkedList<Prova> provas;
    private ArrayList<Integer> provaPos = new ArrayList<>();
    private DefaultListModel dlProvas;

    public EditarProva(Evento evento) {
        super("Editar atletas");
        provas = new LinkedList<>();
        this.evento = evento;

        readProva();
        dlProvas = new DefaultListModel();
        updateList();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(editarPanel);
        pack();
        loadComboBox();

        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
        guardarButton.addActionListener(this::guardarButtonActionPerformed);
        listProvas.addListSelectionListener(this::provaListSelectionEvent);
        guardarButton.setEnabled(false);
        listProvas.setSelectedIndex(0);
    }

    private void guardarButtonActionPerformed(ActionEvent e) {
        int numEvento = listProvas.getSelectedIndex();

        if (numEvento>=0) {
            Prova prova = provas.get(provaPos.get(numEvento)-1);

            String name = "Prova " +  tfEscalaoPeso.getText() + "Kg " + cbFaixaEtaria.getSelectedItem().toString() +""+ (cbGenero.getSelectedItem().toString() == "MASCULINO" ? "(M)":"(F)");
            prova.setNome();
            prova.setGenero(cbGenero.getSelectedItem().toString());
            prova.setEscalaoPeso(Integer.parseInt(tfEscalaoPeso.getText()));
            prova.setFaixaEtaria(cbFaixaEtaria.getSelectedItem().toString());
            prova.setTapetes(Integer.parseInt(tfTapetes.getText()));

            provas.set(provaPos.get(numEvento)-1,prova);

            saveProva();

            updateList();
            listProvas.setSelectedIndex(numEvento);
            JOptionPane.showMessageDialog(this,"Prova " + prova.getNome() + " editada com sucesso.");
        }
    }

    private void cancelarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
    }

    private void provaListSelectionEvent(ListSelectionEvent e) {
        int numEvento = listProvas.getSelectedIndex();

        if (numEvento>=0) {
            Prova prova = provas.get(provaPos.get(numEvento)-1);
            cbGenero.getModel().setSelectedItem(prova.getGenero());
            tfEscalaoPeso.setText(String.valueOf(prova.getEscalaoPeso()));
            cbFaixaEtaria.getModel().setSelectedItem(prova.getFaixaEtaria());
            tfTapetes.setText(String.valueOf(prova.getTapetes()));
            guardarButton.setEnabled(true);
        } else {
            guardarButton.setEnabled(false);
        }
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
    private void loadComboBox() {
        cbGenero.setModel(new DefaultComboBoxModel(Genero.values()));
        cbFaixaEtaria.setModel(new DefaultComboBoxModel(FaixaEtaria.values()));
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
