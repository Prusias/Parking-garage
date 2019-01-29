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
    private int numberOfFreeRegularSpots;
    private int nOfFreeSubSpots;
    private int nOfFreeResSpots;
    private int nOfFreeElecSpots;
    private int nOfFreeMotSpots;
    private int price = 0;
    private int moneyMade = 0; // in cents

    //constructors en functies voor constructors start
    public Garage() {
        localDateTime = LocalDateTime.of(1, 1,1, 3, 0, 0);
    }
    public void initializeGarage() {
        this.numberOfFloors = (int)garageSettings.get("amountOfFloors");
        this.numberOfRows = (int)garageSettings.get("amountOfRows");
        this.numberOfSpots = (int)garageSettings.get("amountOfSpots");
        int totalSpots = numberOfFloors * numberOfRows * numberOfSpots;
        nOfFreeSubSpots = (int)getGarageSetting("subscriptionSpots");
        nOfFreeResSpots = (int)getGarageSetting("reservedSpots");
        nOfFreeElecSpots = (int)getGarageSetting("electricSpots");
        nOfFreeMotSpots = (int)getGarageSetting("motorcycleSpots");
        //this.price = caluclatePriceInCents((double)getGarageSetting("priceInEuro"));
        int neededSpots = nOfFreeSubSpots + nOfFreeResSpots + nOfFreeElecSpots + nOfFreeMotSpots;
        if (neededSpots > totalSpots){
            throw new IllegalStateException("Garage - Not enough parking spots available");
        }
        numberOfFreeRegularSpots = totalSpots - neededSpots;

        parkingSpots = new ParkingSpot[numberOfFloors][numberOfRows][numberOfSpots];
        createParkingSpots();
    }

    private void createParkingSpots() {
        double weight = 0;
        int count = 0;
        for (int f=0; f<numberOfFloors; f++) {
            for (int r=0; r<numberOfRows; r++) {
                for (int p = 0; p< numberOfSpots; p++) {
                    count++;
                    //TODO: Add Modifiers
                    weight = f * 1 + r * 0.7 + p * 0.2;
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
                    } else if (count <= nOfFreeSubSpots + nOfFreeElecSpots + nOfFreeMotSpots + nOfFreeResSpots) {
                        type = Vehicle.Type.CAR;
                        paymentType = Vehicle.PaymentType.RESERVATION;
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

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
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


    public int getNumberOfFreeRegularSpots() {
        return numberOfFreeRegularSpots;
    }
    public void setNumberOfFreeRegularSpots(int numberOfFreeRegularSpots) {
        this.numberOfFreeRegularSpots = numberOfFreeRegularSpots;
    }

    public int getNOfFreeSubSpots() {
        return nOfFreeSubSpots;
    }
    public void setNOfFreeSubSpots(int nOfFreeSubSpots) {
        this.nOfFreeSubSpots = nOfFreeSubSpots;
    }

    public int getnOfFreeResSpots() {
        return nOfFreeResSpots;
    }
    public void setnOfFreeResSpots(int nOfFreeResSpots) {
        this.nOfFreeResSpots = nOfFreeResSpots;
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

    public void increaseTime(int minutes ) {
        localDateTime = localDateTime.plusMinutes(minutes);
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void addMoney(int amount) {
        moneyMade += amount;
    }

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

    public int caluclatePriceInCents(Double priceInEuro) {
        return  (int)(priceInEuro * 10);
    }

