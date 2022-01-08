import java.awt.*;
import java.awt.event.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JOptionPane;
import java.sql.*;

public class PlataFactura {
    private JTextField jcomp1;
    private JLabel jcomp2;
    private JTextField jcomp3;
    private JLabel jcomp4;
    private JTextField jcomp5;
    private JLabel jcomp6;
    private JButton jcomp7;
    private JButton jcomp8;
    private JFrame frame = new JFrame("Plata factura");

    public PlataFactura(String username, String parola) {

        jcomp1 = new JTextField (5);
        jcomp2 = new JLabel ("Numar factura");
        jcomp3 = new JTextField(5);
        jcomp4 = new JLabel ("Furnizor");
        jcomp5 = new JTextField (5);
        jcomp6 = new JLabel ("Suma");
        jcomp7 = new JButton ("Plateste");
        jcomp8 = new JButton ("Exit");

        frame.setSize (new Dimension (500, 430));
        frame.setLayout (null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible (true);


        frame.add(jcomp1);
        frame.add(jcomp2);
        frame.add(jcomp3);
        frame.add(jcomp4);
        frame.add(jcomp5);
        frame.add(jcomp6);
        frame.add(jcomp7);
        frame.add(jcomp8);



        jcomp1.setBounds (60, 80, 160, 25);
        jcomp2.setBounds (60, 55, 100, 25);
        jcomp3.setBounds (60, 155, 160, 25);
        jcomp4.setBounds (60, 130, 100, 25);
        jcomp5.setBounds (60, 245, 160, 25);
        jcomp6.setBounds (60, 220, 100, 25);
        jcomp7.setBounds (300, 160, 100, 25);
        jcomp8.setBounds (90, 350, 100, 25);

        jcomp8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == jcomp8)
                    frame.dispose();
            }
        });

        jcomp7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == jcomp7) {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");

                        String query = "{call plata_factura(?,?,?,?,?)}";
                        CallableStatement stmt = connection.prepareCall(query);

                        stmt.setString(1, username);
                        stmt.setString(2, parola);
                        stmt.setString(3, jcomp3.getText());
                        stmt.setString(4, jcomp1.getText());
                        stmt.setString(5, jcomp5.getText());
                        ResultSet rs = stmt.executeQuery();
                        String alerta = "";

                        while(rs.next()) {
                            alerta = rs.getString("message");
                            int input = JOptionPane.showOptionDialog(null, alerta, "Alert", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                            if (input == JOptionPane.OK_OPTION)
                                JOptionPane.getRootFrame().dispose();
                        }


                    }
                    catch (SQLException d) {
                        d.printStackTrace();
                    }
                }
            }
        });
    }


    public static void main (String[] args) {
    }
}