package db5;

import sun.security.pkcs11.Secmod;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DBmanager
{
    private static DBmanager uniqueInstance = null;
    private static Connection connection = null;

    private DBmanager()
    {
        if(!dbExists())
        {
            System.err.println("DBManager: The database doesn't exist!");
        }
    }

    public static synchronized DBmanager getInstance()
    {
        if(uniqueInstance.equals(null))
        {
            uniqueInstance = new DBmanager();
        }
        return uniqueInstance;
    }

    private boolean dbExists()
    {
        boolean exists = false;

        Statement statement = null;
        try
        {
            if(connection.equals(null))
            {
                makeConnection();
                statement = connection.createStatement();
                statement.executeQuery("USE world;");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(statement != null)
                {
                    statement.close();
                    exists = true;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                statement = null;
            }
        }
        return exists;
    }

    public void makeConnection()
    {
        FileInputStream fis = null;
        try
        {
            Properties properties = new Properties();
            fis = new FileInputStream("database.properties");
            properties.load(fis);
            fis.close();

            String db_url = properties.getProperty("jdbc.db_url");
            String db_params = properties.getProperty("jdbc.db_params");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            connection = DriverManager.getConnection(db_url+db_params,username,password);
        }
        catch(Exception e)
        {
            System.err.println("Connection error.");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(!fis.equals(null))
                {
                    fis.close();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return;
            }
        }
    }
}
