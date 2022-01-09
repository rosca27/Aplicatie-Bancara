import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class HR extends JPanel {
    private JButton buton_date;
    private JButton buton_vizualizare;
    private JButton buton_logout;
    JFrame frame17 = new JFrame ("HR");
    public HR(String username, String parola) {

        buton_date = new JButton ("Vizualizare date");
        buton_vizualizare = new JButton ("Vizualizare clienti");
        buton_logout = new JButton ("Log Out");


        frame17.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame17.setSize(426, 298);
        frame17.setLayout(null);
        frame17.setResizable(false);
        frame17.setVisible (true);
        buton_date.setFocusable(false);
        buton_vizualizare.setFocusable(false);
        buton_vizualizare.setFocusable(false);

        frame17.add(buton_date);
        frame17.add(buton_vizualizare);
        frame17.add(buton_logout);


        buton_date.setBounds (65, 65, 115, 60);
        buton_vizualizare.setBounds (210, 65, 135, 60);
        buton_logout.setBounds (150, 175, 115, 40);

        buton_vizualizare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientiHR();
            }
        });

        buton_date.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VizualizareDateAdmin(username, parola);
            }
        });
        buton_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame17.dispose();
                new Interface();
            }
        });
    }

    public static void main(String[] args) {
    }
}
