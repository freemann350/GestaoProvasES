import pt.ipleiria.estg.dei.ei.esoft.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.Evento;
import pt.ipleiria.estg.dei.ei.esoft.Pais;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriarEvento extends JFrame{
    private JButton criarButton;
    private JButton cancelarButton;
    private JTextField tfNome;
    private JTextField tfDataInicio;
    private JTextField tfDataFim;
    private JTextField tfHoraInicio;
    private JTextField tfLocal;
    private JComboBox cbPais;
    private JPanel criarPanel;
    private LinkedList<Evento> eventos;
    private boolean horaValid,dataInicioValid,dataFimValid,nomeValid,localValid,datasValid;

    public CriarEvento() {
        super("Criar evento");
        eventos = new LinkedList<>();

        loadComboBox();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(criarPanel);
        criarButton.addActionListener(this::criarButtonActionPerformed);
        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
        pack();
    }

    private void criarButtonActionPerformed(ActionEvent e) {
        formValidation();
        if (horaValid && dataInicioValid && dataFimValid && nomeValid && localValid && datasValid) {
            try {
                Evento evento = new Evento(
                        tfNome.getText(),
                        tfDataInicio.getText(),
                        tfDataFim.getText(),
                        tfHoraInicio.getText(),
                        cbPais.getSelectedItem().toString(),
                        tfLocal.getText()
                );

                readEvento();
                saveEvento(evento);

                JOptionPane.showMessageDialog(this, "Evento " + evento.getNome() + " criado com sucesso.");
                this.dispose();
                this.setVisible(false);
                new MenuEventos().setVisible(true);
            } catch (Exception s) {
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
        cbPais.setModel(new DefaultComboBoxModel(Pais.values()));
    }

    private void readEvento() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home")+File.separator+"eventos.dat");
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
    private void saveEvento(Evento evento) {
        ObjectOutputStream oos = null;
        eventos.add(evento);

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

    private void formValidation() {
        //NOME VALIDATION
        if (tfNome.getText().isEmpty()) {
            nomeValid = false;
            JOptionPane.showMessageDialog(null,"O nome não pode ser vazio","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            nomeValid = true;
        }

        //DATAINICIO VALIDATION
        if (tfDataInicio.getText().isEmpty()) {
            nomeValid = false;
            JOptionPane.showMessageDialog(null,"A data de inicio não pode ser vazia","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            nomeValid = true;
        }

        try {
            LocalDate.parse(tfDataInicio.getText(),DateTimeFormatter.ofPattern("d-M-uuuu").withResolverStyle(ResolverStyle.STRICT));
            dataInicioValid = true;
        } catch (DateTimeParseException s) {
            dataInicioValid = false;
            JOptionPane.showMessageDialog(null,"A data de início deve seguir o formato dd-mm-yyyy","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        }

        //DATAFIM VALIDATION
        if (tfDataFim.getText().isEmpty()) {
            nomeValid = false;
            JOptionPane.showMessageDialog(null,"A data de fim não pode ser vazia","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalDate.parse(tfDataFim.getText(),DateTimeFormatter.ofPattern("d-M-uuuu").withResolverStyle(ResolverStyle.STRICT));
            dataFimValid = true;
        } catch (DateTimeParseException s) {
            dataFimValid = false;
            JOptionPane.showMessageDialog(null, "A data de fim deve seguir o formato dd-mm-yyyy", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //DATE VALIDATION
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date dataInicioDate = sdf.parse(tfDataInicio.getText());
            Date dataFimDate = sdf.parse(tfDataFim.getText());

            if (dataInicioDate.after(dataFimDate)) {
                JOptionPane.showMessageDialog(null, "A data de fim tem de ser superior à de início", "Erro", JOptionPane.ERROR_MESSAGE);
                datasValid = false;
                return;
            }
            datasValid = true;
        } catch (Exception s) {
            System.out.println("ERROR");
        }

        //HOUR VALIDATION
        if (tfHoraInicio.getText().isEmpty()) {
            nomeValid = false;
            JOptionPane.showMessageDialog(null,"A hora não pode ser vazia","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            LocalTime.parse(tfHoraInicio.getText());
            horaValid = true;
        } catch (DateTimeParseException | NullPointerException d) {
            horaValid = false;
            JOptionPane.showMessageDialog(null,"A hora tem de seguir o formato HH:MM","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        }

        //LOCAL VALIDATION
        if (tfLocal.getText().isEmpty()) {
            localValid = false;
            JOptionPane.showMessageDialog(null,"O local não pode ser vazio","Erro",JOptionPane.ERROR_MESSAGE);
        } else {
            localValid = true;
        }
    }
}
