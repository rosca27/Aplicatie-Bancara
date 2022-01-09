import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;

public class VizualizareTranzactii extends JPanel {
    private JList lista_tr;
    private JButton buton_aproba;
    private JButton buton_iesire;
    JFrame frame13 = new JFrame ("Vizualizare Tranzactii");
    DefaultListModel v = new DefaultListModel<>();
    String d = "-----------------";


    public VizualizareTranzactii() {

        lista_tr = new JList (v);
        buton_aproba = new JButton ("Aproba");
        buton_iesire = new JButton ("Iesire");
        JLabel tranz = new JLabel ("Vizualizare tranzactii");
        frame13.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
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
            String query = "select expeditor, destinatar, suma, aprobat from transfer;";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
                v.addElement("Destinatar: " + rs.getString(1) + " Expeditor: " +  rs.getString(2)+ " Suma: " + rs.getString(3) + " Status: " + rs.getString(4));
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
                    int suma = Integer.valueOf(x[5]);
                    String query = "{call aprobare_t(?,?,?)}";
                    CallableStatement stmt = connection.prepareCall(query);
                    stmt.setString(1,x[1]);
                    stmt.setString(2,x[3]);
                    stmt.setInt(3,suma);
                    stmt.execute();
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
        VizualizareTranzactii c = new VizualizareTranzactii();
    }

}