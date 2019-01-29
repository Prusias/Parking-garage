package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.objects.*;

import java.time.LocalDateTime;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class GarageModel extends Model {
    private Garage garage;
    private VehicleQueue ticketQueue;
    private VehicleQueue subscriptionQueue;



    public GarageModel() {
        garage = new Garage();
        ticketQueue = new VehicleQueue();
        subscriptionQueue = new VehicleQueue();

    }

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

    public void increaseTime(int minutes) {
        garage.increaseTime(minutes);
    }

    public LocalDateTime getLocalDateTime() {
        return garage.getLocalDateTime();
    }

    public void addToSubscriptionQueue(Vehicle vehicle) {
        subscriptionQueue.addVehicle(vehicle);
    }

    public void addToTicketQueue(Vehicle vehicle) {
        ticketQueue.addVehicle(vehicle);
    }

    public Vehicle pollVehicleFromSubscriptionQueue() {
        return subscriptionQueue.poll();
    }

    public Vehicle peekVehicleFromSubscriptionQueue() {
        return subscriptionQueue.peek();
    }

    public Vehicle pollVehicleFromTicketQueue() {
        return ticketQueue.poll();
    }

    public Vehicle peekVehicleFromTicketQueue() {
        return ticketQueue.peek();
    }

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

    public double moneyMadeInEuro() {
        return ((double)garage.getMoneyMade())/10;
    }

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
}
