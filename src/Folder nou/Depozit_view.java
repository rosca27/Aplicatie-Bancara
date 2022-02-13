import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;

public class Depozit_view {

    JFrame frame7 = new JFrame("Deschidere depozit");
    DefaultListModel v = new DefaultListModel();
    JButton alegere = new JButton("Deschidere depozit");
    JLabel alege = new JLabel("Alegeti Contul");
    JList lista_conturi = new JList(v);
    JLabel eroare = new JLabel("");

    public Depozit_view(String username, String parola){
        lista_conturi.setBounds(50,80,400,200);
        frame7.add(lista_conturi);
        frame7.setLayout(null);
        frame7.setSize(500,400);
        frame7.setVisible(true);
        alegere.setBounds(170,300,150,50);
        alegere.setFocusable(false);
        alege.setBounds(200,20,200,30);
        alege.setFont(new Font("Times New Roman",Font.PLAIN,18));
        eroare.setFont(new Font("Arial",Font.ITALIC,15));
        eroare.setBounds(120,50,300,30);
        eroare.setForeground(Color.RED);

        frame7.setResizable(false);
        frame7.add(eroare);
        frame7.add(alege);
        frame7.add(alegere);
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
                    v.addElement(x);
                }
            }
        }
        catch(SQLException d){

        }
        alegere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String iban2 = lista_conturi.getSelectedValue().toString();
                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                    String query = "{call depo_f(?)}";
                    CallableStatement stmt = connection.prepareCall(query);
                    stmt.setString(1,iban2);
                    stmt.execute();
                    ResultSet rs = stmt.getResultSet();
                    rs.next();
                    char res = rs.getString(1).charAt(0);
                    if(res == '0') {
                        new Creare_depozit(username, parola, iban2);
                        frame7.dispose();
                    }
                        else
                            eroare.setText("Exista deja un depozit pe acest IBAN");
                }catch(SQLException d){
                    d.printStackTrace();
                }

            }
        });
    }

}
