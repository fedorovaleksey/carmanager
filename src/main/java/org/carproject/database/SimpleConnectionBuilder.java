package org.carproject.database;

import org.carproject.entity.BodyType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class SimpleConnectionBuilder implements ConnectionBuilder{
    private static Connection connection;
    private static final Properties properties = new Properties();
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String URL = "url";
    private static final String DRIVERNAME = "driverName";
    private static final String PROPERTYFILE = "src/main/java/org/carproject/database/DBaccess.properties";
    public Connection getConnection() throws IOException, SQLException {
        properties.load(new FileInputStream(PROPERTYFILE));
        String userName = properties.getProperty(USERNAME);
        String password = properties.getProperty(PASSWORD);
        String url = properties.getProperty(URL);
        //String driverName = properties.getProperty(DRIVERNAME);
        //Class.forName(driverName);

        connection = DriverManager.getConnection(url, userName, password);
        return connection;

    }

    public static void main(String[] args) throws IOException, SQLException {
        String INSERT = "INSERT INTO cars (vin, make, model, mfg_date, body_type) VALUES (?, ?, ?, ?, ?)";
        try(
                Connection connection = ConnectionBuilderFactory.getConnectionBuilder().getConnection();
                Statement stmt = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        ) {

            preparedStatement.setString(1, "cheg4re");
            preparedStatement.setString(2, "Renault");
            preparedStatement.setString(3, "Arcana");
            preparedStatement.setDate(4, Date.valueOf(LocalDate.of(2020, 11,19)));
            preparedStatement.setObject(5, BodyType.SUV, Types.OTHER);

            preparedStatement.executeUpdate();

            ResultSet rs = stmt.executeQuery("SELECT * FROM cars");
            while (rs.next()) {
                System.out.println(rs.getString("model") + " : " + rs.getString("make"));
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
