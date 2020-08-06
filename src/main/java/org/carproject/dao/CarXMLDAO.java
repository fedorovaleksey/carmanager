package org.carproject.dao;


import org.carproject.entity.BodyType;
import org.carproject.entity.Car;
import org.carproject.entity.Cars;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class CarXMLDAO extends CarSimpleDAO {

    public CarXMLDAO(){
        super();
        getFromXML();
    }

    public static void main(String[] args) {
        Car car1 = new Car();
        car1.setVin("lti32t78");
        car1.setMake("Dodge");
        car1.setModel("Viper");
        try{
        car1.setMfgDate(new SimpleDateFormat("dd.MM.yyyy").parse("11.10.2015"));
    } catch(
    ParseException e)

    {
        e.printStackTrace();
    }
        car1.setBodyType(BodyType.SEDAN);

    CarDAO carXMLDAO = CarDAOFactory.getCarDAO();
        System.out.println("OK");

    carXMLDAO.addCar(car1);
    for(Car car : carXMLDAO.findCars()){
        System.out.println(car);
    }
        ((CarXMLDAO) carXMLDAO).writeToXML();

    }

    private void writeToXML(){
        try {
            JAXBContext context = JAXBContext.newInstance(org.carproject.entity.Cars.class);
            Marshaller marshaller = context.createMarshaller();
            File file = new File("src/main/resources/cars.xml");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(getCarsObj(), new FileOutputStream(file));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getFromXML(){
        try {
            JAXBContext context = JAXBContext.newInstance(org.carproject.entity.Cars.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("src/main/resources/cars.xml");
            if(!file.exists()){
                System.out.println("File does not exist");
                try {
                    file.createNewFile();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            
            carsObj = (Cars) unmarshaller.unmarshal(new FileInputStream(file));
            //setCars(carsObj.getCars());
            setCarsObj(carsObj);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void addCar(Car car){
        super.addCar(car);
        writeToXML();
    }
    
     @Override
    public void updateCar(Car car) {
        super.updateCar(car);
         writeToXML();
    }
    
     @Override
    public void deleteCar(String vin) {
        super.deleteCar(vin);
        writeToXML();
    }

}
