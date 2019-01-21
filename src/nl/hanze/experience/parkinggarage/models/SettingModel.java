package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.simulation.Simulation;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class SettingModel extends Model {
    private Simulation simulation;

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public String getAmountOfFloors() {
        return Integer.toString(
                (int)simulation.getGarageSetting("amountOfFloors")
        );
    }
    public void setAmountOfFloors(String value) {
        simulation.setGarageSetting("amountOfFloors", Integer.valueOf(value));
    }

    public String getAmountOfRows() {
        return Integer.toString(
                (int)simulation.getGarageSetting("amountOfRows")
        );
    }
    public void setamountOfRows(String value) {
        simulation.setGarageSetting("amountOfRows", Integer.valueOf(value));
    }

    public String getAmountOfSpots() {
        return Integer.toString(
                (int)simulation.getGarageSetting("amountOfSpots")
        );
    }
    public void setAmountOfSpots(String value) {
        simulation.setGarageSetting("amountOfSpots", Integer.valueOf(value));
    }

    public String getPriceInEuro() {
        return Float.toString(
                (float)simulation.getGarageSetting("priceInEuro")
        );
    }
    public void setPriceInEuro(String value) {
        simulation.setGarageSetting("priceInEuro", Float.valueOf(value));
    }

    public boolean simulationHasStarted() {
        return simulation.hasStarted();
    }

}
