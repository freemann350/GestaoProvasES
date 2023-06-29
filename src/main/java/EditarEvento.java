import pt.ipleiria.estg.dei.ei.esoft.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.Evento;
import pt.ipleiria.estg.dei.ei.esoft.Pais;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Date;
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
    private boolean horaValid=true,dataInicioValid=true,dataFimValid=true,nomeValid,localValid,datasValid;


    public EditarEvento() {
        super("Editar eventos");
        eventos = new LinkedList<>();

        readEvento();

        dlEventos = new DefaultListModel();

        updateList();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
            formValidation();
            if (horaValid && dataInicioValid && dataFimValid && datasValid && nomeValid && localValid) {
                try {
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
                } catch (Exception s) {
                    JOptionPane.showMessageDialog(this,"Dados inválidos","Erro",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void cancelarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
        new MenuEventos().setVisible(true);
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

        if (numEvento>=0) { //ATUALIZAR DADOS
            Evento evento = eventos.get(eventPos.get(numEvento)-1);
            tfNome.setText(evento.getNome());
            cbPais.getModel().setSelectedItem(evento.getPais());
            tfDataInicio.setText(evento.getDataInicio());
            tfDataFim.setText(evento.getDataFim());
            tfHora.setText(evento.getHora());
            tfLocal.setText(evento.getLocal());
            guardarButton.setEnabled(true);
        } else {
            guardarButton.setEnabled(false);
        }
    }

    private void loadComboBox() {
        cbPais.setModel(new DefaultComboBoxModel(Pais.values()));
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
        if (tfHora.getText().isEmpty()) {
            nomeValid = false;
            JOptionPane.showMessageDialog(null,"A hora não pode ser vazia","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            LocalTime.parse(tfHora.getText());
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
