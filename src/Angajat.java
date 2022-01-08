import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Angajat extends JPanel {
    private JButton buton_aprobare;
    private JButton buton_modificare;
    private JButton buton_log_out;
    private JButton buton_vizualizare;
    JFrame frame14 = new JFrame ("Angajat");

    public Angajat(String username, String parola) {

        buton_aprobare = new JButton ("Aprobare Tranzactii");
        buton_modificare = new JButton ("Modificare date clienti");
        buton_log_out = new JButton ("Log Out");
        buton_vizualizare = new JButton ("Vizualizare Clienti");


        frame14.setSize(351, 430);
        frame14.setLayout(null);
        frame14.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame14.setVisible (true);
        frame14.setResizable(false);


        frame14.add(buton_aprobare);
        frame14.add(buton_modificare);
        frame14.add(buton_log_out);
        frame14.add(buton_vizualizare);


        buton_aprobare.setBounds (85, 50, 165, 55);
        buton_modificare.setBounds (85, 120, 165, 55);
        buton_log_out.setBounds (110, 360, 120, 55);
        buton_vizualizare.setBounds (85, 195, 165, 50);
    }
}