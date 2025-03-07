import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connenction {
    private String name="root";
    private String password="";
    private String database_name="demo";
    private String host="localhost";
    private int port=3306;
    Scanner scanner=new Scanner(System.in);
    Connection con=null;
    Statement statement=null;
    public Connenction(){
        String url = "jdbc:mysql://" + host + ":" + port + "/" +database_name+ "?useUnicode=true&characterEncoding=utf8";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("xeta burdadi");
        }

        try {
            con = DriverManager.getConnection(url,name, password);
            System.out.println("Bağlantı Başarılı...");


        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
            System.out.println(ex.getMessage());
        }

    }
    public void AddPeople(String name,String surname){

        try {
            statement=con.createStatement();
            String query = "Insert Into people (name,surname) VALUES(" + "'" + name + "'," + "'" + surname + "')";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void EditPeople(int id,String name){

        try {
            statement=con.createStatement();
            String query = "UPDATE people SET name='" + name + "' WHERE id=" + id;

            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void showPeople() {

        String query = "Select * From people";

        try {
            statement = con.createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");

                System.out.println("Id : " + id + "Ad: " + name + "Soyad : " + surname);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }



    }
    public void removePeople(int id){
        try {
            statement=con.createStatement();
            String query = "DELETE FROM people WHERE id=" + id;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
