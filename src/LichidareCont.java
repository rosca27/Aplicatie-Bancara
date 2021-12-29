import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LichidareCont {

    JFrame frame4 = new JFrame("Lichidare cont");
    String[] v = {"mere","pere"};
    JList lista_conturi = new JList(v);

    public LichidareCont(){

        frame4.setLayout(null);
        frame4.setSize(500,400);
        frame4.setVisible(true);
        lista_conturi.setBounds(100,100,400,300);
        frame4.add(lista_conturi);
    }

    public static void main(String[] args) {
        LichidareCont l = new LichidareCont();
    }
}
