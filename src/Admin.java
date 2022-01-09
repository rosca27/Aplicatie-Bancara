import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin {

    JFrame frame3 = new JFrame("Admin");
    JButton platas = new JButton("Plata salarii");
    JButton conta = new JButton("Creeaza cont angajat");
    JButton viz_user = new JButton("Vizualizare date");
    JButton logout = new JButton("Log out");
    JButton user_viz = new JButton("Vizualizare utilizatori");
    JButton aprobare_card = new JButton("Aprobare Card");
    JButton notificari = new JButton("Notificari");
    JButton aprobare = new JButton("Aprobare");

    public Admin(String username, String parola){
        frame3.setLayout(null);
        frame3.setSize(800,600);
        platas.setBounds(50,70,200, 100);
        conta.setBounds(300,70,200,100);
        aprobare.setBounds(300,220,200,100);
        logout.setBounds(300, 440, 100, 60);
        viz_user.setBounds(550, 70, 200, 100);
        conta.setFocusable(false);
        platas.setFocusable(false);
        frame3.setVisible(true);
        frame3.setResizable(false);
        frame3.add(aprobare);
        frame3.add(platas);
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
    }

    public static void main(String[] args) {

    }

}
