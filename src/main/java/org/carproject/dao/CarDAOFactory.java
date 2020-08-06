package org.carproject.dao;


import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.PropertyResourceBundle;

public class CarDAOFactory {
    //get car data access object from file CarDAOclass.properties
    public static final String CARDAO_PROPERTIES = "src/main/resources/CarDAOclass.properties";

    public static CarDAO getCarDAO() {
        String propertyName = "CarDAO.class";
       // PropertyResourceBundle prb = (PropertyResourceBundle) PropertyResourceBundle.getBundle("CarDAOclass");
        Properties carDaoProperties = new Properties();
        try {
            carDaoProperties.load(new FileInputStream(CARDAO_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String className = carDaoProperties.getProperty(propertyName);
        try {
            // Загружаем класс по имени
            Class cl = Class.forName(className);
            // Т.к. наш класс должен имплементировать интерфейс FinanceInfoBuilder
            // то мы можем сделать приведение к интерфейсу
            CarDAO carDAO = (CarDAO)cl.getDeclaredConstructor().newInstance();
            return carDAO;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            ex.printStackTrace(System.out);
        }
        return new CarSimpleDAO();
    }
}
