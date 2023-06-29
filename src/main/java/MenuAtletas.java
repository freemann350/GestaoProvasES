import pt.ipleiria.estg.dei.ei.esoft.Atleta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuAtletas extends JFrame {
    private JButton voltarButton;
    private JButton criarAtletaButton;
    private JButton editarAtletaButton;
    private JButton gestãoDeInscriçõesButton;
    private JButton consultarHistóricoDeAtletasButton;
    private JButton importarDadosDeAtletaButton;
    private JPanel atletaPanel;
    private LinkedList<Atleta> atletas;

    public MenuAtletas() {
        super("Menu de atletas");
        atletas = new LinkedList<>();
        readAtleta();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(atletaPanel);
        pack();
        voltarButton.addActionListener(this::voltarButtonActionPerformed);
        criarAtletaButton.addActionListener(this::criarAtletaButtonActionPerformed);
        editarAtletaButton.addActionListener(this::editarAtletaButtonActionPerformed);
        gestãoDeInscriçõesButton.addActionListener(this::gestaoDeInscriçõesButtonActionPerformed);
        consultarHistóricoDeAtletasButton.addActionListener(this::consultarHistóricoDeAtletasButtonActionPerformed);
        importarDadosDeAtletaButton.addActionListener(this::importarDadosDeAtletaButtonActionPerformed);
    }

    private void voltarButtonActionPerformed(ActionEvent e) {
        var menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        this.dispose();
        this.setVisible(false);
    }

    private void criarAtletaButtonActionPerformed(ActionEvent e) {
        var criarAtleta = new CriarAtleta();
        criarAtleta.setVisible(true);
        this.dispose();
        this.setVisible(false);
    }

    private void editarAtletaButtonActionPerformed(ActionEvent e) {
        if (isAtletasNotEmpty()) {
            var editarAtleta = new EditarAtleta();
            editarAtleta.setVisible(true);
            this.dispose();
            this.setVisible(false);
        }
    }

    private void gestaoDeInscriçõesButtonActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"Em desenvolvimento...");
    }

    private void consultarHistóricoDeAtletasButtonActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"Em desenvolvimento...");
    }

    private void importarDadosDeAtletaButtonActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"Em desenvolvimento...");
    }

    private boolean isAtletasNotEmpty() {
        if (atletas.size()==0) {
            JOptionPane.showMessageDialog(this, "Não existem atletas criados", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
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
}
