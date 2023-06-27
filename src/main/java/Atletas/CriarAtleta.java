package Atletas;

import pt.ipleiria.estg.dei.ei.esoft.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.Pais;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriarAtleta extends JFrame {
    private JPanel criarPanel;
    private JTextField tfNome;
    private JComboBox cbPais;
    private JComboBox cbGenero;
    private JTextField tfPeso;
    private JTextField tfDataNascimento;
    private JTextField tfContacto;
    private JButton criarButton;
    private JButton cancelarButton;
    private LinkedList<Atleta> atletas;

    public CriarAtleta() {
        super("Criar atleta");
        atletas = new LinkedList<>();

        loadComboBox();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(criarPanel);
        criarButton.addActionListener(this::criarButtonActionPerformed);
        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
        pack();
    }

    private void criarButtonActionPerformed(ActionEvent e) {

        Atleta atleta = new Atleta(
                tfNome.getText(),
                cbPais.getSelectedItem().toString(),
                cbGenero.getSelectedItem().toString(),
                tfDataNascimento.getText(),
                Float.parseFloat(tfPeso.getText()),
                tfContacto.getText()
        );

        readAtleta();
        saveAtleta(atleta);

        JOptionPane.showMessageDialog(this,"Atleta " + atleta.getNome() + " criado com sucesso.");
        this.dispose();
        this.setVisible(false);
    }
    private void cancelarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
    }

    private void loadComboBox() {
        cbPais.setModel(new DefaultComboBoxModel(Pais.values()));
        cbGenero.setModel(new DefaultComboBoxModel(Genero.values()));
    }

    private void readAtleta() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home")+File.separator+"atletas.dat");
        if (f.canRead()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                atletas = (LinkedList<Atleta>) ois.readObject();
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

    private void saveAtleta(Atleta atleta) {
        ObjectOutputStream oos = null;
        atletas.add(atleta);

        try {
            File f = new
                    File(System.getProperty("user.home")+File.separator+"atletas.dat");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(atletas);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Atleta.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
}
