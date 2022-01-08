import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Functionar extends JPanel {
    private JButton buton_logut;
    private JButton buton_aprobari;
    private JButton buton_vizualizare;
    private JFrame frame16 = new JFrame("Functionar");

    public Functionar(String username, String parola) {

        buton_logut = new JButton ("Log Out");
        buton_aprobari = new JButton ("Aprobari");
        buton_vizualizare = new JButton ("Vizualizare date");
        frame16.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);


        frame16.setSize(438, 330);
        frame16.setVisible (true);
        frame16.setLayout(null);
        frame16.setResizable(false);


        frame16.add(buton_logut);
        frame16.add(buton_aprobari);
        frame16.add(buton_vizualizare);
        frame16.setVisible(true);


        buton_logut.setBounds (155, 235, 115, 45);
        buton_aprobari.setBounds (55, 90, 135, 55);
        buton_vizualizare.setBounds (240, 90, 140, 55);
        buton_logut.setFocusable(false);
        buton_vizualizare.setFocusable(false);
        buton_aprobari.setFocusable(false);

        buton_aprobari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VizualizareTranzactii();
            }
        });
        buton_vizualizare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VizualizareDateAdmin(username, parola);
            }
        });

        buton_logut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame16.dispose();
                new Interface();
            }
        });
    }

}