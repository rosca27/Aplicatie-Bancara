import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Client implements ActionListener{

    JFrame frame = new JFrame("Client");
    JButton dcont = new JButton("Deschidere cont");
    JButton ddep = new JButton("Deschidere depozit");
    JButton lcont = new JButton("Lichidare cont");
    JButton ldep = new JButton("Lichidare depozit");
    JButton vizualizarec = new JButton("Vizualizare conturi");
    JButton vizualiared = new JButton("Vizualizare depozite");
    JButton plata_facturi = new JButton("Plata facturi");
    JButton transfer = new JButton("Transfer");
    JButton solicitare_card = new JButton("Solicitare card");
    JButton log_out = new JButton("Log out");

    public Client(String username, String parola){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        dcont.setBounds(100,100,150,70);
        dcont.setFocusable(false);

        ddep.setBounds(100,200,150,70);
        ddep.setFocusable(false);

        lcont.setBounds(300, 100,150,70);
        lcont.setFocusable(false);

        ldep.setBounds(300,200,150,70);
        ldep.setFocusable(false);

        vizualiared.setBounds(500,200,150,70);
        vizualiared.setFocusable(false);

        vizualizarec.setBounds(500,100,150,70);
        vizualizarec.setFocusable(false);

        plata_facturi.setBounds(300,300,150,70);
        plata_facturi.setFocusable(false);

        transfer.setBounds(500,300,150,70);
        transfer.setFocusable(false);

        solicitare_card.setBounds(100,300,150,70);
        solicitare_card.setFocusable(false);

        log_out.setBounds(300,400,150,70);
        log_out.setFocusable(false);

        frame.add(log_out);
        frame.add(solicitare_card);
        frame.add(dcont);
        frame.add(ddep);
        frame.add(ldep);
        frame.add(lcont);
        frame.add(vizualiared);
        frame.add(vizualizarec);
        frame.add(transfer);
        frame.add(plata_facturi);

        dcont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Creare_cont(username,parola);
            }
        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
    }
}
