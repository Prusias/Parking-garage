package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
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
