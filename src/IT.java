import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class IT extends JPanel {
    private JButton buton_modificare;
    private JButton buton_logout;
    private JButton buton_vizualizare;
    JFrame frame15 = new JFrame ("IT");

    public IT(String username, String parola) {

        buton_modificare = new JButton ("Modificare date clienti");
        buton_logout = new JButton ("Log Out");
        buton_vizualizare = new JButton("Vizualizare Date");

        frame15.setSize(520, 320);
        frame15.setLayout(null);

        frame15.add(buton_vizualizare);
        frame15.add(buton_modificare);
        frame15.add(buton_logout);
        frame15.setVisible(true);
        frame15.setResizable(false);

        buton_vizualizare.setBounds (55, 65, 175, 60);
        buton_modificare.setBounds (260, 65, 180, 60);
        buton_logout.setBounds (160, 195, 165, 55);
        buton_logout.setFocusable(false);
        buton_modificare.setFocusable(false);

        buton_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame15.dispose();
                new Interface();
            }
        });

        buton_vizualizare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VizualizareDateAdmin(username, parola);
            }
        });
        buton_modificare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModificareClienti();
            }
        });
    }

    public static void main(String[] args) {

    }
}