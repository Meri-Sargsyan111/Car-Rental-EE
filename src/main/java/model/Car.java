package model;

import enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
public class Car {

        private int id;
        private String brand;
        private String model;
        private int year;
        private double dailyRate;
        private CarStatus status;

    public Car(int id, String brend, String model, int year, double dailyRate, CarStatus status) {

    }

    public Car() {


    }


    public Object getId() {
        return null;
    }

    public Object getBrand() {
        return brand;
    }

    public void setBrand(Object brand) {
        this.brand = brand.toString();
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model.toString();
    }

    public Object getYear() {
        return year;
    }

    public void setYear(Object year) {
        this.year = (int) year;
    }

    public Object getDailyRate() {
        return status;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = (CarStatus) status;
    }

    public void setDailyRate(double dailyRate) {

    }
}

