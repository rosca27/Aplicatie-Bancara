import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;

public class AprobareCard extends JPanel {
    private JList lista_tr;
    private JButton buton_aproba;
    private JButton buton_iesire;
    JFrame frame13 = new JFrame ("Vizualizare Tranzactii");
    DefaultListModel v = new DefaultListModel<>();
    String d = "-----------------";


    public AprobareCard() {

        lista_tr = new JList (v);
        buton_aproba = new JButton ("Aproba");
        buton_iesire = new JButton ("Iesire");
        JLabel tranz = new JLabel ("Vizualizare tranzactii");
        frame13.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame13.setResizable(false);
        frame13.setLayout(null);
        buton_aproba.setFocusable(false);
        buton_iesire.setFocusable(false);

        frame13.setSize(680, 480);
        frame13.add(lista_tr);
        frame13.add(buton_aproba);
        frame13.add(buton_iesire);
        frame13.add(tranz);
        frame13.setVisible(true);

        lista_tr.setBounds (55, 55, 550, 315);
        buton_aproba.setBounds (170, 385, 120, 35);
        buton_iesire.setBounds (400, 385, 120, 35);
        tranz.setBounds (300, 15, 135, 25);

        try{

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
            String query = "select nr_card2, nume2, prenume2 from solicitare_card;";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
                v.addElement("Numar: " + rs.getString(1) + " Nume: " +  rs.getString(2)+ " Prenume: " + rs.getString(3));
            }

        }catch(SQLException d){
            d.printStackTrace();
        }

        buton_aproba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                    int i = lista_tr.getSelectedIndex();
                    String[] x = v.get(i).toString().split(" ");
                    String query = "{call aprobare_card(?,?,?)}";
                    CallableStatement stmt = connection.prepareCall(query);
                    stmt.setString(1,x[3]);
                    stmt.setString(2,x[5]);
                    stmt.setString(3,x[1]);
                    stmt.execute();
                    System.out.println(x[3]);
                    System.out.println(x[5]);
                    System.out.println(x[1]);
                    frame13.dispose();

                }catch(SQLException d){
                    d.printStackTrace();
                }

            }
        });

        buton_iesire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame13.dispose();
            }
        });
    }
    public static void main(String[] args) {
        AprobareCard c = new AprobareCard();
    }

}