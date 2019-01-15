package nl.hanze.experience;

import nl.hanze.experience.controllers.CarParkController;
import nl.hanze.experience.models.*;
import nl.hanze.experience.views.CarParkView;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

//TODO: Remove JFrame from here!!!
public class Simulation extends JFrame {
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    private CarParkController carParkController;

    private static final String AD_HOC = "1";
    private static final String PASS = "2";

    private VehicleQueue entranceVehicleQueue;
    private VehicleQueue entrancePassQueue;
    private VehicleQueue paymentVehicleQueue;
    private VehicleQueue exitVehicleQueue;

    int weekDayArrivals= 100; // average number of arriving vehicles per hour
    int weekendArrivals = 200; // average number of arriving vehicles per hour
    int weekDayPassArrivals= 50; // average number of arriving vehicles per hour
    int weekendPassArrivals = 5; // average number of arriving vehicles per hour

    int enterSpeed = 3; // number of vehicles that can enter per minute
    int paymentSpeed = 7; // number of vehicles that can pay per minute
    int exitSpeed = 5; // number of vehicles that can leave per minute

    // TODO: Where to put this? This doesn't seem like the proper place
    private void vehiclesArriving(){
        int numberOfVehicles = getNumberOfVehicles(weekDayArrivals, weekendArrivals);
        addArrivingVehicles(numberOfVehicles, AD_HOC);
        numberOfVehicles = getNumberOfVehicles(weekDayPassArrivals, weekendPassArrivals);
        addArrivingVehicles(numberOfVehicles, PASS);
    }

    final public Garage garage;

    public int getDay() {
        return day;
    }
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }

    public Simulation(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        garage = new Garage(numberOfFloors, numberOfRows, numberOfPlaces);

        entranceVehicleQueue = new VehicleQueue();
        entrancePassQueue = new VehicleQueue();
        paymentVehicleQueue = new VehicleQueue();
        exitVehicleQueue = new VehicleQueue();

        //TODO: Move all of his JPanel initialization
        this.carParkController = new CarParkController(garage);

        Container contentPane = getContentPane();
        contentPane.add(carParkController.getCarParkView(), BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    private void tick() {
        advanceTime();
        handleExit();
        // updateViews(); Used to be nl.hanze.experience.views.SimulatorView.tick:
        for (int floor = 0; floor < garage.getNumberOfFloors(); floor++) {
            for (int row = 0; row < garage.getNumberOfRows(); row++) {
                for (int place = 0; place < garage.getNumberOfPlaces(); place++) {
                    ParkingSpot parkingSpot = new ParkingSpot(floor, row, place);
                    Vehicle vehicle = garage.getVehicleAt(parkingSpot);
                    if (vehicle != null) {
                        vehicle.tick();
                    }
                }
            }
        }
        carParkController.updateView();

        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handleEntrance();
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

    // TODO: Where to put this? This doesn't seem like the proper place
    private void vehiclesEntering(VehicleQueue queue){
        int i=0;
        // Remove vehicle from the front of the queue and assign to a parking space.
        while (queue.vehiclesInQueue()>0 &&
                garage.getNumberOfOpenSpots()>0 &&
                i<enterSpeed) {
            Vehicle vehicle = queue.removeVehicle();
            ParkingSpot parkingSpot = garage.getFirstFreeParkingSpot();
            garage.setVehicleAt(parkingSpot, vehicle);
            i++;
        }
    }

    // TODO: Where to put this? This doesn't seem like the proper place
    private void vehiclesReadyToLeave(){
        // Add leaving vehicles to the payment queue.
        Vehicle vehicle = garage.getFirstLeavingVehicle();
        while (vehicle !=null) {
            if (vehicle.getHasToPay()){
                vehicle.setIsPaying(true);
                paymentVehicleQueue.addVehicle(vehicle);
            }
            else {
                vehiclesLeavesSpot(vehicle);
            }
            vehicle = garage.getFirstLeavingVehicle();
        }
    }

    // TODO: Where to put this? This doesn't seem like the proper place
    private void vehiclesPaying(){
        // Let vehicles pay.
        int i=0;
        while (paymentVehicleQueue.vehiclesInQueue()>0 && i < paymentSpeed){
            Vehicle vehicle = paymentVehicleQueue.removeVehicle();
            // TODO Handle payment.
            vehiclesLeavesSpot(vehicle);
            i++;
        }
    }

    // TODO: Where to put this? This doesn't seem like the proper place
    private void vehiclesLeaving(){
        // Let vehicles leave.
        int i=0;
        while (exitVehicleQueue.vehiclesInQueue()>0 && i < exitSpeed){
            exitVehicleQueue.removeVehicle();
            i++;
        }
    }
    // TODO: Where to put this? This doesn't seem like the proper place
    private int getNumberOfVehicles(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of vehicles that arrive per hour.
        int averageNumberOfVehiclesPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of vehicles that arrive this minute.
        double standardDeviation = averageNumberOfVehiclesPerHour * 0.3;
        double numberOfVehiclesPerHour = averageNumberOfVehiclesPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfVehiclesPerHour / 60);
    }
    // TODO: Where to put this? This doesn't seem like the proper place
    private void addArrivingVehicles(int numberOfVehicles, String type){
        // Add the vehicles to the back of the queue.
        switch(type) {
            case AD_HOC:
                for (int i = 0; i < numberOfVehicles; i++) {
                    entranceVehicleQueue.addVehicle(new AdHocCar());
                }
                break;
            case PASS:
                for (int i = 0; i < numberOfVehicles; i++) {
                    entrancePassQueue.addVehicle(new ParkingPassCar());
                }
                break;
        }
    }
    // TODO: Where to put this? This doesn't seem like the proper place
    private void vehiclesLeavesSpot(Vehicle vehicle){
        garage.removeVehicleAt(vehicle.getParkingSpot());
        exitVehicleQueue.addVehicle(vehicle);
    }
    // TODO: Where to put this? This doesn't seem like the proper place
    protected void handleEntrance(){
        vehiclesArriving();
        vehiclesEntering(entrancePassQueue);
        vehiclesEntering(entranceVehicleQueue);
    }
    // TODO: Where to put this? This doesn't seem like the proper place
    protected void handleExit(){
        vehiclesReadyToLeave();
        vehiclesPaying();
        vehiclesLeaving();
    }

}
