package nl.hanze.experience.simulation;

/**
 * Class used to contain modifiers for the simulation
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class Modifier {
    // Monday = 0, Sunday = 6
    private double[] weekdayModifier;
    // 00:00-01:00 = 0, 23:00-24:00 = 23
    private double[] hourModifier;
    private double baseVehicleModifier;
    private double ticketVehicleModifier;
    private double subscriptionVehicleModifier;
    private double reservationVehicleModifier;
    private double regularCarModifier;
    private double electricCarModifier;
    private double motorcycleModifier;
    private double parkingDurationModifier;
    private double reservationDurationModifier;
    private double ticketQueueSizeModifier;
    private double ticketQueueMaxSize;
    private double subscriptionQueueSizeModifier;
    private double subscriptionQueueMaxSize;
    private double neighbouringParkingSpotWeightModifier;

    /**
     * Initialize Modifier with default values
     */
    public Modifier() {
        weekdayModifier = new double[] { 0.8, 1, 1.1, 1, 1.5, 1.2, 0.5 };
        hourModifier = new double[] { 0.3, 0.2, 0.2, 0.2, 0.3, 0.5, 0.6, 0.7, 0.8, 0.9, 1, 1, 1, 1.1, 1.2, 1.1, 1.1, 1, 0.9, 0.8, 0.8, 0.6, 0.5, 0.3};
        baseVehicleModifier = 2;
        ticketVehicleModifier = 3;
        subscriptionVehicleModifier = 0.3;
        reservationVehicleModifier = 0.2;
        regularCarModifier = 1;
        electricCarModifier = 0.05;
        motorcycleModifier = 0.05;
        parkingDurationModifier = 1.5;
        reservationDurationModifier = 1.5;
        ticketQueueSizeModifier = 10;
        ticketQueueMaxSize = 100;
        subscriptionQueueSizeModifier = 5;
        subscriptionQueueMaxSize = 100;
        neighbouringParkingSpotWeightModifier = 1;
    }

    /**
     * Get the modifier for a day of the week
     * @param day day of the week
     * @return double modifier
     */
    public double getWeekdayModifier(int day) {
        return weekdayModifier[day];
    }
    /**
     * Set the modifier for a day of the week
     * @param day day of the week
     * @param modifier double modifier
     */
    public void setWeekdayModifier(int day, double modifier) {
        weekdayModifier[day] = modifier;
    }
    /**
     * Get the modifier for an hour on a day of the week
     * @param hour hour of the day
     * @return double modifier
     */
    public double getHourModifier(int hour) {
        return hourModifier[hour];
    }
    /**
     * Set the modifier for an hour on a day of the week
     * @param hour hour of the day
     * @param modifier double modifier
     */
    public void setHourModifier(int hour, double modifier) {
        hourModifier[hour] = modifier;
    }

    public double[] getWeekdayModifier() {
        return weekdayModifier;
    }
    public double[] getHourModifier() {
        return hourModifier;
    }

    public double getBaseVehicleModifier() {
        return baseVehicleModifier;
    }
    public void setBaseVehicleModifier(double baseVehicleModifier) {
        this.baseVehicleModifier = baseVehicleModifier;
    }

    public double getTicketVehicleModifier() {
        return ticketVehicleModifier;
    }
    public void setTicketVehicleModifier(double ticketVehicleModifier) {
        this.ticketVehicleModifier = ticketVehicleModifier;
    }

    public double getSubscriptionVehicleModifier() {
        return subscriptionVehicleModifier;
    }
    public void setSubscriptionVehicleModifier(double subscriptionVehicleModifier) {
        this.subscriptionVehicleModifier = subscriptionVehicleModifier;
    }

    public double getReservationVehicleModifier() {
        return reservationVehicleModifier;
    }
    public void setReservationVehicleModifier(double reservationVehicleModifier) {
        this.reservationVehicleModifier = reservationVehicleModifier;
    }

    public double getRegularCarModifier() {
        return regularCarModifier;
    }
    public void setRegularCarModifier(double regularCarModifier) {
        this.regularCarModifier = regularCarModifier;
    }

    public double getElectricCarModifier() {
        return electricCarModifier;
    }
    public void setElectricCarModifier(double electricCarModifier) {
        this.electricCarModifier = electricCarModifier;
    }

    public double getMotorcycleModifier() {
        return motorcycleModifier;
    }
    public void setMotorcycleModifier(double motorcycleModifier) {
        this.motorcycleModifier = motorcycleModifier;
    }

    public double getParkingDurationModifier() {
        return parkingDurationModifier;
    }
    public void setParkingDurationModifier(double parkingDurationModifier) {
        this.parkingDurationModifier = parkingDurationModifier;
    }

    public double getReservationDurationModifier() {
        return reservationDurationModifier;
    }
    public void setReservationDurationModifier(double reservationDurationModifier) {
        this.reservationDurationModifier = reservationDurationModifier;
    }

    public double getTicketQueueSizeModifier() {
        return ticketQueueSizeModifier;
    }
    public void setTicketQueueSizeModifier(double ticketQueueSizeModifier) {
        this.ticketQueueSizeModifier = ticketQueueSizeModifier;
    }

    public double getTicketQueueMaxSize() {
        return ticketQueueMaxSize;
    }
    public void setTicketQueueMaxSize(double ticketQueueMaxSize) {
        this.ticketQueueMaxSize = ticketQueueMaxSize;
    }

    public double getSubscriptionQueueSizeModifier() {
        return subscriptionQueueSizeModifier;
    }
    public void setSubscriptionQueueSizeModifier(double subscriptionQueueSizeModifier) {
        this.subscriptionQueueSizeModifier = subscriptionQueueSizeModifier;
    }

    public double getSubscriptionQueueMaxSize() {
        return subscriptionQueueMaxSize;
    }
    public void setSubscriptionQueueMaxSize(double subscriptionQueueMaxSize) {
        this.subscriptionQueueMaxSize = subscriptionQueueMaxSize;
    }

    public double getNeighbouringParkingSpotWeightModifier() {
        return neighbouringParkingSpotWeightModifier;
    }
    public void setNeighbouringParkingSpotWeightModifier(double neighbouringParkingSpotWeightModifier) {
        this.neighbouringParkingSpotWeightModifier = neighbouringParkingSpotWeightModifier;
    }
}
