import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class VizualizareDateAdmin {
    JFrame frame7 = new JFrame("Date admin");
    JTextField nume = new JTextField();
    JTextField prenume = new JTextField();
    JTextField adresa = new JTextField();
    JTextField nr_telefon = new JTextField();
    JTextField e_mail = new JTextField();
    JTextField iban = new JTextField();
    JTextField nr_contract = new JTextField();
    JTextField cnp = new JTextField();
    JTextField user = new JTextField();
    JTextField pass = new JTextField();

    JLabel numeL = new JLabel("Nume");
    JLabel prenumeL = new JLabel("Prenume");
    JLabel adresaL = new JLabel("Adresa");
    JLabel nr_telefonL = new JLabel("Numar telefon");
    JLabel e_mailL = new JLabel("Email");
    JLabel ibanL = new JLabel("Iban");
    JLabel nr_contractL = new JLabel("Numar contract");
    JLabel cnpL = new JLabel("CNP");
    JLabel userL = new JLabel("Username");
    JLabel passL = new JLabel("Parola");

    JButton modify = new JButton("Modifica");
    JButton save = new JButton("Salveaza");


    Font font1 = new Font("Arial", Font.BOLD, 16);
    Font font2 = new Font("Aria", Font.PLAIN, 18);

    public VizualizareDateAdmin(String username, String parola) {
        frame7.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame7.setSize(800,600);
        frame7.setLayout(null);
        frame7.setResizable(false);
        frame7.setVisible(true);

        frame7.add(nume);
        frame7.add(prenume);
        frame7.add(adresa);
        frame7.add(nr_telefon);
        frame7.add(e_mail);
        frame7.add(iban);
        frame7.add(nr_contract);
        frame7.add(user);
        frame7.add(pass);
        frame7.add(cnp);

        frame7.add(numeL);
        frame7.add(prenumeL);
        frame7.add(adresaL);
        frame7.add(nr_telefonL);
        frame7.add(e_mailL);
        frame7.add(ibanL);
        frame7.add(nr_contractL);
        frame7.add(userL);
        frame7.add(passL);
        frame7.add(cnpL);
        frame7.add(modify);
        frame7.add(save);



        nume.setEditable(false);
        prenume.setEditable(false);
        cnp.setEditable(false);
        nr_telefon.setEditable(false);
        adresa.setEditable(false);
        e_mail.setEditable(false);
        user.setEditable(false);
        pass.setEditable(false);
        nr_contract.setEditable(false);

        nume.setBounds(100, 50, 250, 30);
        numeL.setBounds(50, 40, 50, 50);

        prenume.setBounds(450, 50, 250, 30);
        prenumeL.setBounds(375, 40, 100, 50);

        cnp.setBounds(100, 100, 250, 30);
        cnpL.setBounds(50, 90, 50, 50);

        nr_telefon.setBounds(450, 100, 250, 30);
        nr_telefonL.setBounds(365, 90, 100, 50);

        adresa.setBounds(100, 150, 600, 30);
        adresaL.setBounds(45, 140, 100, 50);

        e_mail.setBounds(100, 200, 400, 30);
        e_mailL.setBounds(45, 190, 100, 50);

        user.setBounds(450, 240, 250, 30);
        userL.setBounds(380, 230, 150, 50);

        pass.setBounds(480, 290, 250, 30);
        passL.setBounds(420, 280, 150, 50);

        nr_contract.setBounds(150, 250, 130, 30);
        nr_contractL.setBounds(45, 240, 100, 50);

        modify.setBounds(100, 430, 100, 30);
        modify.setFocusable(false);

        save.setBounds(450, 430, 100, 30);
        save.setFocusable(false);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "1234");
            String query = "{call view_admin_info(?,?)}";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setString(1, username);
            stmt.setString(2, parola);
            boolean hasResults = stmt.execute();
            System.out.println(hasResults);
            if(hasResults) {
                ResultSet rs = stmt.getResultSet();
                while(rs.next()) {
                    nume.setText(rs.getString(1));
                    prenume.setText(rs.getString(2));
                    cnp.setText(rs.getString(3));
                    nr_telefon.setText(rs.getString(4));
                    adresa.setText(rs.getString(5));
                    e_mail.setText(rs.getString(6));
                    user.setText(rs.getString(7));
                    pass.setText(rs.getString(8));
                    nr_contract.setText(rs.getString(9));
                }
            }
        }
        catch (SQLException d) {

        }

        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == modify) {
                    nume.setEditable(true);
                    prenume.setEditable(true);
                    cnp.setEditable(true);
                    nr_telefon.setEditable(true);
                    adresa.setEditable(true);
                    e_mail.setEditable(true);
                    user.setEditable(true);
                    pass.setEditable(true);
                    nr_contract.setEditable(true);
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == save) {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "1234");
                        String val1 = nume.getText();
                        String val2 = prenume.getText();
                        String val3 = cnp.getText();
                        String val4 = nr_telefon.getText();
                        String val5 = adresa.getText();
                        String val6 = e_mail.getText();
                        String val7 = user.getText();
                        String val8 = pass.getText();
                        String val9 = nr_contract.getText();

                         String query = "UPDATE Utilizatori set nume ='"+val1+"' ,prenume = '"+val2+"' ,cnp ='"+val3+"',nr_telefon ='"+val4+"'  ,adresa ='"+val5+"' ,e_mail ='"+val6+"' ,username = '"+val7+"',parola ='"+val8+"' ,nr_contract='"+val9+"' where username ='"+username+"'and parola = '"+parola+"'";
                         Statement stmt = connection.createStatement();
                         stmt.executeUpdate(query);
                         nume.setEditable(false);
                         prenume.setEditable(false);
                         cnp.setEditable(false);
                         nr_telefon.setEditable(false);
                         adresa.setEditable(false);
                         e_mail.setEditable(false);
                         user.setEditable(false);
                         pass.setEditable(false);
                         nr_contract.setEditable(false);

                        int res = JOptionPane.showOptionDialog(null, "Date modificate", "Alert", JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE, null, null, null);
                        if (res == 0)
                            frame7.dispose();
                    }
                    catch(SQLException d) {

                    }
                }
            }
        });
    }
    public static void main(String[] args) {
        VizualizareDateAdmin a = new VizualizareDateAdmin("a","b");
    }


}
