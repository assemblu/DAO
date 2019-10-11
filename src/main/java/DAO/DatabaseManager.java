package DAO;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

// TODO
// Make this class a singleton
public class DatabaseManager implements dbmInterface
{
    static DatabaseManager dbm = new DatabaseManager();
    private Connection connection;
    private Properties properties;
    private ResultSet resultSet;

    private DatabaseManager()
    {
        this.connection = null;
        this.properties = null;
        this.resultSet = null;
    }

    public static DatabaseManager getInstance()
    {
        return dbm;
    }

    public void setupDBM()
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

    @Override
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

    @Override
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

    @Override
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
