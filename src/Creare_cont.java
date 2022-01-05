import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Creare_cont {

    JFrame frame5 = new JFrame("Creare cont");
    String v[] = {"CURENT","ECONOMII"};
    String[] bancav = {"Banca Transilvania","ING", "Alpha Bank"};
    JComboBox bancao = new JComboBox(bancav);
    JComboBox tip_cont = new JComboBox(v);
    JTextField suma = new JTextField();
    JLabel tcont = new JLabel("Tip cont");
    JLabel tsuma = new JLabel("Suma de inceput");
    JLabel banca = new JLabel("Banca");
    JButton creeaza = new JButton("Creeaza Cont");

    public String tip_contt = "", prefix = ""; public int sumat ;

    public Creare_cont(String username, String parola){
        frame5.setSize(600,300);
        frame5.setLayout(null);
        frame5.setVisible(true);
        frame5.getContentPane().setBackground(Color.WHITE);
        tip_cont.setBounds(200,100,80,30);
        tip_cont.setFocusable(false);
        tcont.setFont(new Font("Arial",Font.BOLD, 15));
        tcont.setBounds(220, 50,100,50);
        suma.setBounds(330, 100, 200, 30);
        suma.setFont(new Font("Arial",Font.BOLD,15));
        tsuma.setBounds(350,60,150,30);
        tsuma.setFont(new Font("Arial",Font.BOLD, 15));
        creeaza.setBounds(200,170,200,50);
        bancao.setBounds(30,100,130,30);
        banca.setBounds(75,55,100,40);
        banca.setFont(new Font("Arial",Font.BOLD,15));
        bancao.setFocusable(false);
        creeaza.setFocusable(false);
        frame5.add(banca);
        frame5.add(bancao);
        frame5.add(creeaza);
        frame5.add(tsuma);
        frame5.add(suma);
        frame5.add(tcont);
        frame5.add(tip_cont);

        creeaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == creeaza){
                    tip_contt = tip_cont.getSelectedItem().toString();
                    sumat = Integer.valueOf(suma.getText());
                    if(bancao.getSelectedItem().toString() == "Banca Transilvania")
                        prefix = "ROTB";
                    else if(bancao.getSelectedItem().toString() == "ING")
                        prefix = "ROING";
                    else if(bancao.getSelectedItem().toString() == "Alpha Bank")
                        prefix = "ROALPHA";
                        if(e.getSource() == creeaza){
                            long ibanl = (long) ((Math.random()*(999999999-100000000)+10000000) + (Math.random()*(9999-10000)+1000)) ;
                            String iban = String.valueOf(ibanl);
                            try {
                                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                                String query = "{call create_cont_client(?,?,?,?,?)}";
                                CallableStatement stmt = connection.prepareCall(query);
                                stmt.setInt(1,sumat);
                                stmt.setString(2,tip_contt);
                                stmt.setString(3,prefix+iban);
                                stmt.setString(4,username);
                                stmt.setString(5,parola);
                                stmt.execute();
                                frame5.dispatchEvent(new WindowEvent(frame5, WindowEvent.WINDOW_CLOSING));
                            }
                            catch(SQLException d){
                                d.printStackTrace();
                            }
                        }

                }
            }
        });

    }

    public static void main(String[] args) {
    }
}
