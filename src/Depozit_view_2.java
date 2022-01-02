import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Depozit_view_2 {
    JFrame frame7 = new JFrame("Vizualizare depozite");
    DefaultListModel v = new DefaultListModel();
    JButton alegere = new JButton("Vizualizeaza depozit");
    JLabel alege = new JLabel("Alegeti depozitul");
    JList lista_conturi = new JList(v);
    public Depozit_view_2(String username, String parola){

        lista_conturi.setBounds(50,80,400,200);
        frame7.add(lista_conturi);
        frame7.setLayout(null);
        frame7.setSize(500,400);
        frame7.setVisible(true);
        alegere.setBounds(170,300,150,50);
        alegere.setFocusable(false);
        alege.setBounds(200,20,200,30);
        alege.setFont(new Font("Times New Roman",Font.PLAIN,18));
        frame7.setResizable(false);
        frame7.add(alege);
        frame7.add(alegere);
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
            String query = "{call vizualizare_depozite(?,?)}";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setString(1,username);
            stmt.setString(2,parola);
            boolean hasResults = stmt.execute();
            System.out.println(hasResults);
            if(hasResults){
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    String x = rs.getString(1);
                    v.addElement(x);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        alegere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String iban2 = lista_conturi.getSelectedValue().toString();
                new Depozit_info(iban2);
            }
        });
    }
}
