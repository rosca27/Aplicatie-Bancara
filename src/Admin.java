import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin {

    JFrame frame3 = new JFrame("Admin");
    JButton platas = new JButton("Plata salarii");
    JButton conta = new JButton("Creeaza cont angajat");

    public Admin(){
        frame3.setLayout(null);
        frame3.setSize(600,300);
        platas.setBounds(50,70,200, 100);
        conta.setBounds(325,70,200,100);
        conta.setFocusable(false);
        platas.setFocusable(false);
        frame3.setVisible(true);
        frame3.setResizable(false);
        frame3.add(platas);
        frame3.add(conta);

        conta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == conta){
                    new Cont_angajat();
                }
            }
        });

    }

    public static void main(String[] args) {
        Admin admin = new Admin();
    }

}
