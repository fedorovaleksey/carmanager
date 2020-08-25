package org.carproject.database;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConPoolConnectionBuilder implements ConnectionBuilder {
    
    private static BasicDataSource basicDataSource = new BasicDataSource();
    private static final Properties properties = new Properties();
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String URL = "url";
    //private static final String DRIVERNAME = "driverName";
    private static final String PROPERTYFILE = "src/main/resources/org/carproject/database/DBaccess.properties";

    static{
        try {
            properties.load(new FileInputStream(PROPERTYFILE));
            basicDataSource.setUrl(properties.getProperty(URL));
            basicDataSource.setUsername(properties.getProperty(USERNAME));
            basicDataSource.setPassword(properties.getProperty(PASSWORD));


            basicDataSource.setMinIdle(5);
            basicDataSource.setMaxIdle(10);
            basicDataSource.setMaxOpenPreparedStatements(100);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Connection getConnection() throws IOException, SQLException {
        return basicDataSource.getConnection();
    }
}
