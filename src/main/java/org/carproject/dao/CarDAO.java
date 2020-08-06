package org.carproject.dao;

import org.carproject.entity.Car;

import java.util.List;

public interface CarDAO { // Car Data Access Object
    void addCar(Car car);

    void updateCar(Car car);

    void deleteCar(String vin);

    Car getCar(String vin);

    List<Car> findCars();

    List<Car> sortCars(List<Car> cars);
}
