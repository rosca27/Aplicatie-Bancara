import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Creare_depozit {
    JFrame frame8 = new JFrame("Creare depozit");
    String v[] = {"3","6","9","12"};
    JComboBox tip_cont = new JComboBox(v);
    JTextField suma = new JTextField();
    JLabel tcont = new JLabel("Perioada depozit (luni)");
    JLabel tsuma = new JLabel("Suma de inceput");
    JButton creeaza = new JButton("Creeaza depozit");
    public String tip_contt = ""; public int sumat ;
    public Creare_depozit(String username, String parola,String iban){
        frame8.setSize(600,300);
        frame8.setLayout(null);
        frame8.setVisible(true);
        frame8.getContentPane().setBackground(Color.WHITE);
        tip_cont.setBounds(100,100,80,30);
        tip_cont.setFocusable(false);
        tcont.setFont(new Font("Arial",Font.BOLD, 15));
        tcont.setBounds(50, 50,200,50);
        suma.setBounds(250, 100, 200, 30);
        suma.setFont(new Font("Arial",Font.BOLD,16));
        tsuma.setBounds(280,60,150,30);
        tsuma.setFont(new Font("Arial",Font.BOLD, 15));
        creeaza.setBounds(200,170,200,50);
        creeaza.setFocusable(false);
        frame8.add(creeaza);
        frame8.add(tsuma);
        frame8.add(suma);
        frame8.add(tcont);
        frame8.add(tip_cont);

        creeaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                    String query = "{call create_depozit_client(?,?,?)}";
                    CallableStatement stmt = connection.prepareCall(query);
                    stmt.setInt(1,Integer.valueOf(suma.getText()));
                    stmt.setInt(2,Integer.valueOf(tip_cont.getSelectedItem().toString()));
                    stmt.setString(3,iban);
                    stmt.execute();
                    frame8.dispatchEvent(new WindowEvent(frame8, WindowEvent.WINDOW_CLOSING));

                }catch (SQLException d){
                    d.printStackTrace();
                }
            }
        });
    }
}
