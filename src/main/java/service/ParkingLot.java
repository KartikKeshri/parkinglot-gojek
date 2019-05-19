package service;

import Model.Car;

import java.util.List;

public interface ParkingLot {
    public int parkCar(Car car);
    public void departCar(int slotNumber);
    public void getParkinglotStatus();
    public List<Integer> getSlotNumbersForCars(String colour);
    public List<String> getRegistrationNumbersForCars(String colour);
    public int getSlotNumberForRegistrationNumber(String registrationNumber);
    public void exit();
}


