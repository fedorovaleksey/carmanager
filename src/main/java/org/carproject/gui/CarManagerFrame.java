package org.carproject.gui;


import org.carproject.business.CarManager;
import org.carproject.entity.Car;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarManagerFrame extends JFrame implements ActionListener {

    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";

    private final CarManager carManager = new CarManager();
    private final JTable carTable = new JTable();

    public CarManagerFrame(){
        carTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridheight = GridBagConstraints.REMAINDER;
        // Элемент раздвигается на весь размер ячейки
        gbc.fill = GridBagConstraints.BOTH;
        // Но имеет границы - слева, сверху и справа по 5. Снизу - 0
        gbc.insets = new Insets(5, 5, 5, 5);
 
        // Создаем панель для кнопок
        JPanel btnPanel = new JPanel();
        // усанавливаем у него layout
        btnPanel.setLayout(gbl);
        // Создаем кнопки
        btnPanel.add(createButton(gbl, gbc, "Обновить", LOAD));
        btnPanel.add(createButton(gbl, gbc, "Добавить", ADD));
        btnPanel.add(createButton(gbl, gbc, "Исправить", EDIT));
        btnPanel.add(createButton(gbl, gbc, "Удалить", DELETE));
 
        // Создаем панель для левой колокни с кнопками
        JPanel bottom = new JPanel();
        // Выставляем layout BorderLayout
        bottom.setLayout(new BorderLayout());
        // Кладем панель с кнопками в верхнюю часть
        bottom.add(btnPanel, BorderLayout.CENTER);
        // Кладем панель для левой колонки на форму слева - WEST
        add(bottom, BorderLayout.SOUTH);
 
        // Кладем панель со скролингом, внутри которой нахоится наша таблица
        // Теперь таблица может скроллироваться
        add(new JScrollPane(carTable), BorderLayout.CENTER);
 
        // выставляем координаты формы
        setBounds(100, 200, 900, 400);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // Загружаем контакты
        setMinimumSize(new Dimension(500, 300));
        setTitle("Car Manager");
        loadCars();
    }
    
    private JButton createButton(GridBagLayout gbl, GridBagConstraints gbc, String title, String action){
        JButton button = new JButton(title);
        button.setActionCommand(action);
        button.addActionListener(this);
        gbl.setConstraints(button, gbc);      
        return button;
    }
    
    private void loadCars(){
        CarTableModel carTableModel = new CarTableModel(carManager.findCars());
        carTable.setModel(carTableModel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action){
            case LOAD:
                loadCars();
                break;
            case ADD:
                addCar();
                break;
            case EDIT:
                editCar();
                break;
            case DELETE:
                deleteCar();
                break;
        }
    }

    private void deleteCar() {
        int sr = carTable.getSelectedRow();
        if(sr==-1){
            JOptionPane.showMessageDialog(this, "Select the car you want to delete!");
            return;
        }
        System.out.println(sr);
        String vin = (String) carTable.getValueAt(sr, 0);
        System.out.println(vin);
        carManager.deleteCar(vin);
        loadCars();
    }

    private void editCar() {

        int sr = carTable.getSelectedRow();
        if(sr==-1){
            JOptionPane.showMessageDialog(this, "Select the car you want to update!");
            return;
        }
        Car car = carManager.getCar((String) carTable.getValueAt(sr,0));
        EditCarDialog ecd = new EditCarDialog(car, "Edit car");

        if(ecd.isSave()){
            Car newCar = ecd.getCar();
            carManager.updateCar(newCar);
        }
        loadCars();
    }

    private void addCar() {
        EditCarDialog ecd = new EditCarDialog("Add car");

        if(ecd.isSave()){
            Car newCar = ecd.getCar();
            carManager.addCar(newCar);
        }
        loadCars();
    }
}
