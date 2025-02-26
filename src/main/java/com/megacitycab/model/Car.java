package com.megacitycab.model;

public class Car {
    
    private String carId;
    private String model;
    private String licensePlate;

    public Car(String carId, String model, String licensePlate) {
        this.carId = carId;
        this.model = model;
        this.licensePlate = licensePlate;
    }
    public String getCarId() {
        return carId;
    }
    public String getModel() {
        return model;
    }
    public String getLicensePlate() {
        return licensePlate;
    }

}
