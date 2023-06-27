import pt.ipleiria.estg.dei.ei.esoft.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.Pais;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditarAtleta extends JFrame{
    private JPanel editarPanel;
    private JList listAtletas;
    private JTextField tfNome;
    private JComboBox cbPais;
    private JComboBox cbGenero;
    private JTextField tfPeso;
    private JTextField tfDataNascimento;
    private JTextField tfContacto;
    private JButton guardarButton;
    private JButton cancelarButton;
    private LinkedList<Atleta> atletas;
    private DefaultListModel dlAtletas;

    public EditarAtleta() {
        super("Janela Principal");
        atletas = new LinkedList<>();

        readAtleta();

        dlAtletas = new DefaultListModel();

        updateList();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(editarPanel);
        pack();
        loadComboBox();

        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
        guardarButton.addActionListener(this::guardarButtonActionPerformed);
        listAtletas.addListSelectionListener(this::atletaListSelectionEvent);
        guardarButton.setEnabled(false);
        listAtletas.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new EditarAtleta().setVisible(true);
    }

    private void guardarButtonActionPerformed(ActionEvent e) {
        int numAtleta = listAtletas.getSelectedIndex();

        if (numAtleta>=0) {
            Atleta atleta = atletas.get(numAtleta);
            atleta.setNome(tfNome.getText());
            atleta.setPais(cbPais.getSelectedItem().toString());
            atleta.setGenero(cbGenero.getSelectedItem().toString());
            atleta.setPeso(Float.parseFloat(tfPeso.getText()));
            atleta.setDataNascimento(tfDataNascimento.getText());
            atleta.setContacto(tfContacto.getText());

            System.out.println(atleta);

            atletas.set(numAtleta,atleta);

            saveAtleta();

            updateList();
            listAtletas.setSelectedIndex(numAtleta);
            JOptionPane.showMessageDialog(this,"Atleta " + atleta.getNome() + " editado com sucesso.");
        }
    }
    private void cancelarButtonActionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
    }

    private void updateList() {
        dlAtletas.removeAllElements();
        listAtletas.setModel(dlAtletas);

        for (Atleta atleta : atletas) {
            dlAtletas.addElement(atleta.getNome());
        }
    }

    private void atletaListSelectionEvent(ListSelectionEvent e) {
        int numAtleta = listAtletas.getSelectedIndex();

        if (numAtleta>=0) {
            Atleta atleta = atletas.get(numAtleta);
            tfNome.setText(atleta.getNome());
            cbPais.getModel().setSelectedItem(atleta.getPais());
            cbGenero.getModel().setSelectedItem(atleta.getGenero());
            tfPeso.setText(String.valueOf(atleta.getPeso()));
            tfDataNascimento.setText(atleta.getDataNascimento());
            tfContacto.setText(atleta.getContacto());
            guardarButton.setEnabled(true);
        } else {
            guardarButton.setEnabled(false);
        }
    }

    private void loadComboBox() {
        cbPais.setModel(new DefaultComboBoxModel(Pais.values()));
        cbGenero.setModel(new DefaultComboBoxModel(Genero.values()));
    }

    private void readAtleta() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home")+ File.separator+"atletas.dat");

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

    private void saveAtleta() {
        ObjectOutputStream oos = null;

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
