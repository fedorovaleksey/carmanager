package org.carproject.dao;

import org.carproject.database.ConnectionBuilder;
import org.carproject.database.ConnectionBuilderFactory;
import org.carproject.entity.Car;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CarDBDAO implements CarDAO{
    private ConnectionBuilder connectionBuilder;
    private static final String SELECT = "SELECT * FROM cars";
    private static final String SELECT_ONE = "SELECT * FROM cars WHERE vin=?";
    private static final String INSERT = "INSERT cars (vin, make, model, date, body_type) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE cars SET make=?, model = ?, date=?, body_type=? WHERE vin=?";
    private static final String DELETE = "DELETE FROM cars WHERE vin=?";

    {
        try {
            connectionBuilder = ConnectionBuilderFactory.getConnectionBuilder();
        } catch (IOException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCar(Car car) {
        try {
            Connection connection = connectionBuilder.getConnection();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public void deleteCar(String vin) {

    }

    @Override
    public Car getCar(String vin) {
        return null;
    }

    @Override
    public List<Car> findCars() {
        return null;
    }

    @Override
    public List<Car> sortCars(List<Car> cars) {
        return null;
    }
}
