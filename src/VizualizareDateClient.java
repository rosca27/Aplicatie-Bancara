import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;

public class VizualizareDateClient {
    private JTextField jcomp1;
    private JLabel jcomp2;
    private JTextField jcomp3;
    private JLabel jcomp4;
    private JTextField jcomp5;
    private JLabel jcomp6;
    private JTextField jcomp7;
    private JLabel jcomp8;
    private JTextField jcomp9;
    private JLabel jcomp10;
    private JTextField jcomp11;
    private JLabel jcomp12;
    private JTextField jcomp13;
    private JLabel jcomp14;
    private JTextField jcomp15;
    private JTextField jcomp16;
    private JLabel jcomp17;
    private JLabel jcomp18;
    private JButton jcomp19;
    private JButton jcomp20;
    private JTextField jcomp21;
    private JTextField jcomp22;
    private JLabel jcomp23;
    private JLabel jcomp24;
    JFrame frame = new JFrame("Vizualizare date");

    public VizualizareDateClient(String username, String parola) {

        jcomp1 = new JTextField(5);
        jcomp2 = new JLabel("Nume");
        jcomp3 = new JTextField(5);
        jcomp4 = new JLabel("Prenume");
        jcomp5 = new JTextField(5);
        jcomp6 = new JLabel("Adresa");
        jcomp7 = new JTextField(5);
        jcomp8 = new JLabel("CNP");
        jcomp9 = new JTextField(5);
        jcomp10 = new JLabel("Numar telefon");
        jcomp11 = new JTextField(5);
        jcomp12 = new JLabel("Email");
        jcomp13 = new JTextField(5);
        jcomp14 = new JLabel("Data nasterii");
        jcomp15 = new JTextField(5);
        jcomp16 = new JTextField(5);
        jcomp17 = new JLabel("Sursa venit");
        jcomp18 = new JLabel("Tranzactii online");
        jcomp19 = new JButton("Modifica");
        jcomp20 = new JButton("Salveaza");
        jcomp21 = new JTextField (5);
        jcomp22 = new JTextField (5);
        jcomp23 = new JLabel ("Username");
        jcomp24 = new JLabel ("Parola");


        frame.setSize(new Dimension(563, 511));
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        frame.add(jcomp1);
        frame.add(jcomp2);
        frame.add(jcomp3);
        frame.add(jcomp4);
        frame.add(jcomp5);
        frame.add(jcomp6);
        frame.add(jcomp7);
        frame.add(jcomp8);
        frame.add(jcomp9);
        frame.add(jcomp10);
        frame.add(jcomp11);
        frame.add(jcomp12);
        frame.add(jcomp13);
        frame.add(jcomp14);
        frame.add(jcomp15);
        frame.add(jcomp16);
        frame.add(jcomp17);
        frame.add(jcomp18);
        frame.add(jcomp19);
        frame.add(jcomp20);
        frame.add(jcomp21);
        frame.add(jcomp22);
        frame.add(jcomp23);
        frame.add(jcomp24);

        jcomp1.setEditable(false);
        jcomp3.setEditable(false);
        jcomp5.setEditable(false);
        jcomp7.setEditable(false);
        jcomp9.setEditable(false);
        jcomp11.setEditable(false);
        jcomp13.setEditable(false);
        jcomp15.setEditable(false);
        jcomp16.setEditable(false);
        jcomp21.setEditable(false);
        jcomp22.setEditable(false);


        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds(50, 65, 125, 25);
        jcomp2.setBounds(50, 40, 100, 25);
        jcomp3.setBounds(50, 145, 125, 25);
        jcomp4.setBounds(50, 120, 100, 25);
        jcomp5.setBounds(45, 220, 180, 25);
        jcomp6.setBounds(45, 195, 100, 25);
        jcomp7.setBounds(290, 80, 170, 25);
        jcomp8.setBounds(290, 55, 100, 25);
        jcomp9.setBounds(290, 140, 170, 25);
        jcomp10.setBounds(290, 115, 100, 25);
        jcomp11.setBounds(45, 290, 185, 25);
        jcomp12.setBounds(45, 265, 100, 25);
        jcomp13.setBounds(290, 200, 170, 25);
        jcomp14.setBounds(290, 175, 100, 25);
        jcomp15.setBounds(290, 290, 95, 25);
        jcomp16.setBounds(430, 290, 55, 25);
        jcomp17.setBounds(290, 265, 100, 25);
        jcomp18.setBounds(415, 265, 100, 25);
        jcomp19.setBounds(155, 430, 100, 25);
        jcomp20.setBounds(325, 430, 100, 25);
        jcomp21.setBounds (80, 350, 130, 25);
        jcomp22.setBounds (315, 350, 135, 25);
        jcomp23.setBounds (80, 325, 100, 25);
        jcomp24.setBounds (315, 325, 100, 25);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
            String query = "{call view_client_info(?, ?)}";
            CallableStatement stmt = connection.prepareCall(query);

            stmt.setString(1, username);
            stmt.setString(2, parola);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                jcomp1.setText(rs.getString(1));
                jcomp3.setText(rs.getString(2));
                jcomp5.setText(rs.getString(4));
                jcomp7.setText(rs.getString(3));
                jcomp9.setText(rs.getString(5));
                jcomp11.setText(rs.getString(6));
                jcomp13.setText(rs.getString(7));
                jcomp15.setText(rs.getString(8));
                jcomp16.setText(rs.getString(9));
                jcomp21.setText(rs.getString(10));
                jcomp22.setText(rs.getString(11));
            }
        }
        catch (SQLException d) {
            d.printStackTrace();
        }

        jcomp19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == jcomp19) {
                    jcomp1.setEditable(true);
                    jcomp3.setEditable(true);
                    jcomp5.setEditable(true);
                    jcomp7.setEditable(true);
                    jcomp9.setEditable(true);
                    jcomp11.setEditable(true);
                    jcomp13.setEditable(true);
                    jcomp15.setEditable(true);
                    jcomp16.setEditable(true);
                    jcomp21.setEditable(true);
                    jcomp22.setEditable(true);
                }
            }
        });

        jcomp20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String val1 = jcomp1.getText();
                String val2 = jcomp3.getText();
                String val3 = jcomp5.getText();
                String val4 = jcomp7.getText();
                String val5 = jcomp9.getText();
                String val6 = jcomp11.getText();
                String val7 = jcomp13.getText();
                String val8 = jcomp15.getText();
                String val9 = jcomp16.getText();
                String val10 = jcomp21.getText();
                String val11 = jcomp22.getText();

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                    String query = "UPDATE Utilizatori set nume ='"+val1+"' ,prenume = '"+val2+"' ,cnp ='"+val4+"',nr_telefon ='"+val5+"'  ,adresa ='"+val3+"' ,e_mail ='"+val6+"' ,username = '"+val10+"',parola ='"+val11+"' where username ='"+username+"'and parola = '"+parola+"'";
                    String query2 = "UPDATE clienti set username ='"+val10+"', parola = '"+val11+"',adresa ='"+val3+"',data_nasterii ='"+val7+"',sursa_venit ='"+val8+"',tranzactii_online ='"+val9+"' where username ='"+username+"'and parola = '"+parola+"'";
                    Statement stmt = connection.createStatement();
                    Statement stmt2 = connection.createStatement();
                    stmt.executeUpdate(query);
                    stmt2.executeUpdate(query2);

                    jcomp1.setEditable(false);
                    jcomp3.setEditable(false);
                    jcomp5.setEditable(false);
                    jcomp7.setEditable(false);
                    jcomp9.setEditable(false);
                    jcomp11.setEditable(false);
                    jcomp13.setEditable(false);
                    jcomp15.setEditable(false);
                    jcomp16.setEditable(false);
                    jcomp21.setEditable(false);
                    jcomp22.setEditable(false);

                    int res = JOptionPane.showOptionDialog(null, "Date modificate", "Alert", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    if (res == 0)
                        frame.dispose();

                }
                catch(SQLException d) {
                    d.printStackTrace();
                }

            }
        });
    }
    public static void main (String[] args) {

    }
}