package DAO;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        /*
        String url = "jdbc:mysql://localhost/world";
        String username = "root";
        String password = "12345";
        String query  = "SELECT * FROM city";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url+"?useSSL=false&allowPublicKeyRetrieval=true", username, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next())
        {
            String name = resultSet.getString("Name");
            System.out.println(name);
        }
        */

        DatabaseManager dbm = new DatabaseManager();
        dbm.setDatabase("world");
        dbm.runQuery("SELECT * FROM city");

        while(dbm.resultNext())
        {
            System.out.println(dbm.getResultString("Name"));
        }
    }
}
