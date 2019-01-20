package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.objects.*;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class GarageModel extends Model {
    private Garage garage;

    public void createGarage(int floorAmount, int rowPerFloor, int spotsPerRow) {
        this.garage = new Garage(floorAmount, rowPerFloor, spotsPerRow);
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

    public int getNumberOfFreeSpots() {
        return garage.getNumberOfOpenSpots();
    }

    public ParkingSpot[][][] getParkingSpots() {
        return garage.parkingspots;
    }
    public ParkingSpot getParkingSpot(int floor, int row, int spot) {
        return garage.getParkingspot(floor, row, spot);
    }
}
