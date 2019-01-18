package nl.hanze.experience.objects;

/**
 * @author
 * @version 0.0.4
 * @since 0.0.4
 */
public class ParkingSpot {
    public enum Type { REGULAR, ELECTRIC_CHARGER, MOTORCYCLE }

    private int floor;
    private int row;
    private int number;
    private Type type;
    private boolean subscriptionSpot;
    private boolean reserved;
    private Vehicle vehicle;

    // getters floor/type
    public int getFloor() {
        return floor;
    }
    public int getRow() {
        return row;
    }
    public int getNumber() {
        return number;
    }
    public Type getType() {
        return type;
    }

    public boolean isReserved() {
        return reserved;
    }
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isSubscriptionSpot() {
        return subscriptionSpot;
    }
    public void setSubscriptionSpot(boolean subscriptionSpot) {
        this.subscriptionSpot = subscriptionSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot(int floor, int row, int number, Type type) {
        this.floor = floor;
        this.row = row;
        this.number = number;
        this.type = type;
    }

    public void changeType(Type type) {
        this.type = type;
    }





}
