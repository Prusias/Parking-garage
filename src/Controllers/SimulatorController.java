package Controllers;

import Models.*;
import Views.*;

/**
 * Class that acts as the Controller for the Simulation
 * @author Mike van der Velde
 * @version 0.2
 * @since 0.2
 */
public class SimulatorController {
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;



    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    private SimulatorView simulatorView;
    private VehicleController vehicleController;

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
    public int getNumberOfRows() {
        return numberOfRows;
    }
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }
    public int getNumberOfOpenSpots() {
        return numberOfOpenSpots;
    }
    public void setNumberOfOpenSpots(int numberOfOpenSpots) {
        this.numberOfOpenSpots = numberOfOpenSpots;
    }
    public void incrementNumberOfOpenSpots() {
        numberOfOpenSpots++;
    }
    public void decrementNumberOfOpenSpots() {
        numberOfOpenSpots--;
    }

    public int getDay() {
        return day;
    }
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }

    public VehicleController getVehicleController() {
        return vehicleController;
    }

    /**
     * Initialize the Controllers.SimulatorController class
     * @param numberOfFloors
     * @param numberOfRows
     * @param numberOfPlaces
     */
    public SimulatorController(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = numberOfFloors*numberOfRows*numberOfPlaces;

        // These must be initialized in the correct order.
        vehicleController = new VehicleController(this);
        simulatorView = new SimulatorView(this);
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    private void tick() {
        advanceTime();
        vehicleController.handleExit();
        // updateViews(); Used to be Views.SimulatorView.tick:
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Vehicle vehicle = vehicleController.getVehicleAt(location);
                    if (vehicle != null) {
                        vehicle.tick();
                    }
                }
            }
        }
        simulatorView.updateView();
        //        //


        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        vehicleController.handleEntrance();
    }

    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }





}
