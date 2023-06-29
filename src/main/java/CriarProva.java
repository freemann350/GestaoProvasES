import pt.ipleiria.estg.dei.ei.esoft.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
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
    private boolean escalaoValid,tapetesValid;

    public CriarProva(Evento evento) {
        super("Criar prova");
        provas = new LinkedList<>();

        this.evento = evento;
        loadComboBox();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(provaPanel);
        criarButton.addActionListener(this::criarButtonActionPerformed);
        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
        pack();
    }

    private void criarButtonActionPerformed(ActionEvent e) {
        formValidation();

        if (escalaoValid && tapetesValid) {
            try {
                Prova prova = new Prova(
                    null,
                    this.evento,
                    cbGenero.getSelectedItem().toString(),
                    Integer.parseInt(tfEscalaoPeso.getText()),
                    cbFaixaEtaria.getSelectedItem().toString(),
                    Integer.parseInt(tfTapetes.getText())
                );
                prova.setNome();

                readProva();
                saveProva(prova);
                JOptionPane.showMessageDialog(this,"Prova criada com sucesso.");
                this.dispose();
                this.setVisible(false);
                new MenuEventos().setVisible(true);
            } catch (Exception s){
                JOptionPane.showMessageDialog(this,"Dados inválidos","Erro",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cancelarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
        new MenuEventos().setVisible(true);
    }

    private void loadComboBox() {
        cbGenero.setModel(new DefaultComboBoxModel(Genero.values()));
        cbFaixaEtaria.setModel(new DefaultComboBoxModel(FaixaEtaria.values()));
    }

    private void formValidation() {
        //ESCALAO VALIDATION
        if (tfEscalaoPeso.getText().isEmpty()) {
            escalaoValid = false;
            JOptionPane.showMessageDialog(null,"O escalão de peso não pode ser vazio","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            escalaoValid = true;
        }

        try {
            int d = Integer.parseInt(tfEscalaoPeso.getText());
            escalaoValid = true;
        } catch (NumberFormatException nfe) {
            escalaoValid = false;
            JOptionPane.showMessageDialog(null,"Escalão de peso inválido","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        }

        //TAPETES VALIDATION
        if (tfTapetes.getText().isEmpty()) {
            tapetesValid = false;
            JOptionPane.showMessageDialog(null,"Os tapetes não podem ser vazios","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            tapetesValid = true;
        }

        try {
            int d = Integer.parseInt(tfTapetes.getText());
            tapetesValid = true;
        } catch (NumberFormatException nfe) {
            tapetesValid = false;
            JOptionPane.showMessageDialog(null,"Tapetes inválidos","Erro",JOptionPane.ERROR_MESSAGE);
        }
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
