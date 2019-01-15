package nl.hanze.experience.objects;

public class Vehicle {
    public enum Type { CAR, ELECTRIC_CAR, MOTORCYCLE }
    public enum PaymentType { TICKET, RESERVATION, SUBSCRIPTION }

    private Type type;
    private PaymentType paymentType;
    private int timeLeft;

    public Type getType() {
        return type;
    }
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public int getTimeLeft() {
        return timeLeft;
    }
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Vehicle(Type type, PaymentType paymentType) {
        this.type = type;
        this.paymentType = paymentType;
    }


}
