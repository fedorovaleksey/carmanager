package org.carproject.gui;

import org.carproject.entity.BodyType;
import org.carproject.entity.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class EditCarDialog extends JDialog implements ActionListener {
    // Заголовки кнопок
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";

    // Размер отступа
 //   private static final int PAD = 10;
    // Ширина метки
 //   private static final int W_L = 100;
    //Ширина поля для ввода
 //   private static final int W_T = 300;
    // Ширина кнопки
  //  private static final int W_B = 120;
    // высота элемента - общая для всех
 //   private static final int H_B = 25;

    // Поле для ввода Имени
    private final JLabel vinLabel = new JLabel("Vin: ");
    private final JTextField vinTextField = new JTextField(20);
    // Поле для ввода Фамилии
    private final JLabel makeLabel = new JLabel("Make: ");
    private final JTextField makeTextField = new JTextField(20);
    // Поле для ввода Телефона
    private final JLabel modelLabel = new JLabel("Model: ");
    private final JTextField modelTextField = new JTextField(20);
    // Поле для ввода E-mail
    private final JLabel mfgDateLabel = new JLabel("Mfg Date: ");
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
    private final DateTextField mfgDateTextField = new DateTextField();
    
    private final JLabel bodyTypeLabel = new JLabel("Body Type: ");
    private final JComboBox bodyTypeComboBox = new JComboBox(BodyType.values());

    // Поле для хранения ID контакта, если мы собираемся редактировать
    // Если это новый контакт - cjntactId == null
    //private Long contactId = null;
    // Надо ли записывать изменения после закрытия диалога
    private boolean save = false;
    private String title = null;

    public EditCarDialog(String title) {
        this(null, title);
    }

    public EditCarDialog(Car car, String title) {
        // Убираем layout - будем использовать абсолютные координаты
       // setLayout(new GridBagLayout());
        this.title = title;
        setTitle(title);
        // Выстраиваем метки и поля для ввода
        add(buildFields(), BorderLayout.CENTER);
        
        // Если нам передали контакт - заполняем поля формы
        initFields(car);

        // выстариваем кнопки
        buildButtons();
        //setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        // Диалог в модальном режиме - только он активен
        setModal(true);
        // Запрещаем изменение размеров
        setResizable(false);
        // Выставляем размеры формы
        setBounds(300, 300, 400, 250);
        // Делаем форму видимой
        setVisible(true);
    }

    // Размещаем метки и поля ввода на форме
    private JPanel buildFields() {
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0, 5);
        // Набор метки и поля для VIN
        gbc.anchor = GridBagConstraints.EAST;
        fieldsPanel.add(vinLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        fieldsPanel.add(vinTextField, gbc);
        // Набор метки и поля для MAKE
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        fieldsPanel.add(makeLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        fieldsPanel.add(makeTextField, gbc);

        // Набор метки и поля для Model
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        fieldsPanel.add(modelLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        fieldsPanel.add(modelTextField, gbc);

        // Набор метки и поля для MfgDate
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        fieldsPanel.add(mfgDateLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        //mfgDateTextField.setColumns(30);
        fieldsPanel.add(mfgDateTextField, gbc);
        // Набор метки и поля для BodyType
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        fieldsPanel.add(bodyTypeLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        fieldsPanel.add(bodyTypeComboBox, gbc);
        
        return fieldsPanel;
    }

    // Если нам епередали контакт - заполняем поля из контакта
    private void initFields(Car car) {
        if (car != null) {
            vinTextField.setText(car.getVin());
            vinTextField.setEditable(false);
            makeTextField.setText(car.getMake());
            modelTextField.setText(car.getModel());
            mfgDateTextField.setDate(car.getMfgDate());
            bodyTypeComboBox.setSelectedItem(car.getBodyType());

        }
    }

    // Размещаем кнопки на форме
    private void buildButtons() {
        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout());

        JButton btnSave = new JButton("SAVE");
        btnSave.setActionCommand(SAVE);
        btnSave.addActionListener(this);

        panel.add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);

        panel.add(btnCancel);

        add(panel, BorderLayout.SOUTH);
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        // Если нажали кнопку SAVE (сохранить изменения) - запоминаем этой
        save = SAVE.equals(action);
        // Закрываем форму
        setVisible(false);
    }

    // Надо ли сохранять изменения
    public boolean isSave() {
        return save;
    }

    // Создаем контакт из заполенных полей, который можно будет записать
    public Car getCar() {
        Car car = new Car();
        car.setVin(vinTextField.getText());
        car.setMake(makeTextField.getText());
        car.setModel(modelTextField.getText());
        car.setMfgDate(mfgDateTextField.getDate());
        car.setBodyType((BodyType)bodyTypeComboBox.getSelectedItem());
        return car;
    }
}
