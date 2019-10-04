package DAO;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class CityDAO implements dbmInterface
{
    DatabaseManager dbm;
    ResultSet resultSet;
    String query;

    CityDAO(DatabaseManager dbm)
    {
        this.dbm = dbm;
        this.resultSet = null;
        this.query = null;
    }

    public void setupCityDB()
    {
        this.dbm.setupDBM();
        dbm.setDatabase("world");
    }

    public String getQuery()
    {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String query = "";
        while(input.hasNextLine() && ! (query = input.nextLine()).contains(";"))
        {
            sb.append(query + " ");
        }
        sb.append(query);

        return sb.toString();
    }

    @Override
    public void runQuery(String query)
    {
        System.out.println(query);
        this.dbm.runQuery(query);
    }

    @Override
    public boolean resultNext() {
        try
        {
            return resultSet.next();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String getResultString(String columnLabel) {
        return null;
    }

    public void searchCity(String cityName)
    {
        this.dbm.runQuery("SELECT Name FROM city WHERE Name LIKE '" + cityName + "%';");
    }
}
