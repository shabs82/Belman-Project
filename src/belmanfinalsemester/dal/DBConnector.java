/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 *
 * @author Test
 */
public class DBConnector {
    private SQLServerDataSource ds;

    public DBConnector() throws FileNotFoundException
    {
        try
        {
            Properties databaseProperties = new Properties();
            databaseProperties.load(new FileInputStream("src/data/database.properties"));
            ds = new SQLServerDataSource();
            ds.setServerName(databaseProperties.getProperty("Server"));
            ds.setDatabaseName(databaseProperties.getProperty("Database"));
            ds.setUser(databaseProperties.getProperty("Login"));
            ds.setPassword(databaseProperties.getProperty("password"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
       public Connection getConnection() throws SQLServerException  
    {
        return ds.getConnection();
    }
        }

    

