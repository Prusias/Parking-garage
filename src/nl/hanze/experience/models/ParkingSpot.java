package nl.hanze.experience.models;

//TODO: Implement listeners
/**
 * @author Mike van der Velde
 * @version 0.0.3
 * @since 0.0.3
 */
public class ParkingSpot extends Model {
    private int floor;
    private int row;
    private int place;

    private Vehicle vehicle;
    public enum PSType { NORMAL, ELECTRIC, BIKE }

    private PSType type;

    public ParkingSpot(int floor, int row, int place) {
        this.floor = floor;
        this.row = row;
        this.place = place;
        this.type = PSType.NORMAL;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    /**
     * Implement content equality.
     */
    public boolean equals(Object obj) {
        if(obj instanceof ParkingSpot) {
            ParkingSpot other = (ParkingSpot) obj;
            return floor == other.getFloor() && row == other.getRow() && place == other.getPlace();
        }
        else {
            return false;
        }
    }
    /**
     * Return a string of the form floor,row,place.
     * @return A string representation of the location.
     */
    public String toString() {
        return floor + "," + row + "," + place;
    }
    /**
     * Use the 10 bits for each of the floor, row and place
     * values. Except for very big car parks, this should give
     * a unique hash code for each (floor, row, place) tupel.
     * @return A hashcode for the location.
     */
    public int hashCode() {
        return (floor << 20) + (row << 10) + place;
    }

    /**
     * @return The floor.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * @return The row.
     */
    public int getRow() {
        return row;
    }

    /**
     * @return The place.
     */
    public int getPlace() {
        return place;
    }
}
