package org.carproject.dao;

import org.carproject.entity.BodyType;
import org.carproject.entity.Car;
import org.carproject.entity.Cars;
import org.carproject.entity.DriveWheels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarSimpleDAO implements CarDAO {


    protected Cars carsObj;


    //protected List<Car> cars;

    public CarSimpleDAO(){
        carsObj  = new Cars(new ArrayList<>());
        //cars = carsObj.getCars();
    }

    protected Cars getCarsObj() {
        return carsObj;
    }

    protected void setCarsObj(Cars carsObj) {
        this.carsObj = carsObj;
    }

    /*public void setCars(List<Car> cars) {
        this.cars = cars;
    }*/

    @Override
    public void addCar(Car car) {
        if(carsObj.getCars().isEmpty()){
            carsObj.getCars().add(car);
            return;
        } else {
            for(Car tempCar : carsObj.getCars()){
                if(tempCar.getVin().equals(car.getVin())){
                    return;
                }
            } carsObj.getCars().add(car);

        }

    }

    @Override
    public void updateCar(Car car) {
        Car carFromList = getCar(car.getVin());
        if(carFromList != null){
            carFromList.setMake(car.getMake());
            carFromList.setModel(car.getModel());
            carFromList.setMfgDate(car.getMfgDate());
            carFromList.setBodyType(car.getBodyType());
            carFromList.setColor(car.getColor());
            carFromList.setDriveWheels(car.getDriveWheels());
            carFromList.setNumDoors(car.getNumDoors());
        } else System.out.println("Car with this vin number was not found!");
    }

    @Override
    public void deleteCar(String vin) {
        for(int i = 0; i<carsObj.getCars().size(); i++){
            if(vin.equals(carsObj.getCars().get(i).getVin())){
                carsObj.getCars().remove(i);
                return;
            }
        }
    }

    @Override
    public Car getCar(String vin) {
        for(Car car : carsObj.getCars()){
            if(vin.equals(car.getVin())){
                return car;
            }
        } 
        return null;
    }

    @Override
    public List<Car> findCars() {
        return carsObj.getCars();
    }

    @Override
    public List<Car> sortCars(List<Car> cars) {
        return cars.stream().sorted(Comparator.comparing(Car::getMake).thenComparing(Car::getModel)).collect(Collectors.toList());
    }
}
