import java.sql.*;

public class Main {
    private final static String USERNAME = "root";
    private final static String PASSWORD = "Sergiucraiova12";
    private final static String DB_NAME = "proiect_final";
    private final static String CONNECTION_LINK = "jdbc:mysql://localhost:3306/";


    public static void main(String[] args) {
        Connection connection;

        try {
            //Interface a = new Interface();
            Creeaza_cont b = new Creeaza_cont();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_final", "root", "Sergiucraiova12");

            Statement statement = connection.createStatement();

            String query = ("call create_account_angajat");
            CallableStatement stmt = connection.prepareCall(query);
            stmt.execute();
            System.out.println(b.cnpt + " " + b.numet + " " + b.prenumet);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
