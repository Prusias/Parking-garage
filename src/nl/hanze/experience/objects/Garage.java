package nl.hanze.experience.objects;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author Steven Woudstra
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class Garage {
    public ParkingSpot[][][] parkingSpots; // floor - row - place
    private HashMap<String, Object> garageSettings = new HashMap<>();
    private LocalDateTime localDateTime;

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfSpots;
    private int numberOfOpenSpots;
    private int price = 65;

    private int ticketQueue;
    private int subscriptionQueue;

    //constructors en functies voor constructors start
    public Garage() {
        ticketQueue = 0;
        subscriptionQueue = 0;
        localDateTime = LocalDateTime.of(1, 1,1, 3, 0, 0);
    }
    public void initializeGarage() {
        this.numberOfFloors = (int)garageSettings.get("amountOfFloors");
        this.numberOfRows = (int)garageSettings.get("amountOfRows");
        this.numberOfSpots = (int)garageSettings.get("amountOfSpots");
        this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfSpots;
        parkingSpots = new ParkingSpot[numberOfFloors][numberOfRows][numberOfSpots];
        createParkspots();
    }

    private void createParkspots() {
        int weight = 1;
        for (int f=0; f<numberOfFloors; f++) {
            if(f>0) { weight += 2; }
            for (int r=0; r<numberOfRows; r++) {
                for (int p = 0; p< numberOfSpots; p++) {
                    parkingSpots[f][r][p] = new ParkingSpot(f, r, p, ParkingSpot.Type.REGULAR, weight);
                }
            }
        }
    }

    //einde constructors en functies


    public ParkingSpot getParkingspot(int floor, int row, int place) {
        return parkingSpots[floor][row][place];
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

    public void addToTicketQueue() {
        ticketQueue++;
    }

    public void addToSubscriptionQueue() {
        subscriptionQueue++;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
    public int getNumberOfRows() {
        return numberOfRows;
    }
    public int getNumberOfSpots() {
        return numberOfSpots;
    }

    public void increaseTime(int minutes ) {
        localDateTime = localDateTime.plusMinutes(minutes);
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Object getGarageSetting(String key) {
        if (!garageSettings.containsKey(key)) {
            throw new IllegalStateException("Garage - garageSettings key does not exist");
        }
        return garageSettings.get(key);
    }

    public void setGarageSetting(String key, Object value) {
        garageSettings.put(key, value);
    }

}
