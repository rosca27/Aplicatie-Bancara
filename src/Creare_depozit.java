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
    JFrame frame8 = new JFrame("Creare cont");
    String v[] = {"3","6","9","12"};
    JComboBox tip_cont = new JComboBox(v);
    JTextField suma = new JTextField();
    JLabel tcont = new JLabel("Perioada depozit (luni)");
    JLabel tsuma = new JLabel("Suma de inceput");
    JButton creeaza = new JButton("Creeaza depozit");
    public String tip_contt = ""; public int sumat ;
    public Creare_depozit(/*String username, String parola*/){
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
    }

    public static void main(String[] args) {
        Creare_depozit d = new Creare_depozit();
    }
}
