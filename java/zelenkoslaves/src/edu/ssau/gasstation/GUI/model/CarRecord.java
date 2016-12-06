package edu.ssau.gasstation.GUI.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by andrey on 05.12.16.
 */
public class CarRecord {
    private final SimpleIntegerProperty recordId;
    private final SimpleStringProperty carType;
    private final SimpleStringProperty fuelType;
    private final SimpleDoubleProperty tankVolume;

    public CarRecord(int recordId, String carType, String fuelType, Double tankVolume) {
        this.recordId = new SimpleIntegerProperty(recordId);
        this.carType = new SimpleStringProperty(carType);
        this.fuelType = new SimpleStringProperty(fuelType);
        this.tankVolume = new SimpleDoubleProperty(tankVolume);
    }

    public int getRecordId() {
        return recordId.get();
    }

    public SimpleIntegerProperty recordIdProperty() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId.set(recordId);
    }

    public String getCarType() {
        return carType.get();
    }

    public SimpleStringProperty carTypeProperty() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType.set(carType);
    }

    public String getFuelType() {
        return fuelType.get();
    }

    public SimpleStringProperty fuelTypeProperty() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType.set(fuelType);
    }

    public double getTankVolume() {
        return tankVolume.get();
    }

    public SimpleDoubleProperty tankVolumeProperty() {
        return tankVolume;
    }

    public void setTankVolume(double tankVolume) {
        this.tankVolume.set(tankVolume);
    }
}
