package DAO;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
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

        try
        {
            DatabaseManager dbm = new DatabaseManager();
            CityDAO cityDAO = new CityDAO(dbm);
            cityDAO.setupCityDB();

            Scanner input = new Scanner(System.in);
            System.out.print("Enter query: ");
            String query = input.nextLine();

            cityDAO.searchCity(query);

            while(dbm.resultNext())
            {
                System.out.println(dbm.getResultString("Name"));
            }
        }
        catch(Exception e)
        {
            System.err.println("MAIN LOOP: ");
            e.printStackTrace();
        }

    }
}
