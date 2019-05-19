package Model;


import java.util.List;

public class ParkingSlot {
    private int capacity;
    private List<Car> cars;

    public ParkingSlot() {

    }

    public ParkingSlot(int capacity, List<Car> cars) {
        this.capacity = capacity;
        this.cars = cars;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
