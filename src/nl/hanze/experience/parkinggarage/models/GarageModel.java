package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.objects.*;

import java.time.LocalDateTime;

/**
 * The garage model class where the logical operations regarding the garage
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public class GarageModel extends Model {
    private Garage garage;
    private VehicleQueue ticketQueue;
    private VehicleQueue subscriptionQueue;


    /**
     * Make a new garage model
     */
    public GarageModel() {
        garage = new Garage();
        ticketQueue = new VehicleQueue();
        subscriptionQueue = new VehicleQueue();

    }

    /**
     * Initialize The garage
     */
    public void initializeGarage() {
        garage.initializeGarage();
        notifyView();
    }

    /*
    public void vehicleToQueue(Vehicle.Type vehicleType, Vehicle.PaymentType paymentType, int duration) {
        switch (paymentType) {
            case TICKET:
                garage.addToTicketQueue(new Vehicle(vehicleType, paymentType, duration));
                break;
            case RESERVATION:
                garage.addToSubscriptionQueue(new Vehicle(vehicleType, paymentType, duration));
                break;
            case SUBSCRIPTION:
                garage.addToSubscriptionQueue(new Vehicle(vehicleType, paymentType, duration));
                break;
        }
    }
    */

    /**
     *Get parking spots
     * @return A three dimensional array of parking spots
     */
    public ParkingSpot[][][] getParkingSpots() {
        return garage.parkingSpots;
    }

    public ParkingSpot getParkingSpot(int floor, int row, int spot) {
        return garage.getParkingSpot(floor, row, spot);
    }

    public int getNumberOfFloors() {
        return garage.getNumberOfFloors();
    }

    public int getNumberOfRows() {
        return garage.getNumberOfRows();
    }

    public int getNumberOfSpots() {
        return garage.getNumberOfSpots();
    }

    public Object getGarageSetting(String key) {
        return garage.getGarageSetting(key);
    }

    public void setGarageSetting(String key, Object value) {
        garage.setGarageSetting(key, value);
    }

    /**
     * Increase the current time in the garage
     * @param minutes Amount of minutes to increase the time by
     */
    public void increaseTime(int minutes) {
        garage.increaseTime(minutes);
    }

    public LocalDateTime getLocalDateTime() {
        return garage.getLocalDateTime();
    }

    /**
     * Add a vehicle to the queue where vehicle have subscriptions
     * @param vehicle To add to the queue
     */
    public void addToSubscriptionQueue(Vehicle vehicle) {
        subscriptionQueue.addVehicle(vehicle);
    }

    /**
     * Add a vehicle to the queue where vehicle have tickets but no subscription
     * @param vehicle To add to the ticketed queue
     */
    public void addToTicketQueue(Vehicle vehicle) {
        ticketQueue.addVehicle(vehicle);
    }

    /**
     * Poll one vehicle from the top of the subscription queue
     * @return The vehicle of the top of subscription queue the queue
     */
    public Vehicle pollVehicleFromSubscriptionQueue() {
        return subscriptionQueue.poll();
    }

    /**
     * See which vehicle is on the top of the subscription queue without deleting it
     * @return vehicle that is on the top of the the subscription queue
     */
    public Vehicle peekVehicleFromSubscriptionQueue() {
        return subscriptionQueue.peek();
    }

    /**
     * Polls out a ticketed vehicle from the top of the ti
     * @return The vehicle on the top of the ticketed vehicles queue
     */
    public Vehicle pollVehicleFromTicketQueue() {
        return ticketQueue.poll();
    }

    /**
     * See which vehicle is on the top of the ticketed vehicles queue without deleting it
     * @return vehicle that is on the top of the ticketed vehicles queue
     */
    public Vehicle peekVehicleFromTicketQueue() {
        return ticketQueue.peek();
    }

    /**
     * Get the number of regular ticketed parking spots
     * @return Integer of the number of regular ticketed parking spots
     */
    public int getNumberOfFreeRegularTicketSpots() {
        return garage.getNumberOfFreeRegularTicketSpots();
    }

    public void setNumberOfFreeRegularTicketSpots(int numberOfFreeRegularTicketSpots) {
        garage.setNumberOfFreeRegularTicketSpots(numberOfFreeRegularTicketSpots);
    }

    public int getNumberOfFreeSubscriptionSpots() {
        return garage.getNOfFreeSubSpots();
    }

    public void setNumberOfFreeSubscriptionSpots(int numberOfFreeSubscriptionSpots) {
        garage.setNOfFreeSubSpots(numberOfFreeSubscriptionSpots);
    }

    public int getNumberOfFreeReservedSpots() {
        return garage.getnOfFreeResSpots();
    }

    public void setNumberOfFreeReservedSpots(int numberOfFreeReservedSpots) {
        garage.setnOfFreeResSpots(numberOfFreeReservedSpots);
    }

    public int getNumberOfFreeElectricSpots() {
        return garage.getNOfFreeElecSpots();
    }

    public void setNumberOfFreeElectricSpots(int numberOfFreeElectricSpots) {
        garage.setNOfFreeElecSpots(numberOfFreeElectricSpots);
    }

    public int getNumberOfFreeMotorcycleSpots() {
        return garage.getNOfFreeMotSpots();
    }

    public void setNumberOfFreeMotorcycleSpots(int numberOfFreeMotorcycleSpots) {
        garage.setNOfFreeMotSpots(numberOfFreeMotorcycleSpots);
    }

    public ParkingSpot getParkingSpotLeft(ParkingSpot parkingSpot) {
        if (parkingSpot.getSpot() == 0) {
            return null;
        }
        return garage.getParkingSpot(parkingSpot.getFloor(), parkingSpot.getRow(), parkingSpot.getSpot() - 1);
    }

    public ParkingSpot getParkingSpotRight(ParkingSpot parkingSpot) {
        if (parkingSpot.getSpot() == garage.getNumberOfSpots() - 1) {
            return null;
        }
        return garage.getParkingSpot(parkingSpot.getFloor(), parkingSpot.getRow(), parkingSpot.getSpot() + 1);
    }

    public int getSubscriptionQueueSize() {
        return subscriptionQueue.Size();
    }

    public int getTicketQueueSize() {
        return ticketQueue.Size();
    }

    /**
     * Determines how much should a vehicle pay for parking
     * @param vehicle To determine payment amount of
     */
    public void vehiclePay(Vehicle vehicle) {
        if (vehicle.getPaymentType() != Vehicle.PaymentType.SUBSCRIPTION) {
            int payingTime;
            payingTime = vehicle.getDuration() / 10;
            if (vehicle.getDuration() % 10 != 0) {
                payingTime = +1;
            }
            garage.addMoney(garage.getPrice() * payingTime);
        }
    }

    /**
     * Total amount of money made in Euros
     * @return Double variable of the amount of money made
     */


    public int getTotalSubVehicles() {
        return garage.getTotalSubVehicles();
    }
    public void setTotalSubVehicles(int totalSubVehicles) {
        garage.setTotalSubVehicles(totalSubVehicles);
    }

    public int getTotalResVehicles() {
        return garage.getTotalResVehicles();
    }
    public void setTotalResVehicles(int totalResVehicles) {
        garage.setTotalResVehicles(totalResVehicles);
    }

    public int getTotalTicVehicles() {
        return garage.getTotalTicVehicles();
    }
    public void setTotalTicVehicles(int totalTicVehicles) {
        garage.setTotalTicVehicles(totalTicVehicles);
    }

    public int getAmountOfPayingVehicles() {
        return garage.getTotalResVehicles() + garage.getTotalTicVehicles();
    }

    /**
     * Calculate the prise of a parking spot in cents
     * @param priceInEuro The price of a parking spot in euros
     * @return
     */
    public int calculatePriceInCents(double priceInEuro) {
        return (int)(priceInEuro * 100);

    }

    public double calculatePriceInEuro(int cents) {
        return (double) cents / 100D;
    }

    public double moneyMadeInEuro() {
        return calculatePriceInEuro(garage.getMoneyMade());
    }

    public double getPriceInEuro() {
        return calculatePriceInEuro(garage.getPrice());
    }
}
