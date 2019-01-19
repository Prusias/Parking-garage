package nl.hanze.experience.objects;

/**
 * @author Steven Woudstra
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class Garage {
    private ParkingSpot[][][] parkingspots; // floor - row - place

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private int price = 65;

    private VehicleQueue ticketQueue;
    private VehicleQueue subscriptionQueue;

    public Garage(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        //this.parkingspots = parkingspots;
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        createParkspots();
        createQueues();
    }

    private void createParkspots() {
        for (int f=0; f>=numberOfFloors; f++) {
            for (int r=0; r>=numberOfRows; r++) {
                for (int p=0; p>=numberOfPlaces;p++) {
                    parkingspots[f][r][p] = new ParkingSpot(f, r, p, ParkingSpot.Type.REGULAR);
                }
            }
        }
    }

    private void createQueues() {
        ticketQueue = new VehicleQueue();
        subscriptionQueue = new VehicleQueue();
    }

    public int getNumberOfOpenSpots() {
        return numberOfOpenSpots;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void addToTicketQueue(Vehicle vehicle) {
        ticketQueue.addVehicle(vehicle);
    }

    public void addToSubscriptionQueue(Vehicle vehicle) {
        subscriptionQueue.addVehicle(vehicle);
    }
}
