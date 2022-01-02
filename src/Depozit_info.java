import javax.naming.InsufficientResourcesException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class Depozit_info {

    JFrame frame7 = new JFrame("Informatii depozit");
    JTextField tip_contt = new JTextField();
    JTextField suma_cont = new JTextField();
    JTextField ibann = new JTextField();
    JLabel tip = new JLabel("Perioada depozitului");
    JLabel sumaa = new JLabel("Suma din depozit");
    JLabel ibant = new JLabel("IBAN");
    JButton exit = new JButton("Iesire");

    public Depozit_info(String iban2){

        frame7.setSize(600,400);
        frame7.setLayout(null);
        frame7.setVisible(true);
        frame7.getContentPane().setBackground(Color.WHITE);
        tip_contt.setBounds(50,100,200,35);
        suma_cont.setBounds(300,100,200,35);
        tip.setBounds(110,70,100,30);
        sumaa.setBounds(350,70,100,30);
        ibant.setBounds(260,170,100,30);
        ibann.setBounds(185,200,200,35);
        exit.setBounds(250,300,80,30);
        ibann.setFont(new Font("Arial",Font.BOLD,16));
        suma_cont.setFont(new Font("Arial",Font.BOLD,16));
        tip_contt.setFont(new Font("Arial",Font.BOLD,16));
        exit.setFocusable(false);
        ibann.setEditable(false);
        suma_cont.setEditable(false);
        tip_contt.setEditable(false);

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
            String query = "{call view_depo_info(?)}";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setString(1,iban2);
            boolean hasResults = stmt.execute();
            System.out.println(hasResults);
            if(hasResults){
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    String x = rs.getString(3);
                    tip_contt.setText(rs.getString(2) + " luni");
                    ibann.setText(iban2);
                    suma_cont.setText(rs.getString(1));
                }
            }
        }
        catch(SQLException d){

        }

        frame7.add(exit);
        frame7.add(ibann);
        frame7.add(ibant);
        frame7.add(tip_contt);
        frame7.add(suma_cont);
        frame7.add(tip);
        frame7.add(sumaa);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame7.dispatchEvent(new WindowEvent(frame7, WindowEvent.WINDOW_CLOSING));
            }
        });
    }
}
