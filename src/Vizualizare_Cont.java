import javax.naming.InsufficientResourcesException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class Vizualizare_Cont {
    JFrame frame6 = new JFrame("Lichidare cont");
    DefaultListModel v = new DefaultListModel();
    JButton alegere = new JButton("Vizualizare");
    JLabel alege = new JLabel("Alegeti Contul");
    JList lista_conturi = new JList(v);
    public Vizualizare_Cont(String username, String parola){
        lista_conturi.setBounds(50,80,400,200);
        frame6.add(lista_conturi);
        frame6.setLayout(null);
        frame6.setSize(500,400);
        frame6.setVisible(true);
        alegere.setBounds(180,300,120,50);
        alegere.setFocusable(false);
        alege.setBounds(200,20,200,30);
        alege.setFont(new Font("Times New Roman",Font.PLAIN,18));
        frame6.setResizable(false);
        frame6.add(alege);
        frame6.add(alegere);
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
            String query = "{call view_cont(?,?)}";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setString(1,username);
            stmt.setString(2,parola);
            boolean hasResults = stmt.execute();
            System.out.println(hasResults);
            if(hasResults){
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    String x = rs.getString(3);
                    System.out.println(x+ '\n');
                    v.addElement(x);
                }
            }
        }
        catch(SQLException d){

        }


        alegere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ibann2 = lista_conturi.getSelectedValue().toString();
                new Cont_vizualizat(username, parola,ibann2);
            }
        });
    }


}
