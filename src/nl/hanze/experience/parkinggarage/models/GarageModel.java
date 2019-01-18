package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.objects.*;

public class GarageModel extends Model {
    private Garage garage;

    public void createGarage(int floorAmount, int rowPerFloor, int spotsPerRow) {
        this.garage = new Garage(floorAmount, rowPerFloor, spotsPerRow);
    }

    public void vehicleToQueue(Vehicle.Type type, Vehicle.PaymentType paymentType, int duration) {
        switch (paymentType) {
            case TICKET:
                garage.addToTicketQueue(new Vehicle(type, paymentType, duration));
                break;
            case RESERVATION:
                garage.addToTicketQueue(new Vehicle(type, paymentType, duration));
                break;
            case SUBSCRIPTION:
                garage.addToSubscriptionQueue(new Vehicle(type, paymentType, duration));
                break;
        }

    }
}
