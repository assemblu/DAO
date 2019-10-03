package DAO;

import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseManager
{
    private Connection connection;
    private Properties properties;
    private ResultSet resultSet;


    DatabaseManager()
    {
        setupDBM();
    }

    private void setupDBM()
    {
        loadProperties();
        makeConnection();
    }

    public void loadProperties()
    {
        try(InputStream input=  new FileInputStream("database.properties"))
        {
            this.properties = new Properties();
            this.properties.load(input);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void makeConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/" + this.properties.getProperty("db_parameters"),
                    this.properties.getProperty("db_user"),
                    this.properties.getProperty("db_password"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void runQuery(String query)
    {
        try
        {
            Statement statement = this.connection.createStatement();
            this.resultSet = statement.executeQuery(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean resultNext()
    {
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

    public String getResultString(String columnLabel)
    {
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

    public void setDatabase(String databaseName)
    {
        runQuery("use " + databaseName + ";");
    }
}
