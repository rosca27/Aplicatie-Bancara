import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Transfer {

    private JTextField ibant;
    private JLabel ibanl;
    private JList listaC;
    private JTextField sumat;
    private JLabel sumal;
    private JLabel contactel;
    private JButton buton_transfer;
    private JButton buton_contacte;
    private JButton buton_bani;
    private JFrame frame11 = new JFrame("Transfer");
    private JLabel error;
    private JLabel error2;

    public Transfer(String username, String parola,String ibann) {


            DefaultListModel v = new DefaultListModel();


            ibant = new JTextField();
            ibanl = new JLabel ("IBAN");
            listaC = new JList (v);
            sumat = new JTextField ();
            sumal = new JLabel ("Suma");
            contactel = new JLabel ("Lista contacte favorite");
            buton_transfer = new JButton ("Transfer");
            buton_bani = new JButton ("Adauga bani pe cont");
            buton_contacte = new JButton ("Adauga contacte favorite");
            error = new JLabel ("");
            error2 = new JLabel ("");
            String[] x1 = new String[3];

            frame11.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            frame11.setSize(750, 380);
            frame11.setLayout(null);
            frame11.setVisible (true);
            frame11.setResizable(false);

            buton_contacte.setFocusable(false);
            buton_transfer.setFocusable(false);
            buton_bani.setFocusable(false);

            frame11.add(error2);
            frame11.add(error);
            frame11.add(ibant);
            frame11.add(ibanl);
            frame11.add(listaC);
            frame11.add(sumat);
            frame11.add(sumal);
            frame11.add(contactel);
            frame11.add(buton_transfer);
            frame11.add(buton_contacte);
            frame11.add(buton_bani);


            ibant.setBounds (45, 55, 190, 30);
            ibanl.setBounds (45, 15, 100, 25);
            listaC.setBounds (480, 65, 190, 245);
            sumat.setBounds (45, 125, 190, 30);
            sumal.setBounds (45, 95, 100, 25);
            contactel.setBounds (505, 20, 140, 25);
            buton_transfer.setBounds (165, 200, 115, 50);
            buton_contacte.setBounds (35, 290, 175, 45);
            buton_bani.setBounds (230, 290, 195, 45);
            error.setBounds (120, 15, 145, 25);
            error.setForeground(Color.RED);
            error.setFont(new Font("Arial",Font.ITALIC,15));
            sumat.setFont(new Font("Arial",Font.BOLD,15));
            ibant.setFont(new Font("Arial",Font.BOLD,15));
            error2.setBounds (115, 95, 150, 25);
            error2.setForeground(Color.RED);
            error2.setFont(new Font("Arial",Font.ITALIC,15));

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
            String query = "{call view_contacte(?,?)}";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.setString(1,username);
            stmt.setString(2,parola);
            boolean hasResults = stmt.execute();
            System.out.println(hasResults);
            if(hasResults){
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    String x = rs.getString(1);
                    String y = rs.getString(2);
                    v.addElement(x + " " + y);
                }
            }
        }
        catch(SQLException d){

        }

            buton_transfer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String ibann3 = ibant.getText().toString();
                    int k;

                    if(sumat.getText().compareTo("") != 0) {
                        if(ibant.getText().compareTo("")!= 0) {
                            try {
                                error2.setText("");
                                int suma = Integer.valueOf(sumat.getText());
                                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                                String query = "{call find_iban(?,?)}";
                                CallableStatement stmt = connection.prepareCall(query);
                                stmt.setString(1, ibann3);
                                stmt.registerOutParameter(2, Types.INTEGER);
                                stmt.execute();
                                k = stmt.getInt(2);
                                System.out.println(k);
                                if (k == 0) {
                                    error.setText("IBAN inexistent");
                                } else {
                                    String query2 = "{call transferr2(?,?,?)}";
                                    CallableStatement stmt2 = connection.prepareCall(query2);
                                    stmt2.setString(1, ibann);
                                    stmt2.setString(2, ibann3);
                                    stmt2.setInt(3, suma);
                                    stmt2.execute();
                                    error.setText("");
                                    JFrame info = new JFrame("Succes!");
                                    JOptionPane.showMessageDialog(info,"Solicitarea s-a realizat cu succes!");
                                    frame11.dispose();
                                }

                            } catch (SQLException d) {
                                d.printStackTrace();
                            }
                        }
                        else{
                            int i = listaC.getSelectedIndex();
                            String[] c = v.get(i).toString().split(" ");
                            System.out.println(c[0]);
                            System.out.println(c[1]);
                            try {

                                int suma = Integer.valueOf(sumat.getText());
                                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                                String query3 = "{call get_c(?,?)}";
                                CallableStatement stmt3 = connection.prepareCall(query3);
                                stmt3.setString(1,c[0]);
                                stmt3.setString(2,c[1]);
                                stmt3.execute();
                                ResultSet rs = stmt3.getResultSet();
                                rs.next();
                                String ibannc = rs.getString(1);
                                System.out.println(ibannc);
                                String query4 = "{call transferr2(?,?,?)}";
                                CallableStatement stmt4 = connection.prepareCall(query4);
                                stmt4.setString(1,ibann);
                                stmt4.setString(2,ibannc);
                                stmt4.setInt(3,suma);
                                stmt4.execute();
                            }catch(SQLException d){
                                d.printStackTrace();
                            }
                        }
                    }
                    else error2.setText("Introduceti o suma");
                    }
            });

            buton_bani.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int suma = Integer.valueOf(sumat.getText());
                    try{

                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                        String query = "{call depunere(?,?)}";
                        CallableStatement stmt = connection.prepareCall(query);
                        stmt.setString(1,ibann);
                        stmt.setInt(2,suma);
                        stmt.execute();
                        frame11.dispose();
                    }catch(SQLException d){
                        d.printStackTrace();
                    }
                }
            });

            buton_contacte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String ibann3 = ibant.getText().toString();
                    int k;
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                        String query = "{call find_iban(?,?)}";
                        CallableStatement stmt = connection.prepareCall(query);
                        stmt.setString(1, ibann3);
                        stmt.registerOutParameter(2, Types.INTEGER);
                        stmt.execute();
                        k = stmt.getInt(2);
                        System.out.println(k);
                        if (k == 0 ) {
                            error.setText("IBAN inexistent");
                        }
                        else {
                            error.setText("");
                            String query2 = "{call add_contact(?,?,?)}";
                            CallableStatement stmt2 = connection.prepareCall(query2);
                            stmt2.setString(1,username);
                            stmt2.setString(2,parola);
                            stmt2.setString(3,ibann3);
                            stmt2.execute();
                            frame11.dispose();
                        }
                    }catch (SQLException d){
                        d.printStackTrace();
                    }
                }
            });

    }


    public static void main(String[] args) {
    }
}
