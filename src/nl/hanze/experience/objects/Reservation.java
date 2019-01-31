package nl.hanze.experience.objects;

/**
 * The reservation class where vehicle
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public class Reservation {
    private Vehicle vehicle;
    private int timeOfArrival;
    private int duration; // in minutes
    private boolean noshow;

    public Vehicle getVehicle() {
        return vehicle;
    }
    public int getTimeOfArrival() {
        return timeOfArrival;
    }
    // TODO: This isn't needed as the vehicle also keeps track?
    public int getDuration() {
        return duration;
    }
    public boolean isNoshow() {
        return noshow;
    }
    public void setTimeOfArrival(int timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    /**
     * Make new reservation
     * @param vehicle The vehicle that will have a reservation
     * @param timeOfArrival Time of arrival of the vehicle
     * @param duration How long is the vehicle going to stay
     */
    public Reservation(Vehicle vehicle, int timeOfArrival, int duration, boolean noshow) {
        this.vehicle = vehicle;
        this.timeOfArrival = timeOfArrival;
        this.duration = duration;
        this.noshow = noshow;
    }

}
