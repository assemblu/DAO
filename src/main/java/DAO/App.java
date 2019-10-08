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
