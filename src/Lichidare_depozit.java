import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.WindowEvent;

public class Lichidare_depozit {
    JFrame frame4 = new JFrame("Lichidare depozit");
    DefaultListModel v = new DefaultListModel();
    JButton lichidare = new JButton("Lichideaza");
    JLabel alege = new JLabel("Alegeti depozitul");
    JList lista_conturi = new JList(v);

    public String ibann = "";

    public Lichidare_depozit(String username, String parola){

        lista_conturi.setBounds(50,80,400,200);
        frame4.add(lista_conturi);
        frame4.setLayout(null);
        frame4.setSize(500,400);
        frame4.setVisible(true);
        lichidare.setBounds(200,300,100,50);
        lichidare.setFocusable(false);
        alege.setBounds(200,20,200,30);
        alege.setFont(new Font("Times New Roman",Font.PLAIN,18));
        frame4.setResizable(false);
        frame4.add(alege);
        frame4.add(lichidare);

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
        lichidare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    ibann = lista_conturi.getSelectedValue().toString();
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                    String query = "{call lichidare_depo(?)}";
                    CallableStatement stmt = connection.prepareCall(query);
                    stmt.setString(1,ibann);
                    stmt.execute();
                    frame4.dispatchEvent(new WindowEvent(frame4, WindowEvent.WINDOW_CLOSING));

                }catch (SQLException d){
                    d.printStackTrace();
                }
            }
        });

    }
}
