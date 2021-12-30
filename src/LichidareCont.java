import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LichidareCont {

    JFrame frame4 = new JFrame("Lichidare cont");
    String[] v = {"mere","pere"};
    JButton lichidare = new JButton("Lichideaza");
    JLabel alege = new JLabel("Alegeti Contul");
    JList lista_conturi = new JList(v);

    public LichidareCont(){

        frame4.setLayout(null);
        frame4.setSize(500,400);
        frame4.setVisible(true);
        lista_conturi.setBounds(50,80,400,200);
        lichidare.setBounds(200,300,100,50);
        lichidare.setFocusable(false);
        alege.setBounds(200,20,200,30);
        alege.setFont(new Font("Times New Roman",Font.PLAIN,18));
        frame4.setResizable(false);
        frame4.add(alege);
        frame4.add(lichidare);
        frame4.add(lista_conturi);

        lichidare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == lichidare){


                }
            }
        });
    }

    public static void main(String[] args) {
        LichidareCont l = new LichidareCont();
    }
}
