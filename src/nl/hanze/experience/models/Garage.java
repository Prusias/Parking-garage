package nl.hanze.experience.models;

//TODO: Implement listeners
public class Garage extends Model {
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;

    //TODO: Each ParkingSpot should be aware of which vehicle is in it.
    private Vehicle[][][] vehicles;

    public Garage(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        super();
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = numberOfFloors*numberOfRows*numberOfPlaces;

        vehicles = new Vehicle[numberOfFloors][numberOfRows][numberOfPlaces];
    }

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

    public Vehicle[][][] getVehicles() {
        return vehicles;
    }

    public Vehicle getVehicleAt(ParkingSpot parkingSpot) {
        if (!parkingSpotIsEmpty(parkingSpot)) {
            return null;
        }
        return vehicles[parkingSpot.getFloor()][parkingSpot.getRow()][parkingSpot.getPlace()];
    }
    public boolean setVehicleAt(ParkingSpot parkingSpot, Vehicle vehicle) {
        if (!parkingSpotIsEmpty(parkingSpot)) {
            return false;
        }
        Vehicle oldVehicle = getVehicleAt(parkingSpot);
        if (oldVehicle == null) {
            vehicles[parkingSpot.getFloor()][parkingSpot.getRow()][parkingSpot.getPlace()] = vehicle;
            vehicle.setParkingSpot(parkingSpot);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }
    public Vehicle removeVehicleAt(ParkingSpot parkingSpot) {
        if (!parkingSpotIsEmpty(parkingSpot)) {
            return null;
        }
        Vehicle vehicle = getVehicleAt(parkingSpot);
        if (vehicle == null) {
            return null;
        }
        vehicles[parkingSpot.getFloor()][parkingSpot.getRow()][parkingSpot.getPlace()] = null;
        vehicle.setParkingSpot(null);
        numberOfOpenSpots++;
        return vehicle;
    }

    //TODO: Each ParkingSpot should be aware of which vehicle is in it. Which would mean this function can be moved to the class
    private boolean parkingSpotIsEmpty(ParkingSpot location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
    //TODO: Each ParkingSpot should be aware of which vehicle is in it. Which would mean this function can be moved to the class
    public ParkingSpot getFirstFreeParkingSpot() {
        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                    ParkingSpot parkingSpot = new ParkingSpot(floor, row, place);
                    if (getVehicleAt(parkingSpot) == null) {
                        return parkingSpot;
                    }
                }
            }
        }
        return null;
    }
    //TODO: Each ParkingSpot should be aware of which vehicle is in it. Which would mean this function can be moved to the class
    public Vehicle getFirstLeavingVehicle() {
        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                    ParkingSpot parkingSpot = new ParkingSpot(floor, row, place);
                    Vehicle vehicle = getVehicleAt(parkingSpot);
                    if (vehicle != null && vehicle.getMinutesLeft() <= 0 && !vehicle.getIsPaying()) {
                        return vehicle;
                    }
                }
            }
        }
        return null;
    }

//    public void incrementNumberOfOpenSpots() {
//        numberOfOpenSpots++;
//    }
//    public void decrementNumberOfOpenSpots() {
//        numberOfOpenSpots--;
//    }


}
