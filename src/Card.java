import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Card {

    JFrame frame7 = new JFrame("Detaliile cardului");
    JTextField titular_card = new JTextField();
    JTextField numar_identificare = new JTextField();
    JTextField cvv = new JTextField();
    JTextField exp = new JTextField();
    JLabel titular_c = new JLabel("Titular Card");
    JLabel cvv_c = new JLabel("CVV");
    JLabel nr_identificare = new JLabel("Numar identificare");
    JLabel data_e = new JLabel("Data expirarii");
    JButton exit = new JButton("Iesire");
    public Card(String username, String parola,String ibann){

        frame7.setVisible(true);
        frame7.setLayout(null);
        frame7.getContentPane().setBackground(Color.white);
        frame7.setResizable(false);
        frame7.setSize(330,300);
        titular_c.setBounds(40,30,100,30);
        titular_card.setBounds(30,60,140,30);
        titular_card.setEditable(false);
        data_e.setBounds(200,30, 100,30);
        exp.setBounds(210,60,70,30);
        exp.setEditable(false);
        nr_identificare.setBounds(100,90,130,30);
        numar_identificare.setBounds(70,120,170,30);
        numar_identificare.setEditable(false);
        cvv_c.setBounds(220,155,50,30);
        cvv.setBounds(220,180,70,30);
        cvv.setEditable(false);
        exit.setBounds(115,220,100,30);
        exit.setFocusable(false);
        frame7.add(exit);
        frame7.add(cvv);
        frame7.add(cvv_c);
        frame7.add(numar_identificare);
        frame7.add(nr_identificare);
        frame7.add(titular_c);
        frame7.add(titular_card);
        frame7.add(data_e);
        frame7.add(exp);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
            String query = "{call card_info(?,?,?)}";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setString(1,username);
            stmt.setString(2,parola);
            stmt.setString(3,ibann);
            boolean hasResoults = stmt.execute();
            if(hasResoults){
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    numar_identificare.setText(rs.getString(1));
                    cvv.setText(rs.getString(2));
                    titular_card.setText(rs.getString(3) + " " + rs.getString(4));
                    exp.setText(rs.getString(5));
                }
            }
        }catch(SQLException d){
            d.printStackTrace();
        }
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame7.dispose();
            }
        });
    }

    public static void main(String[] args) {
    }
}
