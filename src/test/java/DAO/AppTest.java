package DAO;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testCitiesSearchWithEN()
    {
        ArrayList<String> output = new ArrayList<String>();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.setupDBM();
        CityDAO cityDAO = new CityDAO(dbm);
        cityDAO.setupCityDB();

        Scanner input = new Scanner(System.in);
        // skip asking for input
        String query = "En"; // pre-defined input
        cityDAO.searchCity(query);

        while(dbm.resultNext())
        {
            output.add(dbm.getResultString("Name"));
        }

        int correctCounter = 0;
        for(String data : output)
        {
            if(data.substring(0,2).equals("En"))
            {
                correctCounter++;
            }
        }

        assertTrue(output.size() == correctCounter);
    }

    @Test
    public void testInputEN()
    {
        ArrayList<String> output = new ArrayList<String>();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.setupDBM();
        CityDAO cityDAO = new CityDAO(dbm);
        cityDAO.setupCityDB();

        Scanner input = new Scanner(System.in);
        // skip asking for input
        ByteArrayInputStream inquery = new ByteArrayInputStream("En".getBytes());
        cityDAO.searchCity(inquery.toString());

        while(dbm.resultNext())
        {
            output.add(dbm.getResultString("Name"));
        }

        int correctCounter = 0;
        for(String data : output)
        {
            if(data.substring(0,2).equals("En"))
            {
                correctCounter++;
            }
        }

        assertTrue(output.size() == correctCounter);
    }

    @Test
    public void testInputX()
    {
        ArrayList<String> output = new ArrayList<String>();
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.setupDBM();
        CityDAO cityDAO = new CityDAO(dbm);
        cityDAO.setupCityDB();

        Scanner input = new Scanner(System.in);
        // skip asking for input
        ByteArrayInputStream inquery = new ByteArrayInputStream("X".getBytes());
        cityDAO.searchCity(inquery.toString());

        while(dbm.resultNext())
        {
            output.add(dbm.getResultString("Name"));
        }

        int correctCounter = 0;
        for(String data : output)
        {
            if(data.substring(0,1).equals("X"))
            {
                correctCounter++;
            }
        }

        assertTrue(output.size() == correctCounter);
    }

    @Test
    public void testTwoInputs()
    {
        String output = null;
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.setupDBM();
        CityDAO cityDAO = new CityDAO(dbm);
        cityDAO.setupCityDB();

        cityDAO.searchCity("en net");
        while(dbm.resultNext())
        {
            output = (dbm.getResultString("Name"));
        }
        assertTrue(output.equals("Enschede"));
    }
}
