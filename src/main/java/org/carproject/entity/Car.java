package org.carproject.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Car{
  @XmlElement
  private String vin; //Вин номер авто
  @XmlElement
  private String make; // Производитель авто
  @XmlElement
  private String model; // Модель авто
  @XmlElement
  private Date mfgDate; // Дата производства
  @XmlElement
  private BodyType bodyType; // Тип кузова
  private String color; // Цвет авто
  private DriveWheels driveWheels; // Ведущие колеса
  private int numDoors; // Число дверей

  public Car(){

  }

  public Car(String vin, String make, String model, Date mfgDate, BodyType bodyType, String color, DriveWheels driveWheels, int numDoors) {
    this.vin = vin;
    this.make = make;
    this.model = model;
    this.mfgDate = mfgDate;
    this.bodyType = bodyType;
    this.color = color;
    this.driveWheels = driveWheels;
    this.numDoors = numDoors;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Date getMfgDate() {
    return mfgDate;
  }

  public void setMfgDate(Date mfgDate) {
    this.mfgDate = mfgDate;
  }

  public BodyType getBodyType() {
    return bodyType;
  }

  public void setBodyType(BodyType bodyType) {
    this.bodyType = bodyType;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public DriveWheels getDriveWheels() {
    return driveWheels;
  }

  public void setDriveWheels(DriveWheels driveWheels) {
    this.driveWheels = driveWheels;
  }

  public int getNumDoors() {
    return numDoors;
  }

  public void setNumDoors(int numDoors) {
    this.numDoors = numDoors;
  }

  @java.lang.Override
  public java.lang.String toString() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    return "Car{" +
            "vin='" + vin + '\'' +
            ", make='" + make + '\'' +
            ", model='" + model + '\'' +
            ", mfgDate=" + simpleDateFormat.format(mfgDate) +
            ", bodyType=" + bodyType +

            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Car car = (Car) o;
    return vin.equals(car.vin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vin);
  }
}
