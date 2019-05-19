import Model.Car;
import constants.Constants;
import exception.ParkingLotException;
import service.ParkingLot;
import service.ParkingLotImpl;

import java.io.*;
import java.util.Scanner;

class Main {
    private static ParkingLot parkingLot = null;

    public static void main(String args[]) {
        Scanner sc;
        if (args.length > 0) {
            String fileName = args[0];
            File f = new File(fileName);
            try {
                sc = new Scanner(f);
                command(sc);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        } else {
            sc = new Scanner(System.in);
            command(sc);
        }
    }

    private static void command(Scanner sc) {
        String command = sc.nextLine();
        String[] commands = command.split(" ");
        while (!Constants.EXIT.equalsIgnoreCase(commands[0])) {
            try {
                //Execute Command
                executeCommand(commands);
            } catch (ParkingLotException e) {
                //Display Exception if any
                System.out.println(e.getMessage());
            }
            //Get next command
            command = sc.nextLine();
            commands = command.split(" ");
        }
    }

    private static void executeCommand(String[] commands) throws ParkingLotException {
        Scanner sc = new Scanner(System.in);
        if (commands[0].equals(Constants.CREATE_PARKING_LOT)) {
            createParkingLot(Integer.parseInt(commands[1]));
        } else if (commands[0].equals(Constants.PARK_CAR)) {
            String regNo = commands[1];
            String colour = commands[2];
            int slotNumber = parkingLot.parkCar(new Car(regNo, colour));
            if (slotNumber != -1) {
                System.out.println("Allocated Slot Number: " + slotNumber);
            } else
                System.out.println("Sorry, parking lot is full");
        } else if (commands[0].equals(Constants.LEAVE)) {
            int slotNumber = Integer.parseInt(commands[1]);
            parkingLot.departCar(slotNumber - 1);
            System.out.println("Slot number " + slotNumber + " is free");
        } else if (commands[0].equals(Constants.LOT_STATUS)) {
            parkingLot.getParkinglotStatus();
        } else if (commands[0].equals(Constants.SEARCH_SLOT_BY_CAR)) {
            int slotNumber = parkingLot.getSlotNumberForRegistrationNumber(commands[1]);
            if (slotNumber != -1) {
                System.out.println(slotNumber);
            } else
                System.out.println("Not Found");
        } else if (commands[0].equals(Constants.SEARCH_CAR_BY_COLOUR)) {
            parkingLot.getRegistrationNumbersForCars(commands[1]);
        } else if (commands[0].equals(Constants.SEARCH_SLOT_BY_COLOUR)) {
            parkingLot.getSlotNumbersForCars(commands[1]);
        } else {
            System.out.println("Invalid Command!");
        }
    }

    public static void createParkingLot(int capacity) throws ParkingLotException {
        if (parkingLot != null) throw new ParkingLotException("Parking lot is already initialized!");
        parkingLot = new ParkingLotImpl(capacity);
        System.out.println("Created a parking lot with " + capacity + " slots");
    }
}