import javax.naming.InsufficientResourcesException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class Vizualizare_Card {
    JFrame frame6 = new JFrame("Solicitare Card");
    DefaultListModel v = new DefaultListModel();
    JButton alegere = new JButton("Solicitare");
    JLabel alege = new JLabel("Alegeti Contul");
    JButton vizualizare_c = new JButton("Vizualizare");
    JList lista_conturi = new JList(v);
    JLabel eroare = new JLabel("");

    public Vizualizare_Card(String username, String parola) {
        lista_conturi.setBounds(50, 80, 400, 200);
        frame6.add(lista_conturi);
        frame6.setLayout(null);
        frame6.setSize(500, 400);
        frame6.setVisible(true);
        alegere.setBounds(250, 300, 120, 50);
        alegere.setFocusable(false);
        alege.setBounds(200, 20, 200, 30);
        alege.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        vizualizare_c.setBounds(110,300,120,50);
        vizualizare_c.setFocusable(false);
        frame6.setResizable(false);
        eroare.setForeground(Color.RED);
        eroare.setFont(new Font("Arial",Font.ITALIC,15));
        eroare.setBounds(150,50,250,30);

        frame6.add(eroare);
        frame6.add(vizualizare_c);
        frame6.add(alege);
        frame6.add(alegere);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
            String query = "{call view_cont(?,?)}";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setString(1, username);
            stmt.setString(2, parola);
            boolean hasResults = stmt.execute();
            System.out.println(hasResults);
            if (hasResults) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    String x = rs.getString(3);
                    v.addElement(x);
                }
            }
        } catch (SQLException d) {
        }
        alegere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long nr1, nr2;
                int cvvn;
                String nr1t = "", nr2t = "", nr3t = "";
                nr1 = (long) (Math.random() * (99999999 - 10000000) + 10000000);
                nr2 = (long) (Math.random() * (99999999 - 10000000) + 10000000);
                nr1t = String.valueOf(nr1);
                nr2t = String.valueOf(nr2);
                nr3t = nr1t + nr2t;
                cvvn = (int) (Math.random() * (999 - 100) + 100);
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                    String query2 = "{call card_f(?)}";
                    CallableStatement stmt2 = connection.prepareCall(query2);
                    stmt2.setString(1, lista_conturi.getSelectedValue().toString());
                    stmt2.execute();
                    ResultSet rs2 = stmt2.getResultSet();
                    rs2.next();
                    char res = rs2.getString(1).charAt(0);
                    System.out.println(res);
                        if (res == '0') {
                            String query = "{call solicitare_c(?,?,?,?,?)}";
                            CallableStatement stmt = connection.prepareCall(query);
                            stmt.setString(1, nr3t);
                            stmt.setInt(2, cvvn);
                            stmt.setString(3, username);
                            stmt.setString(4, parola);
                            stmt.setString(5, lista_conturi.getSelectedValue().toString());
                            stmt.execute();
                            frame6.dispose();
                        } else eroare.setText("Card existent pe acest IBAN");
                    }catch(SQLException d){
                        d.printStackTrace();
                    }
                }
        });

        vizualizare_c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ibann2 = lista_conturi.getSelectedValue().toString();
                frame6.dispose();
                new Card(username,parola,ibann2);
            }
        });
    }
}
