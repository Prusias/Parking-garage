package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;


public class TestModel extends Model {

    private int amountOfCars;

    public int getAmountOfCars() {
        return amountOfCars;
    }

    public void setAmountOfCars(int amountOfCars) {
        this.amountOfCars = amountOfCars;
        notifyView();
    }
}
