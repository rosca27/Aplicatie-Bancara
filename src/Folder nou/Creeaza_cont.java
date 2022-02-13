import javax.naming.InsufficientResourcesException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class Creeaza_cont extends JFrame implements ActionListener {
    JFrame frame2 = new JFrame("Cont client");
    JTextField text_nume = new JTextField();
    JTextField text_prenume = new JTextField();
    JTextField text_cnp = new JTextField();
    JTextField text_tel = new JTextField();
    JTextField text_adresa = new JTextField();
    JTextField text_email = new JTextField();
    JTextField text_user = new JTextField();
    JTextField text_parola = new JTextField();
    JTextField text_venit = new JTextField();
    public String usert = "",parolat ="", numet = "", prenumet = "", nr_tel = "", email = "", adresat = "", cnpt = "",
    sursa_venit = "", tranzactiit ="", zit ="", lunat = "", ant = "";

    String[] ziv = {"1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" , "10" ,"11" , "12" , "13" , "14" , "15" , "16" , "17" , "18" , "19" , "20","21" , "22" , "23" , "24" , "25" , "26" , "27" , "28" , "29", "30","31" };
    String[] lunav = {"1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" , "10" ,"11" , "12"};
    String[] anv = {"1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988",
        "1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010",
            "2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021"};
    String[] tranzactiiv = {"DA","NU"};

    JComboBox zi = new JComboBox(ziv);
    JComboBox luna = new JComboBox(lunav);
    JComboBox an = new JComboBox(anv);
    JComboBox tranzactiio = new JComboBox(tranzactiiv);

    JLabel nume = new JLabel("Nume");
    JLabel prenume = new JLabel("Prenume");
    JLabel cnp = new JLabel("CNP");
    JLabel tel = new JLabel("Nr. telefon");
    JLabel adresa = new JLabel("Adresa");
    JLabel e_mail = new JLabel("E-mail");
    JLabel user = new JLabel("Nume utilizator");
    JLabel parola = new JLabel("Parola");
    JLabel datan = new JLabel("Data Nastere");
    JLabel venit = new JLabel("Sursa venit");
    JLabel tranzactii = new JLabel("Tranzactii online");

    JButton creeaza_cnt = new JButton("Creeaza cont");

    Font font1 = new Font("Arial", Font.BOLD, 16);
    Font font2 = new Font("Aria", Font.PLAIN, 18);

    public Creeaza_cont() {


        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame2.setSize(800, 600);
        frame2.getContentPane().setBackground(Color.WHITE);
        frame2.setLayout(null);
        frame2.setResizable(false);
        frame2.setVisible(true);

        text_nume.setBounds(100, 50, 250, 30);
        text_nume.setFont(font2);

        nume.setBounds(50, 40, 50, 50);
        nume.setFont(font1);

        text_prenume.setBounds(450, 50, 250, 30);
        prenume.setBounds(375, 40, 100, 50);
        prenume.setFont(font1);
        text_prenume.setFont(font2);

        text_cnp.setBounds(100, 100, 250, 30);
        cnp.setBounds(50, 90, 50, 50);
        cnp.setFont(font1);
        text_cnp.setFont(font2);

        text_tel.setBounds(450, 100, 250, 30);
        tel.setBounds(365, 90, 100, 50);
        tel.setFont(font1);
        text_tel.setFont(font2);

        text_adresa.setBounds(100, 150, 600, 30);
        adresa.setBounds(45, 140, 100, 50);
        adresa.setFont(font1);
        text_adresa.setFont(font2);

        text_email.setBounds(100, 200, 400, 30);
        e_mail.setBounds(45, 190, 100, 50);
        e_mail.setFont(font1);
        text_email.setFont(font2);

        text_user.setBounds(450, 240, 250, 30);
        text_user.setFont(font1);
        user.setBounds(320, 230, 150, 50);
        user.setFont(font1);

        text_parola.setBounds(450, 290, 250, 30);
        text_parola.setFont(font1);
        parola.setBounds(380, 280, 150, 50);
        parola.setFont(font1);


        datan.setBounds(50,300,100,100);
        datan.setFont(font1);

        zi.setBounds(50, 370,40,20);
        luna.setBounds(90,370,40,20);
        an.setBounds(130,370,80,20);
        an.addActionListener(this);
        luna.addActionListener(this);
        zi.addActionListener(this);



        text_venit.setBounds(130,420,250,30);
        text_venit.setFont(font2);

        venit.setBounds(30,410,100,50);
        venit.setFont(font1);

        tranzactii.setBounds(420,410,150,50);
        tranzactii.setFont(font1);

        tranzactiio.setBounds(580,420,50,30);
        tranzactiio.setFocusable(false);

        creeaza_cnt.setBounds(300,470, 150,50);
        creeaza_cnt.setFocusable(false);



        creeaza_cnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                numet = text_nume.getText();
                prenumet = text_prenume.getText();
                cnpt = text_cnp.getText();
                adresat = text_adresa.getText();
                nr_tel = text_tel.getText();
                sursa_venit = text_venit.getText();
                tranzactiit = tranzactiio.getSelectedItem().toString();
                email = text_email.getText();
                zit = zi.getSelectedItem().toString();
                lunat = luna.getSelectedItem().toString();
                ant = an.getSelectedItem().toString();
                usert = text_user.getText();
                parolat = text_parola.getText();
                long ibanl = (long) ((Math.random()*(999999999-100000000)+10000000) + (Math.random()*(9999-10000)+1000)) ;
                String iban = String.valueOf(ibanl);

                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");

                    if(usert.length()>7 && parolat.length() > 7 && numet.length()>3 && prenumet.length() > 3 && Long.parseLong(cnpt) > 0) {
                        String query = "{call create_account_client(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                        CallableStatement stmt = connection.prepareCall(query);
                        stmt.setString(1, usert);
                        stmt.setString(2, parolat);
                        stmt.setString(3, cnpt);
                        stmt.setString(4, numet);
                        stmt.setString(5, prenumet);
                        stmt.setString(6, adresat);
                        stmt.setString(7, nr_tel);
                        stmt.setString(8, email);
                        stmt.setString(9, "ROBTRL" + iban);
                        stmt.setString(10, "1");
                        stmt.setString(11, ant + "-" + lunat + "-" + zit);
                        stmt.setString(12, sursa_venit);
                        stmt.setString(13, tranzactiit);
                        stmt.execute();
                        frame2.dispose();
                        new Interface();
                    }
                    else {text_user.setText("Minim 8 carcatere");
                        text_parola.setText("");}
                }
                catch (SQLException d) {
                    d.printStackTrace();
                }
            }
        });

        frame2.add(creeaza_cnt);
        frame2.add(tranzactii);
        frame2.add(tranzactiio);
        frame2.add(venit);
        frame2.add(text_venit);
        frame2.add(an);
        frame2.add(luna);
        frame2.add(datan);
        frame2.add(zi);
        frame2.add(parola);
        frame2.add(user);
        frame2.add(text_user);
        frame2.add(text_parola);
        frame2.add(adresa);
        frame2.add(text_adresa);
        frame2.add(text_email);
        frame2.add(e_mail);
        frame2.add(text_cnp);
        frame2.add(cnp);
        frame2.add(tel);
        frame2.add(text_tel);
        frame2.add(prenume);
        frame2.add(text_prenume);
        frame2.add(text_nume);
        frame2.add(nume);


    }

    public static void main(String[] args) {
        Creeaza_cont login = new Creeaza_cont();
    }
    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
