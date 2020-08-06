package org.carproject.business;

import org.carproject.dao.CarDAO;
import org.carproject.dao.CarDAOFactory;
import org.carproject.entity.Car;

import java.util.List;

public class CarManager {
    private CarDAO dao;

    public CarManager(){
        dao = CarDAOFactory.getCarDAO();
    }

    public void addCar(Car car){
        dao.addCar(car);
    }

    public void updateCar(Car car){
        dao.updateCar(car);
    }

    public void deleteCar(String vin){
        dao.deleteCar(vin);
    }

    public Car getCar(String vin){
        return dao.getCar(vin);
    }

    public List<Car> findCars(){
        return dao.findCars();
    }

    public List<Car> sortCars(List<Car> cars){
        return dao.sortCars(cars);
    }

}
