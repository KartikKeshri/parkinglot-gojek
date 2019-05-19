package service;

import Model.Car;
import Model.ParkingSlot;
import exception.ParkingLotException;

import java.util.List;

public interface ParkingLot {
    public int parkCar(Car car);
    public void departCar(int slotNumber);
    public void getParkinglotStatus();
    public void getSlotNumbersForCars(String colour);
    public void getRegistrationNumbersForCars(String colour);
    public int getSlotNumberForRegistrationNumber(String registrationNumber);
    public void exit();
}


