package org.carproject.gui;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.text.DateFormat;

public class DateTextField extends JDateChooser {
    public DateTextField(){
        getDateEditor().setDateFormatString("dd.MM.yyyy");

    }
}
