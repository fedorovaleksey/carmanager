package org.carproject.gui;

import org.carproject.entity.Car;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class CarTableModel extends AbstractTableModel {

    private final List<Car> cars;
    private final String[] headers = {"Vin", "Make", "Model", "Mfg Date", "Body type"};
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public CarTableModel(List<Car> cars){
        this.cars = cars;
    }

    @Override
    public int getRowCount() {
        return cars.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(int col){
        return headers[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Car car = cars.get(row);
        switch (col){
            case 0:
                return car.getVin();
            case 1:
                return car.getMake();
            case 2:
                return car.getModel();
            case 3:
                return format.format(car.getMfgDate());
            default:
                return car.getBodyType();
        }
    }
}
