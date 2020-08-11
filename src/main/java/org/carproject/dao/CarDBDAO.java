package org.carproject.dao;

import org.carproject.database.ConnectionBuilder;
import org.carproject.database.ConnectionBuilderFactory;
import org.carproject.entity.BodyType;
import org.carproject.entity.Car;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDBDAO implements CarDAO{
    private ConnectionBuilder connectionBuilder;
    private static final String SELECT = "SELECT * FROM cars";
    private static final String SELECT_ONE = "SELECT * FROM cars WHERE vin=?";
    private static final String INSERT = "INSERT INTO cars (vin, make, model, mfg_date, body_type) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE cars SET make=?, model = ?, mfg_date=?, body_type=? WHERE vin=?";
    private static final String DELETE = "DELETE FROM cars WHERE vin=?";

    {
        try {
            connectionBuilder = ConnectionBuilderFactory.getConnectionBuilder();
        } catch (IOException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.setVin("quququ0118913");
        car.setMake("Skoda");
        car.setModel("Octavia");
        car.setMfgDate(java.sql.Date.valueOf("2018-07-22"));
        car.setBodyType(BodyType.WAGON);

        CarDAO carDAO = new CarDBDAO();
        carDAO.updateCar(car);
        //carDAO.deleteCar(car.getVin());
        Car tempCar = carDAO.getCar(car.getVin());
        System.out.println(tempCar);
      /* List<Car> cars = carDAO.findCars();
        for(Car tempCar : cars){
            System.out.println(tempCar);
        }*/
    }

    @Override
    public void addCar(Car car) {
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement psmt = connection.prepareStatement(INSERT)) {
            psmt.setString(1, car.getVin());
            psmt.setString(2, car.getMake());
            psmt.setString(3, car.getModel());
            psmt.setDate(4, new java.sql.Date(car.getMfgDate().getTime()));
            psmt.setObject(5, car.getBodyType(), Types.OTHER);
            psmt.executeUpdate();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCar(Car car) {
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement psmt = connection.prepareStatement(UPDATE)) {
            psmt.setString(5, car.getVin());
            psmt.setString(1, car.getMake());
            psmt.setString(2, car.getModel());
            psmt.setDate(3, new java.sql.Date(car.getMfgDate().getTime()));
            psmt.setObject(4, car.getBodyType(), Types.OTHER);
            psmt.executeUpdate();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCar(String vin) {
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement psmt = connection.prepareStatement(DELETE)) {
            psmt.setString(1, vin);
            psmt.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car getCar(String vin) {
        Car car = new Car();
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement psmt = connection.prepareStatement(SELECT_ONE)) {
            psmt.setString(1, vin);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){

                car.setVin(rs.getString(1));
                car.setMake(rs.getString(2));
                car.setModel(rs.getString(3));
                car.setMfgDate(rs.getDate(4));
                car.setBodyType(BodyType.valueOf(rs.getString(5)));
                return car;
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> findCars() {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement psmt = connection.prepareStatement(SELECT)) {

           ResultSet rs = psmt.executeQuery();
           while(rs.next()){
               Car car = new Car();
               car.setVin(rs.getString(1));
               car.setMake(rs.getString(2));
               car.setModel(rs.getString(3));
               car.setMfgDate(rs.getDate(4));
               car.setBodyType(BodyType.valueOf(rs.getString(5)));
               cars.add(car);
           }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public List<Car> sortCars(List<Car> cars) {
        return null;
    }
}
