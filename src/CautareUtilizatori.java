import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;

public class CautareUtilizatori extends JPanel {
    private JList lista_u;
    private JList lista_u2;
    private JTextField text_cauta;
    private JButton buton_cauta;
    JFrame frame12 = new JFrame ("Cautare utilizatori");
    private JButton buton_resetare;

    public CautareUtilizatori() {

        DefaultListModel v =new DefaultListModel<>();
        DefaultListModel c =new DefaultListModel<>();
        lista_u = new JList (v);
        lista_u2 = new JList (c);
        text_cauta = new JTextField (0);
        buton_cauta = new JButton ("Cauta");
        buton_resetare = new JButton ("Resetare");

        frame12.setSize(373, 480);
        frame12.setLayout (null);
        frame12.setResizable(false);

        lista_u2.setVisible(false);

        frame12.add(lista_u2);
        frame12.add(buton_resetare);
        frame12.add(lista_u);
        frame12.add(text_cauta);
        frame12.add(buton_cauta);
        frame12.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame12.setVisible (true);
        buton_cauta.setFocusable(false);
        buton_resetare.setFocusable(false);
        lista_u.setVisible(true);



        lista_u.setBounds (65, 70, 230, 280);
        lista_u2.setBounds (65, 70, 230, 280);
        text_cauta.setBounds (65, 20, 230, 30);
        buton_cauta.setBounds (185, 380, 110, 35);
        buton_resetare.setBounds (65, 380, 100, 35);

        try{

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
            String query = "select distinct u.nume, u.prenume from utilizatori as u,clienti as c where u.id = c.utilizator_id;";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.execute();
            ResultSet rs =stmt.getResultSet();
            while (rs.next()){
                v.addElement(rs.getString(1) + " " + rs.getString(2));
            }

        }catch(SQLException d){
            d.printStackTrace();
        }
        buton_cauta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = text_cauta.getText().toString();

                try {
                    c.clear();
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                    String query = "{call search(?)}";
                    CallableStatement stmt = connection.prepareCall(query);
                    stmt.setString(1,text);
                    stmt.execute();
                    ResultSet rs = stmt.getResultSet();
                    while(rs.next()){
                        c.addElement(rs.getString(1) + " " + rs.getString(2));
                    }
                    lista_u.setVisible(false);
                    lista_u2.setVisible(true);

                }catch(SQLException d){
                    d.printStackTrace();
                }
            }
        });

        buton_resetare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lista_u.setVisible(true);
                lista_u2.setVisible(false);
            }
        });
    }


    public static void main(String[] args) {
        CautareUtilizatori c = new CautareUtilizatori();
    }
}