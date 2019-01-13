package Controllers;

import Models.*;

import java.util.Random;

public class VehicleController {
    private static final String AD_HOC = "1";
    private static final String PASS = "2";

    private Vehicle[][][] vehicles;

    private VehicleQueue entranceVehicleQueue;
    private VehicleQueue entrancePassQueue;
    private VehicleQueue paymentVehicleQueue;
    private VehicleQueue exitVehicleQueue;

    private SimulatorController simController;

    int weekDayArrivals= 100; // average number of arriving vehicles per hour
    int weekendArrivals = 200; // average number of arriving vehicles per hour
    int weekDayPassArrivals= 50; // average number of arriving vehicles per hour
    int weekendPassArrivals = 5; // average number of arriving vehicles per hour

    int enterSpeed = 3; // number of vehicles that can enter per minute
    int paymentSpeed = 7; // number of vehicles that can pay per minute
    int exitSpeed = 5; // number of vehicles that can leave per minute

    public VehicleController(SimulatorController simulatorController) {
        entranceVehicleQueue = new VehicleQueue();
        entrancePassQueue = new VehicleQueue();
        paymentVehicleQueue = new VehicleQueue();
        exitVehicleQueue = new VehicleQueue();

        this.simController = simulatorController;

        vehicles = new Vehicle[simController.getNumberOfFloors()][simController.getNumberOfRows()][simController.getNumberOfPlaces()];
    }

    public Vehicle getVehicleAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return vehicles[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setVehicleAt(Location location, Vehicle vehicle) {
        if (!locationIsValid(location)) {
            return false;
        }
        Vehicle oldVehicle = getVehicleAt(location);
        if (oldVehicle == null) {
            vehicles[location.getFloor()][location.getRow()][location.getPlace()] = vehicle;
            vehicle.setLocation(location);
            simController.decrementNumberOfOpenSpots();
            return true;
        }
        return false;
    }

    public Vehicle removeVehicleAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Vehicle vehicle = getVehicleAt(location);
        if (vehicle == null) {
            return null;
        }
        vehicles[location.getFloor()][location.getRow()][location.getPlace()] = null;
        vehicle.setLocation(null);
        simController.incrementNumberOfOpenSpots();
        return vehicle;
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= simController.getNumberOfFloors() || row < 0 || row > simController.getNumberOfFloors() || place < 0 || place > simController.getNumberOfPlaces()) {
            return false;
        }
        return true;
    }


    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < simController.getNumberOfFloors(); floor++) {
            for (int row = 0; row < simController.getNumberOfRows(); row++) {
                for (int place = 0; place < simController.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getVehicleAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public Vehicle getFirstLeavingVehicle() {
        for (int floor = 0; floor < simController.getNumberOfFloors(); floor++) {
            for (int row = 0; row < simController.getNumberOfRows(); row++) {
                for (int place = 0; place < simController.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Vehicle vehicle = getVehicleAt(location);
                    if (vehicle != null && vehicle.getMinutesLeft() <= 0 && !vehicle.getIsPaying()) {
                        return vehicle;
                    }
                }
            }
        }
        return null;
    }

    private void vehiclesArriving(){
        int numberOfVehicles = getNumberOfVehicles(weekDayArrivals, weekendArrivals);
        addArrivingVehicles(numberOfVehicles, AD_HOC);
        numberOfVehicles = getNumberOfVehicles(weekDayPassArrivals, weekendPassArrivals);
        addArrivingVehicles(numberOfVehicles, PASS);
    }

    private void vehiclesEntering(VehicleQueue queue){
        int i=0;
        // Remove vehicle from the front of the queue and assign to a parking space.
        while (queue.vehiclesInQueue()>0 &&
                simController.getNumberOfOpenSpots()>0 &&
                i<enterSpeed) {
            Vehicle vehicle = queue.removeVehicle();
            Location freeLocation = getFirstFreeLocation();
            setVehicleAt(freeLocation, vehicle);
            i++;
        }
    }

    private void vehiclesReadyToLeave(){
        // Add leaving vehicles to the payment queue.
        Vehicle vehicle = getFirstLeavingVehicle();
        while (vehicle !=null) {
            if (vehicle.getHasToPay()){
                vehicle.setIsPaying(true);
                paymentVehicleQueue.addVehicle(vehicle);
            }
            else {
                vehiclesLeavesSpot(vehicle);
            }
            vehicle = getFirstLeavingVehicle();
        }
    }

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

    private void vehiclesLeaving(){
        // Let vehicles leave.
        int i=0;
        while (exitVehicleQueue.vehiclesInQueue()>0 && i < exitSpeed){
            exitVehicleQueue.removeVehicle();
            i++;
        }
    }

    private int getNumberOfVehicles(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of vehicles that arrive per hour.
        int averageNumberOfVehiclesPerHour = simController.getDay() < 5
                ? weekDay
                : weekend;

        // Calculate the number of vehicles that arrive this minute.
        double standardDeviation = averageNumberOfVehiclesPerHour * 0.3;
        double numberOfVehiclesPerHour = averageNumberOfVehiclesPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfVehiclesPerHour / 60);
    }

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

    private void vehiclesLeavesSpot(Vehicle vehicle){
        removeVehicleAt(vehicle.getLocation());
        exitVehicleQueue.addVehicle(vehicle);
    }

    protected void handleEntrance(){
        vehiclesArriving();
        vehiclesEntering(entrancePassQueue);
        vehiclesEntering(entranceVehicleQueue);
    }

    protected void handleExit(){
        vehiclesReadyToLeave();
        vehiclesPaying();
        vehiclesLeaving();
    }
}
