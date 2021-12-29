import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Interface extends JFrame implements ActionListener{
    JFrame frame = new JFrame("Aplicatie Bancara");
    JTextField text_user = new JTextField("");
    JLabel label1 = new JLabel("Utilizator");
    JLabel label2 = new JLabel("Parola");
    JLabel label3 = new JLabel("");
    Font font1 = new Font("Arial", Font.PLAIN, 18);
    JButton buton1 = new JButton("Creeaza Cont");
    JButton buton2 = new JButton("Conectare");
    public String userc = "",parolac = "";
    JPasswordField text_parola = new JPasswordField();

    public Interface() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        text_user.setBounds(250,220,300,40);
        text_parola.setBounds(250,280,300,40);

        text_user.setFont(font1);
        text_parola.setFont(font1);
        text_parola.setEchoChar('*');

        label1.setBounds(170,215, 300, 50);
        label2.setBounds(190,275, 300, 50);

        buton1.setBounds(250,330,130,60);
        buton1.setFont(new Font("Arial",Font.BOLD,14));
        buton1.setBorder(new RoundedBorder(10));
        buton1.setFocusable(false);
        buton1.addActionListener(this);

        buton2.setBounds(420,330,130,60);
        buton2.setFont(new Font("Arial",Font.BOLD,14));
        buton2.setBorder(new RoundedBorder(10));
        buton2.setFocusable(false);
        buton2.addActionListener(this);

        label1.setFont(font1);
        label2.setFont(font1);
        label3.setFont(new Font("Arial",Font.ITALIC,18));
        label3.setForeground(Color.RED);

        label3.setBounds(275,150,300,40);

        frame.add(label3);
        frame.add(buton1);
        frame.add(buton2);
        frame.add(label1);
        frame.add(label2);
        frame.add(text_parola);
        frame.add(text_user);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.WHITE);
    }


    public static void main(String[] args) {
        Interface interfata = new Interface();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            int k, k2,k3;
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");

            userc = String.valueOf(text_user.getText());
            parolac = String.valueOf(text_parola.getText());


            String query = "{call loginc(?,?,?)}";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setString(1,userc);
            stmt.setString(2,parolac);
            stmt.registerOutParameter(3,Types.INTEGER);
            stmt.execute();
            k = stmt.getInt(3);

            String query2 = "{call logina(?,?,?)}";
            CallableStatement stmt2 = connection.prepareCall(query2);
            stmt2.setString(1,userc);
            stmt2.setString(2,parolac);
            stmt2.registerOutParameter(3,Types.INTEGER);
            stmt2.execute();
            k2 = stmt2.getInt(3);

            String query3 = "{call loginadmin(?,?,?)}";
            CallableStatement stmt3 = connection.prepareCall(query3);
            stmt3.setString(1,userc);
            stmt3.setString(2,parolac);
            stmt3.registerOutParameter(3,Types.INTEGER);
            stmt3.execute();
            k3 = stmt3.getInt(3);

            if(e.getSource() == buton2 && text_user.getText().length() > 7 && text_parola.getPassword().length > 7 && k != 0) {
                new Client(userc,parolac);
                System.out.println(k);
            }
            else if(e.getSource() == buton2 && text_user.getText().length() > 7 && text_parola.getPassword().length > 7 && k2 != 0) {
            }
            else if(e.getSource() == buton2 && text_user.getText().length() > 7 && text_parola.getPassword().length > 7 && k3 != 0){
                new Admin();
            }
            else{
            label3.setText("Utilizator sau parola invalide");
                System.out.println(userc+" "+parolac+" "+k);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }



        if(e.getSource() == buton1){
            new Creeaza_cont();
            frame.setVisible(false);}


    }
}