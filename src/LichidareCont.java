import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.WindowEvent;

public class LichidareCont {

    JFrame frame4 = new JFrame("Lichidare cont");
    DefaultListModel v = new DefaultListModel();
    JButton lichidare = new JButton("Lichideaza");
    JLabel alege = new JLabel("Alegeti Contul");
    JList lista_conturi = new JList(v);

    public String ibann = "";

    public LichidareCont(String username, String parola){


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

        lichidare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == lichidare){
                    ibann = lista_conturi.getSelectedValue().toString();
                    Connection connection2 = null;
                    try {
                        connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                        String query2 = "{call lichidare_cont(?,?,?)}";
                        CallableStatement stmt = connection2.prepareCall(query2);
                        stmt.setString(1,username);
                        stmt.setString(2,parola);
                        stmt.setString(3,ibann);
                        stmt.execute();
                        frame4.dispatchEvent(new WindowEvent(frame4, WindowEvent.WINDOW_CLOSING));

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });

    }

    public static void main(String[] args) {
    }
}
