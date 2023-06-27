package Eventos;

import pt.ipleiria.estg.dei.ei.esoft.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.Evento;
import pt.ipleiria.estg.dei.ei.esoft.Pais;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
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

    public CriarEvento() {
        super("Criar evento");
        eventos = new LinkedList<>();

        loadComboBox();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(criarPanel);
        criarButton.addActionListener(this::criarButtonActionPerformed);
        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
        pack();
    }

    private void criarButtonActionPerformed(ActionEvent e) {

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

        JOptionPane.showMessageDialog(this,"Evento " + evento.getNome() + " criado com sucesso.");
        this.dispose();
        this.setVisible(false);
    }

    private void cancelarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
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
}
