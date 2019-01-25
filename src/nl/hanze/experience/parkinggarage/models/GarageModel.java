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

    public GarageModel() {garage = new Garage();

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

    public int getNumberOfFreeSpots() {
        return garage.getNumberOfOpenSpots();
    }

    public ParkingSpot[][][] getParkingSpots() {
        return garage.parkingSpots;
    }
    public ParkingSpot getParkingSpot(int floor, int row, int spot) {
        return garage.getParkingspot(floor, row, spot);
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


}
