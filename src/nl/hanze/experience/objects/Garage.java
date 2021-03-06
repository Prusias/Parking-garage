package nl.hanze.experience.objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
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
    private int numberOfFreeRegularTicketSpots;
    private int nOfFreeSubSpots;
    private int nOfFreeElecSpots;
    private int nOfFreeMotSpots;
    private int totalSubVehicles;
    private int totalResVehicles;
    private int totalTicVehicles;
    private int price;
    private int moneyMade = 0; // in cents
    private int moneyMadeUntillYeasterday; //in cents
    private List<Integer> dailyMoneyLog = new ArrayList<>();

    /**
     * Make new garage
     */
    //constructors en functies voor constructors start
    public Garage() {
        localDateTime = LocalDateTime.of(2019, 1, 1, 3, 0, 0);
        totalSubVehicles = 0;
        totalResVehicles = 0;
        totalTicVehicles = 0;
    }

    /**
     * Initialize the garage with all the needed values
     */
    public void initializeGarage() {
        this.numberOfFloors = (int) getGarageSetting("amountOfFloors");
        this.numberOfRows = (int) getGarageSetting("amountOfRows");
        this.numberOfSpots = (int) getGarageSetting("amountOfSpots");
        int totalSpots = numberOfFloors * numberOfRows * numberOfSpots;
        nOfFreeSubSpots = (int) getGarageSetting("subscriptionSpots");
        nOfFreeElecSpots = (int) getGarageSetting("electricSpots");
        nOfFreeMotSpots = (int) getGarageSetting("motorcycleSpots");
        this.price = (int) ((Double)getGarageSetting("priceInEuro") * 100);
        int neededSpots = nOfFreeSubSpots + nOfFreeElecSpots + nOfFreeMotSpots;
        if (neededSpots > totalSpots) {
            throw new IllegalStateException("Garage - Not enough parking spots available");
        }
        numberOfFreeRegularTicketSpots = totalSpots - neededSpots;

        parkingSpots = new ParkingSpot[numberOfFloors][numberOfRows][numberOfSpots];
        createParkingSpots();
    }

    /**
     * Create parking spots in the garage
     */
    private void createParkingSpots() {
        double weight = 0;
        int count = 0;
        double floorWeight = (double)getGarageSetting("floorWeight");
        double rowWeight = (double)getGarageSetting("rowWeight");
        double spotWeight = (double)getGarageSetting("spotWeight");
        for (int f = 0; f < numberOfFloors; f++) {
            for (int r = 0; r < numberOfRows; r++) {
                for (int p = 0; p < numberOfSpots; p++) {
                    count++;
                    //TODO: Add Modifiers
                    weight = f * floorWeight + r * rowWeight + p * spotWeight;
                    Vehicle.Type type;
                    Vehicle.PaymentType paymentType;
                    if (count <= nOfFreeSubSpots) {
                        type = Vehicle.Type.CAR;
                        paymentType = Vehicle.PaymentType.SUBSCRIPTION;
                    } else if (count <= nOfFreeSubSpots + nOfFreeElecSpots) {
                        type = Vehicle.Type.ELECTRIC_CAR;
                        paymentType = Vehicle.PaymentType.TICKET;
                    } else if (count <= nOfFreeSubSpots + nOfFreeElecSpots + nOfFreeMotSpots) {
                        type = Vehicle.Type.MOTORCYCLE;
                        paymentType = Vehicle.PaymentType.TICKET;
                    } else {
                        type = Vehicle.Type.CAR;
                        paymentType = Vehicle.PaymentType.TICKET;
                    }
                    parkingSpots[f][r][p] = new ParkingSpot(f, r, p, type, paymentType, weight);
                }
            }
        }
    }

    //einde constructors en functies


    public ParkingSpot getParkingSpot(int floor, int row, int place) {
        return parkingSpots[floor][row][place];
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

//    public void addToTicketQueue() {
//        ticketQueue++;
//    }
//
//    public void addToSubscriptionQueue() {
//        subscriptionQueue++;
//    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfSpots() {
        return numberOfSpots;
    }


    public int getNumberOfFreeRegularTicketSpots() {
        return numberOfFreeRegularTicketSpots;
    }

    public void setNumberOfFreeRegularTicketSpots(int numberOfFreeRegularTicketSpots) {
        this.numberOfFreeRegularTicketSpots = numberOfFreeRegularTicketSpots;
    }

    public int getNOfFreeSubSpots() {
        return nOfFreeSubSpots;
    }

    public void setNOfFreeSubSpots(int nOfFreeSubSpots) {
        this.nOfFreeSubSpots = nOfFreeSubSpots;
    }

    public int getNOfFreeElecSpots() {
        return nOfFreeElecSpots;
    }

    public void setNOfFreeElecSpots(int nOfFreeElecSpots) {
        this.nOfFreeElecSpots = nOfFreeElecSpots;
    }

    public int getNOfFreeMotSpots() {
        return nOfFreeMotSpots;
    }

    public void setNOfFreeMotSpots(int nOfFreeMotSpots) {
        this.nOfFreeMotSpots = nOfFreeMotSpots;
    }

    /**
     * Increase the time in the garage
     * @param minutes Amount of minutes to increase the time with
     */
    public void increaseTime(int minutes) {
        localDateTime = localDateTime.plusMinutes(minutes);
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * Add money to the amount of money made
     * @param amount
     */
    public void addMoney(int amount) {
        moneyMade += amount;
    }

    /**
     * Gives te omount of moneymade in cents
     * @return money made in cents
     */
    public int getMoneyMade() {
        return moneyMade;
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

    public List getDailyMoneyLog() {
        return dailyMoneyLog;
    }

    public int getTotalSubVehicles() {
        return totalSubVehicles;
    }
    public void setTotalSubVehicles(int totalSubVehicles) {
        this.totalSubVehicles = totalSubVehicles;
    }

    public int getTotalResVehicles() {
        return totalResVehicles;
    }
    public void setTotalResVehicles(int totalResVehicles) {
        this.totalResVehicles = totalResVehicles;
    }

    public int getTotalTicVehicles() {
        return totalTicVehicles;
    }
    public void setTotalTicVehicles(int totalTicVehicles) {
        this.totalTicVehicles = totalTicVehicles;
    }

    public int getMoneyMadeUntillYeasterday() {
        return moneyMadeUntillYeasterday;
    }
    public void setMoneyMadeUntillYeasterday(int money) {
        moneyMadeUntillYeasterday = money;
    }
}

