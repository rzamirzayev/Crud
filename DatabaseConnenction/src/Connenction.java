import java.sql.*;
import java.util.Scanner;

public class Connenction {
    private String name="root";
    private String password="";
    private String database_name="demo";
    private String host="localhost";
    private int port=3306;
    Scanner scanner=new Scanner(System.in);
    Connection con=null;
    Statement statement=null;
    PreparedStatement preparedStatement=null;
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
        String query = "Insert Into people (name,surname) VALUES(?,?)";

        try {
            preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void EditPeople(int id,String name){
        String query = "UPDATE people SET name=? WHERE id=?";

        try {
            preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        String query = "DELETE FROM people WHERE id=?";

        try {
            preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
