package org.carproject.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ConnectionBuilderFactory {
    private static ConnectionBuilder connectionBuilder;
    private static Properties properties = new Properties();
    private static final String PROPERTIES_FILE = "src/main/java/org/carproject/database/DBaccess.properties";
    private static final String CONNECTION_BUILDER = "connectionBuilder";

    public static ConnectionBuilder getConnectionBuilder() throws IOException, ClassNotFoundException, NoSuchMethodException {
        properties.load(new FileReader(PROPERTIES_FILE));
        try {
            connectionBuilder = (ConnectionBuilder) Class.forName(properties.getProperty(CONNECTION_BUILDER)).getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return connectionBuilder;
    }
}
