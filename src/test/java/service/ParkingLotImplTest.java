package service;

import Model.Car;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParkingLotImplTest {
    ParkingLotImpl parkingLotImpl;

    @Test
    public void testParkCar_availableSpace() {
        parkingLotImpl = new ParkingLotImpl(1);
        int actual = parkingLotImpl.parkCar(new Car("1234", "white"));
        assertEquals(1, actual);
    }

    @Test
    public void testParkCar_No_availableSpace() {
        parkingLotImpl = new ParkingLotImpl(0);
        int actual = parkingLotImpl.parkCar(new Car("1234", "white"));
        assertEquals(-1, actual);
    }


    @Test
    public void testGetSlotNumberForRegistrationNumber_if_car_is_present() {
        parkingLotImpl = new ParkingLotImpl(1);
        parkingLotImpl.parkCar(new Car("1234", "white"));
        int actual = parkingLotImpl.getSlotNumberForRegistrationNumber("1234");
        assertEquals(1, actual);
    }

    @Test
    public void testGetSlotNumberForRegistrationNumber_if_car_is_not_present() {
        parkingLotImpl = new ParkingLotImpl(1);
        parkingLotImpl.parkCar(new Car("1234", "white"));
        int actual = parkingLotImpl.getSlotNumberForRegistrationNumber("2345");
        assertEquals(-1, actual);
    }

    @Test
    public void testDepartCar() {
        parkingLotImpl = new ParkingLotImpl(2);
        parkingLotImpl.parkCar(new Car("1234", "white"));
        parkingLotImpl.parkCar(new Car("2345", "black"));
        parkingLotImpl.departCar(2);
        int actual = parkingLotImpl.getAvailableSlots();
        assertEquals(1, actual);
    }

    @Test
    public void testGetRegistrationNumbersForCars() {
        parkingLotImpl = new ParkingLotImpl(3);
        parkingLotImpl.parkCar(new Car("1234", "white"));
        parkingLotImpl.parkCar(new Car("12345", "white"));
        parkingLotImpl.parkCar(new Car("2345", "black"));
        int actual1 = parkingLotImpl.getRegistrationNumbersForCars("white").size();
        int actual2 = parkingLotImpl.getRegistrationNumbersForCars("black").size();
        assertEquals(2, actual1);
        assertEquals(1, actual2);
    }

    @Test
    public void testGetSlotNumbersForCars() {
        parkingLotImpl = new ParkingLotImpl(3);
        parkingLotImpl.parkCar(new Car("1234", "white"));
        parkingLotImpl.parkCar(new Car("2345", "black"));
        parkingLotImpl.parkCar(new Car("12345", "white"));
        List<Integer> list = parkingLotImpl.getSlotNumbersForCars("white");
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(3);
        assertEquals(2, list.size());
        assertTrue(list1.equals(list));
    }
}
