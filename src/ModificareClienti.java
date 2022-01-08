import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ModificareClienti extends JFrame{

    private JList lista_u;
    private JList lista_u2;
    private JTextField text_cauta;
    private JButton buton_cauta;
    JFrame frame16 = new JFrame ("Cautare utilizatori");
    private JButton buton_resetare;
    private JButton buton_vizualizare;

    public ModificareClienti() {
        DefaultListModel v = new DefaultListModel<>();
        DefaultListModel c = new DefaultListModel<>();
        lista_u = new JList(v);
        lista_u2 = new JList(c);
        text_cauta = new JTextField(0);
        buton_cauta = new JButton("Cauta");
        buton_resetare = new JButton("Resetare");
        buton_vizualizare = new JButton("Vizualizeaza Date");

        frame16.setSize(373, 530);
        frame16.setLayout(null);
        frame16.setResizable(false);

        lista_u2.setVisible(false);

        lista_u.setBounds(65, 70, 230, 280);
        lista_u2.setBounds(65, 70, 230, 280);
        text_cauta.setBounds(65, 20, 230, 30);
        buton_cauta.setBounds(185, 430, 110, 35);
        buton_resetare.setBounds(65, 430, 100, 35);
        buton_vizualizare.setBounds(100,390,160,35);
        buton_vizualizare.setFocusable(false);

        frame16.add(buton_vizualizare);
        frame16.add(lista_u2);
        frame16.add(buton_resetare);
        frame16.add(lista_u);
        frame16.add(text_cauta);
        frame16.add(buton_cauta);
        frame16.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame16.setVisible(true);
        buton_cauta.setFocusable(false);
        buton_resetare.setFocusable(false);
        lista_u.setVisible(true);

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
            String query = "select distinct u.nume, u.prenume from utilizatori as u,clienti as c where u.id = c.utilizator_id;";
            CallableStatement stmt = connection.prepareCall(query);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                v.addElement(rs.getString(1) + " " + rs.getString(2));
            }

        } catch (
                SQLException d) {
            d.printStackTrace();
        }

        buton_cauta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = text_cauta.getText().toString();

                try {
                    c.clear();
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                    String query = "{call search(?)}";
                    CallableStatement stmt = connection.prepareCall(query);
                    stmt.setString(1, text);
                    stmt.execute();
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        c.addElement(rs.getString(1) + " " + rs.getString(2));
                    }
                    lista_u.setVisible(false);
                    lista_u2.setVisible(true);

                } catch (SQLException d) {
                    d.printStackTrace();
                }
            }
        });

        buton_resetare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lista_u.setVisible(true);
                lista_u2.setVisible(false);
            }
        });

        buton_vizualizare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    String value = lista_u.getSelectedValue().toString();
                    if(value.compareTo("") > 0){
                        int j = lista_u.getSelectedIndex();
                        String x2[] = v.get(j).toString().split(" ");
                        String usernamee = "", parolaa = "";
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                        String query ="{call nume_prenume(?,?)}";
                        CallableStatement stmt = connection.prepareCall(query);
                        stmt.setString(1,x2[0]);
                        stmt.setString(2,x2[1]);
                        stmt.execute();
                        ResultSet rs = stmt.getResultSet();
                        rs.next();
                        usernamee = rs.getString(1);
                        parolaa = rs.getString(2);
                        System.out.println(usernamee + " " + parolaa);
                        new VizualizareDateClient(usernamee,parolaa);
                    }
                    int i = lista_u2.getSelectedIndex();
                    String x[] = v.get(i).toString().split(" ");
                    String usernamee = "", parolaa = "";
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");
                    String query ="{call nume_prenume(?,?)}";
                    CallableStatement stmt = connection.prepareCall(query);
                    stmt.setString(1,x[0]);
                    stmt.setString(2,x[1]);
                    stmt.execute();
                    ResultSet rs = stmt.getResultSet();
                    rs.next();
                        usernamee = rs.getString(1);
                        parolaa = rs.getString(2);
                    System.out.println(usernamee + " " + parolaa);
                    new VizualizareDateClient(usernamee,parolaa);
                }catch (SQLException d){
                    d.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
    }
}

