
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;

public class ViewNotificari extends JPanel {
    private JList lista_not;
    private JButton jcomp2;
    JFrame frame18 = new JFrame("Notificari");

    public ViewNotificari(String username, String parola) {

        DefaultListModel v = new DefaultListModel();
        lista_not = new JList (v);
        jcomp2 = new JButton ("Iesire");


        frame18.setSize(570, 400);
        frame18.setLayout(null);
        frame18.setVisible(true);
        frame18.setResizable(false);


        frame18.add(lista_not);
        frame18.add(jcomp2);


        lista_not.setBounds (60, 30, 420, 235);
        jcomp2.setBounds (205, 290, 105, 40);

        try{

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
            String query = "{call get_mesaj(?,?)}";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setString(1,username);
            stmt.setString(2,parola);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                v.addElement(rs.getString(1));
            }

        }catch (SQLException d){
            d.printStackTrace();
        }

        jcomp2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame18.dispose();
            }
        });
    }
}