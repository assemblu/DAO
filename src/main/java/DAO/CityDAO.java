package DAO;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class CityDAO implements dbmInterface
{
    private DatabaseManager dbm;
    private ResultSet resultSet;

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
        try
        {
            return this.resultSet.getString(columnLabel);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "Error.";
    }

    public void selectFromCities(String query)
    {
        this.dbm.runQuery("SELECT " + query + " FROM city");
    }

    public void getCities()
    {
        this.dbm.runQuery("SELECT * FROM city;");
    }

    public void selectCitiesWhere(String query)
    {
        this.dbm.runQuery("SELECT * FROM city WHERE " + query);
    }

    public void searchCity(String query)
    {
        String[] queryContent = query.split(" ");
        if(queryContent.length == 2)
        {
            // user submitted country
            this.dbm.runQuery("SELECT Name FROM city WHERE Name LIKE '" + queryContent[0] + "%' AND " +
                    "CountryCode IN (SELECT Code FROM country WHERE Name LIKE '" + queryContent[1] + "%')");
        }
        else
        {
            // user only submitted city
            this.dbm.runQuery("SELECT Name FROM city WHERE Name LIKE '" + queryContent[0] + "%'");
        }
    }
}
