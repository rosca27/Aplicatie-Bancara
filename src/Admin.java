import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Admin {

    JFrame frame3 = new JFrame("Admin");
    JButton conta = new JButton("Creeaza cont angajat");
    JButton viz_user = new JButton("Vizualizare date");
    JButton logout = new JButton("Log out");
    JButton user_viz = new JButton("Vizualizare utilizatori");
    JButton plata_salarii = new JButton("Plata Salarii");
    JButton aprobare_card = new JButton("Aprobare Card");
    JButton notificari = new JButton("Notificari");
    JButton aprobare = new JButton("Aprobare");
    JLabel error = new JLabel("");

    public Admin(String username, String parola){
        frame3.setLayout(null);
        frame3.setSize(800,600);
        conta.setBounds(300,70,200,100);
        aprobare.setBounds(50,70,200,100);
        logout.setBounds(300, 440, 100, 60);
        viz_user.setBounds(550, 70, 200, 100);
        notificari.setBounds(50,220,200,100);
        plata_salarii.setBounds(550,220,200,100);
        error .setBounds(100,20,300,30);
        error.setFont(new Font("Arial",Font.ITALIC,18));
        error.setForeground(Color.RED);
        frame3.add(error);
        conta.setFocusable(false);
        frame3.setVisible(true);
        frame3.setResizable(false);
        frame3.add(plata_salarii);
        frame3.add(aprobare);
        frame3.add(notificari);
        frame3.add(conta);
        frame3.add(viz_user);
        frame3.add(logout);

        conta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == conta){
                    new Cont_angajat();
                }

            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == logout) {
                    frame3.dispose();
                }
            }
        });

        viz_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == viz_user) {
                    new VizualizareDateAdmin(username, parola);
                }
            }
        });
        aprobare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AprobareCard();
            }
        });

        notificari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewNotificari(username, parola);
            }
        });

        plata_salarii.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int j = 2;
                    Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                    String query6 = "{call plata_angajati(?)}";
                    CallableStatement stmt5 = connection2.prepareCall(query6);
                    stmt5.registerOutParameter(1,Types.INTEGER);
                    stmt5.execute();
                    //rs.next();
                    int k3 = stmt5.getInt(1);
                    if(k3 == 0)
                        error.setText("Nu este data de 15!");
                    else
                        error.setText("Plata efectuata cu succes!");

                }catch (SQLException d){
                    d.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {

    }

}
