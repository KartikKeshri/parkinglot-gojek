import Model.Car;
import Model.ParkingSlot;
import constants.Constants;
import exception.ParkingLotException;
import service.ParkingLot;
import service.ParkingLotImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Main {
    private static ParkingLot parkingLot = null;
    private static ParkingSlot slot = null;

    public static void main(String args[]) {
        if (args.length > 0) {
            readFile(args[0],parkingLot);
        } else {
            command();
        }
    }

    private static void readFile(final String fileName, final ParkingLot parkingLot) {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String line;
            Scanner scanner;
            while ((line = br.readLine()) != null) {
                scanner = new Scanner(line).useDelimiter("\\s");
                String command = scanner.next();
                executeCommand(command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void command() {
        Scanner sc = new Scanner(System.in);
        String command = sc.next();
        while (!Constants.EXIT.equalsIgnoreCase(command)) {
            try {
                //Execute Command
                executeCommand(command);
            } catch (ParkingLotException e) {
                //Display Exception if any
                System.out.println(e.getMessage());
            }
            //Get next command
            command = sc.next();
        }
    }

    private static void executeCommand(String command) throws ParkingLotException{
        Scanner sc = new Scanner(System.in);
        if (command.equals(Constants.CREATE_PARKING_LOT)) {
            int capacity = Integer.parseInt(sc.next());
            createParkingLot(capacity);
        } else if (command.equals(Constants.PARK_CAR)) {
            String regNo = sc.next();
            String colour = sc.next();
            parkingLot.parkCar(new Car(regNo, colour));
        } else if (command.equals(Constants.LEAVE)) {
            int slotNumber = sc.nextInt();
            parkingLot.departCar(slotNumber);
        } else if (command.equals(Constants.LOT_STATUS)) {
            parkingLot.getParkinglotStatus();
        } else if (command.equals(Constants.SEARCH_SLOT_BY_CAR)) {
            parkingLot.getSlotNumberForRegistrationNumber(sc.next());
        } else if (command.equals(Constants.SEARCH_CAR_BY_COLOUR)) {
            parkingLot.getRegistrationNumbersForCars(sc.next());
        } else if (command.equals(Constants.SEARCH_SLOT_BY_COLOUR)) {
            parkingLot.getSlotNumbersForCars(sc.next());
        } else {
            System.out.println("Invalid Command!");
        }
    }

    public static void createParkingLot(int capacity) throws ParkingLotException{
        if (parkingLot != null) throw new ParkingLotException("Parking lot is already initialized!");
        parkingLot = new ParkingLotImpl(capacity);
        System.out.println("Created a parking lot with " + capacity + " slots");
    }
}