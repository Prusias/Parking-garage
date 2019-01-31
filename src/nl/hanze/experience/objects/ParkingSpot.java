package nl.hanze.experience.objects;

import nl.hanze.experience.objects.Vehicle.*;

/**
 * The class for the parking spots
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class ParkingSpot {
    private int floor;
    private int row;
    private int spot;
    private Type type;
    private PaymentType paymentType;
    private Vehicle vehicle;
    private double weight;

    // getters floor/type
    public int getFloor() {
        return floor;
    }
    public int getRow() {
        return row;
    }
    public int getSpot() {
        return spot;
    }

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getWeight(){
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Make new parking spot
     * @param floor Which floor is the parking sport at
     * @param row Which row is the parking spot in
     * @param spot The spot's number
     * @param type What type of spot it is (cars, electric car or motorcycle)
     * @param paymentType The payment type for this spot (is it a reservation, subscription or ticket spot)
     * @param weight How attractive the parking spot is
     */
    public ParkingSpot(int floor, int row, int spot, Type type, PaymentType paymentType, double weight) {
        this.floor = floor;
        this.row = row;
        this.spot = spot;
        this.type = type;
        this.paymentType = paymentType;
        this.weight = weight;
    }

    public void changeType(Type type) {
        this.type = type;
    }

//    @Override
//    public boolean equals(Object o){
//        if (o == this) {
//            return true;
//        }
//        if (!(o instanceof ParkingSpot)) {
//            return false;
//        }
//        ParkingSpot p = (ParkingSpot)o;
//        if (p.floor == floor && p.row == row && p.spot == spot) {
//            return true;
//        }
//        return false;
//    }
//    public int hashCode() {
//        int result = 17 + floor + 19 * row + 23 * spot;
//        return result;
//    }



}
