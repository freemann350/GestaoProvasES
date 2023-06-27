package Provas;

import pt.ipleiria.estg.dei.ei.esoft.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriarProva extends JFrame {
    private JPanel provaPanel;
    private JButton criarButton;
    private JButton cancelarButton;
    private JComboBox cbGenero;
    private JTextField tfEscalaoPeso;
    private JComboBox cbFaixaEtaria;
    private JTextField tfTapetes;
    private static Evento evento;
    private LinkedList<Prova> provas;

    public CriarProva(Evento evento) {
        super("Criar prova");
        provas = new LinkedList<>();

        this.evento = evento;
        loadComboBox();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(provaPanel);
        criarButton.addActionListener(this::criarButtonActionPerformed);
        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
        pack();
    }

    private void criarButtonActionPerformed(ActionEvent e) {

        String name = "Prova " +  tfEscalaoPeso.getText() + "Kg " + cbFaixaEtaria.getSelectedItem().toString() +" "+ (cbGenero.getSelectedItem().toString() == "MASCULINO" ? "(M)":"(F)");

        Prova prova = new Prova(
                name,
                this.evento,
                cbGenero.getSelectedItem().toString(),
                Integer.parseInt(tfEscalaoPeso.getText()),
                cbFaixaEtaria.getSelectedItem().toString(),
                Integer.parseInt(tfTapetes.getText())
        );
        readProva();
        saveProva(prova);

        JOptionPane.showMessageDialog(this,"Prova criada com sucesso.");
        this.dispose();
        this.setVisible(false);
    }

    private void cancelarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
    }

    private void loadComboBox() {
        cbGenero.setModel(new DefaultComboBoxModel(Genero.values()));
        cbFaixaEtaria.setModel(new DefaultComboBoxModel(FaixaEtaria.values()));
    }

    private void readProva() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home")+File.separator+"provas.dat");
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

    private void saveProva(Prova prova) {
        ObjectOutputStream oos = null;
        provas.add(prova);

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
