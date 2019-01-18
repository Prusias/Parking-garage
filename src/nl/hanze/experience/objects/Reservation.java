package nl.hanze.experience.objects;

/**
 * @author
 * @version 0.0.4
 * @since 0.0.4
 */
public class Reservation {
    private Vehicle vehicle;
    private int timeOfArrival;
    private int duration; // in minutes

    public Vehicle getVehicle() {
        return vehicle;
    }
    public int getTimeOfArrival() {
        return timeOfArrival;
    }
    public int getDuration() {
        return duration;
    }

    public Reservation(Vehicle vehicle, int timeOfArrival, int duration) {
        this.vehicle = vehicle;
        this.timeOfArrival = timeOfArrival;
        this.duration = duration;
    }

}
