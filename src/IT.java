import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class IT extends JPanel {
    private JButton buton_modificare;
    private JButton buton_logout;
    JFrame frame15 = new JFrame ("IT");

    public IT() {

        buton_modificare = new JButton ("Modificare date clienti");
        buton_logout = new JButton ("Log Out");


        frame15.setSize(257, 282);
        frame15.setLayout(null);

        frame15.add(buton_modificare);
        frame15.add(buton_logout);
        frame15.setVisible(true);
        frame15.setResizable(false);

        buton_modificare.setBounds (50, 70, 160, 60);
        buton_logout.setBounds (75, 210, 100, 25);
        buton_logout.setFocusable(false);
        buton_modificare.setFocusable(false);

        buton_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame15.dispose();
                new Interface();
            }
        });
    }

    public static void main(String[] args) {
        IT i = new IT();
    }
}