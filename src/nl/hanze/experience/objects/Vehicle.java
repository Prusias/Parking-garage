package nl.hanze.experience.objects;

import java.net.PasswordAuthentication;

/**
 * The class for vehicles in the simulation
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class Vehicle {
    public enum Type { CAR, ELECTRIC_CAR, MOTORCYCLE }
    public enum PaymentType { TICKET, RESERVATION, SUBSCRIPTION }

    private Type type;
    private PaymentType paymentType;
    private int duration; //minutes
    private int timeOfLeaving; // tick
    private ParkingSpot parkingSpot;

    public Type getType() {
        return type;
    }
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public int getTimeOfLeaving() {
        return timeOfLeaving;
    }
    public void setTimeOfLeaving(int timeOfLeaving) {
        this.timeOfLeaving = timeOfLeaving;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    /**
     * Make new vehicle
     * @param type What type of a vehicle it is
     * @param paymentType What kind of a payment (subscription, ticket or reservation)
     * @param duration How long is the vehicle going to stay
     */
    public Vehicle(Type type, PaymentType paymentType, int duration) {
        this.type = type;
        this.paymentType = paymentType;
        this.duration = duration;
    }


}
