package service;

import Model.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class ParkingLotImpl implements ParkingLot {
    private int capacity;
    private int availableSlots;
    private HashMap<Integer, Car> parkingLotMap;
    private TreeSet<Integer> freeSlots;

    public ParkingLotImpl(int c) {
        capacity = c;
        availableSlots = c;
        parkingLotMap = new HashMap<Integer, Car>();
        freeSlots = new TreeSet<Integer>();
        for (int i = 0; i < capacity; i++) {
            parkingLotMap.put(i, null);
            freeSlots.add(i);
        }
    }

    public int parkCar(Car car) {
        int nextAvail;
        if (availableSlots == 0) {
            return -1;
        } else {
            nextAvail = freeSlots.first();
            parkingLotMap.put(nextAvail, car);
            availableSlots--;
            freeSlots.remove(nextAvail);
        }
        return (nextAvail + 1);
    }

    public void departCar(int slotNumber) {
        availableSlots++;
        freeSlots.add(slotNumber);
        parkingLotMap.put(slotNumber, null);
    }

    public void getParkinglotStatus() {
        System.out.println("Slot No. Registration No Colour");
        for (int i = 0; i < capacity; i++) {
            Car car = parkingLotMap.get(i);
            if (car != null) {
                System.out.println(i + 1 + "     " + car.getRegistrationNumber() + " " + car.getColour());
            }
        }
    }

    public void getSlotNumbersForCars(String colour) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < capacity; i++) {
            Car r = parkingLotMap.get(i);
            if (r != null && colour.equals(r.getColour())) {
                res.add(i + 1);
            }
        }
        printIntegerList(res);
    }

    public void getRegistrationNumbersForCars(String colour) {
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < capacity; i++) {
            Car car = parkingLotMap.get(i);
            if (car != null && colour.equals(car.getColour())) {
                res.add(car.getRegistrationNumber());
            }
        }
        printStringList(res);
    }

    public int getSlotNumberForRegistrationNumber(String registrationNumber) {
        int res = -1;
        for (int i = 0; i < capacity; i++) {
            Car car = parkingLotMap.get(i);
            if (car != null && registrationNumber.equals(car.getRegistrationNumber())) {
                res = i + 1;
            }
        }
        return res;
    }

    public void exit() {
        System.exit(0);
    }

    private void printStringList(List<String> list) {
        int i = 0;
        for (i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.println(list.get(i));
    }

    private void printIntegerList(List<Integer> list) {
        int i = 0;
        for (i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.println(list.get(i));
    }
}
