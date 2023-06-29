import pt.ipleiria.estg.dei.ei.esoft.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.Pais;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
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
    private boolean nomeValid,dateValid,pesoValid,contactoValid;
    public CriarAtleta() {
        super("Criar atleta");
        atletas = new LinkedList<>();

        loadComboBox();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(criarPanel);
        criarButton.addActionListener(this::criarButtonActionPerformed);
        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
        pack();

    }

    private void criarButtonActionPerformed(ActionEvent e) {
        formValidation();

        if (nomeValid && dateValid && pesoValid && contactoValid) {
            try {
                Atleta atleta = new Atleta(
                        tfNome.getText(),
                        cbPais.getSelectedItem().toString(),
                        cbGenero.getSelectedItem().toString(),
                        tfDataNascimento.getText(),
                        Integer.parseInt(tfPeso.getText()),
                        tfContacto.getText()
                );
                readAtleta();
                saveAtleta(atleta);

                JOptionPane.showMessageDialog(this, "Atleta " + atleta.getNome() + " criado com sucesso.");
                this.dispose();
                this.setVisible(false);
                new MenuAtletas().setVisible(true);
            } catch (Exception s) {
                JOptionPane.showMessageDialog(this,"Dados inválidos","Erro",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cancelarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
        new MenuAtletas().setVisible(true);
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

    private void formValidation() {
        //NOME VALIDATION
        if (tfNome.getText().isEmpty()) {
            nomeValid = false;
            JOptionPane.showMessageDialog(null,"O nome não pode ser vazio","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            nomeValid = true;
        }

        //PESO VALIDATION
        if (tfPeso.getText().isEmpty()) {
            pesoValid = false;
            JOptionPane.showMessageDialog(null,"O peso não pode ser vazio","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            pesoValid = true;
        }

        String text = tfPeso.getText();
        try {
            int d = Integer.parseInt(text);
            pesoValid = true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null,"Peso inválido","Erro",JOptionPane.ERROR_MESSAGE);
            pesoValid = false;
            return;
        }

        //DATANASCIMENTO VALIDATION
        if (tfDataNascimento.getText().isEmpty()) {
            dateValid = false;
            JOptionPane.showMessageDialog(null,"A data de nascimento não pode ser vazia","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalDate.parse(tfDataNascimento.getText(),DateTimeFormatter.ofPattern("d-M-uuuu").withResolverStyle(ResolverStyle.STRICT));
            dateValid = true;
        } catch (DateTimeParseException s) {
            dateValid = false;
            JOptionPane.showMessageDialog(null, "A data de nascimento deve seguir o formato dd-mm-yyyy", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //CONTACTO VALIDATION
        if (tfContacto.getText().isEmpty()) {
            contactoValid = false;
            JOptionPane.showMessageDialog(null,"O contacto não pode ser vazio","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            contactoValid = true;
        }

        try {
            int d = Integer.parseInt(tfContacto.getText());
            contactoValid = true;
        } catch (NumberFormatException nfe) {
            contactoValid = false;
            JOptionPane.showMessageDialog(null,"Contacto inválido","Erro",JOptionPane.ERROR_MESSAGE);
        }
    }
}
