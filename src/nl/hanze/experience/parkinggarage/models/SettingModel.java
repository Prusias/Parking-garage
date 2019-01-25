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
                (int)simulation.getGarageModel().getGarageSetting("amountOfFloors")
        );
    }
    public void setAmountOfFloors(String value) {
        simulation.getGarageModel().setGarageSetting("amountOfFloors", Integer.valueOf(value));
    }

    public String getAmountOfRows() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("amountOfRows")
        );
    }
    public void setamountOfRows(String value) {
        simulation.getGarageModel().setGarageSetting("amountOfRows", Integer.valueOf(value));
    }

    public String getAmountOfSpots() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("amountOfSpots")
        );
    }
    public void setAmountOfSpots(String value) {
        simulation.getGarageModel().setGarageSetting("amountOfSpots", Integer.valueOf(value));
    }

    public String getPriceInEuro() {
        return Float.toString(
                (float)simulation.getGarageModel().getGarageSetting("priceInEuro")
        );
    }
    public void setPriceInEuro(String value) {
        simulation.getGarageModel().setGarageSetting("priceInEuro", Float.valueOf(value));
    }

    public boolean simulationHasStarted() {
        return simulation.hasStarted();
    }

}
