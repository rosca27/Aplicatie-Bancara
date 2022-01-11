import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Cont_angajat implements ActionListener {

        JFrame frame2 = new JFrame("Aplicatie Bancara");
        JTextField text_nume = new JTextField();
        JTextField text_prenume = new JTextField();
        JTextField text_cnp = new JTextField();
        JTextField text_tel = new JTextField();
        JTextField text_adresa = new JTextField();
        JTextField text_email = new JTextField();
        JTextField text_user = new JTextField();
        JTextField text_parola = new JTextField();
        JTextField text_salariu = new JTextField();

        String[] normav = {"4 ore","8 ore"};
        String[] sucursalav = {"Arad","Brasov","Bucuresti","Cluj-Napoca","Targu Mures"};
        String[] activitatev = {"HR","IT","Functionar"};

        public String usert = "",parolat ="", numet = "", prenumet = "", nr_tel = "", email = "", adresat = "", cnpt = "",
        normat= "", sucursalat = "", activitatet = "";
        int salariut;

        JComboBox normao = new JComboBox(normav);
        JComboBox sucursalao = new JComboBox(sucursalav);
        JComboBox departamento = new JComboBox(activitatev);

        JLabel nume = new JLabel("Nume");
        JLabel prenume = new JLabel("Prenume");
        JLabel cnp = new JLabel("CNP");
        JLabel tel = new JLabel("Nr. telefon");
        JLabel adresa = new JLabel("Adresa");
        JLabel e_mail = new JLabel("E-mail");
        JLabel user = new JLabel("Nume utilizator");
        JLabel parola = new JLabel("Parola");
        JLabel salariu = new JLabel("Salariu");
        JLabel norma = new JLabel("Norma");
        JLabel sucursala = new JLabel("Sucursala");
        JLabel departament = new JLabel("Departament");

        JButton creeaza_cnt = new JButton("Creeaza cont");




    Font font1 = new Font("Arial", Font.BOLD, 16);
        Font font2 = new Font("Aria", Font.PLAIN, 18);

        public Cont_angajat() {


            frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

            salariu.setBounds(50,340,100,50);
            salariu.setFont(font1);

            text_salariu.setBounds(110,350,200,30);
            text_salariu.setFont(font2);

            sucursala.setBounds(400,340,100,50);
            sucursala.setFont(font1);

            sucursalao.setBounds(490,350,100,30);
            sucursalao.setFocusable(false);

            norma.setBounds(50,400,100,50);
            norma.setFont(font1);

            normao.setBounds(120,415,60,25);
            normao.setFocusable(false);

            departament.setBounds(400,415,100,50);
            departament.setFont(font1);

            departamento.setBounds(500,425,100,30);
            departamento.setFocusable(false);

            creeaza_cnt.setBounds(300,470, 150,50);
            creeaza_cnt.setFocusable(false);

            frame2.add(creeaza_cnt);
            frame2.add(departament);
            frame2.add(departamento);
            frame2.add(norma);
            frame2.add(normao);
            frame2.add(sucursala);
            frame2.add(sucursalao);
            frame2.add(salariu);
            frame2.add(text_salariu);
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


            creeaza_cnt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == creeaza_cnt){
                        numet = text_nume.getText();
                        prenumet = text_prenume.getText();
                        cnpt = text_cnp.getText();
                        adresat = text_adresa.getText();
                        nr_tel = text_tel.getText();
                        email = text_email.getText();
                        usert = text_user.getText();
                        parolat = text_parola.getText();
                        salariut = Integer.valueOf(text_salariu.getText());
                        normat = normao.getSelectedItem().toString();
                        activitatet = departamento.getSelectedItem().toString();
                        sucursalat  = sucursalao.getSelectedItem().toString();
                        int norman;
                        if(normat == "4 ore"){
                            norman = 4;
                        }
                        else
                            norman = 8;

                        long ibanl = (long) ((Math.random()*(999999999-100000000)+10000000) + (Math.random()*(9999-10000)+1000)) ;
                        String iban = String.valueOf(ibanl);

                        try {

                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");

                            String query ="{call create_account_angajat(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                            CallableStatement stmt = connection.prepareCall(query);
                            stmt.setString(1,usert);
                            stmt.setString(2,parolat);
                            stmt.setString(3,cnpt);
                            stmt.setString(4,numet);
                            stmt.setString(5,prenumet);
                            stmt.setString(6,adresat);
                            stmt.setString(7,nr_tel);
                            stmt.setString(8,email);
                            stmt.setString(9,"ROBTRL"+iban);
                            stmt.setString(10,"1");
                            stmt.setInt(11,norman);
                            stmt.setInt(12, salariut);
                            stmt.setString(13,sucursalat);
                            stmt.setString(14,activitatet);

                            stmt.execute();
                        }
                        catch (SQLException d) {
                            d.printStackTrace();
                        }
                    }


                }
            });


        }

        public static void main(String[] args) {
            Cont_angajat login = new Cont_angajat();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        }
}


