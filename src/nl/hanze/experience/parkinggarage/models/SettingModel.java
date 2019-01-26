package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.simulation.Simulation;

import java.util.Arrays;

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
    public void setAmountOfRows(String value) {
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

    public String getSeed() {
        return Long.toString(simulation.getSeed());
    }
    public void setSeed(String value) {
        simulation.setSeed(Long.valueOf(value));
    }

    public String getWeekdayModifier(int day) {
        return Double.toString(simulation.getModifier().getWeekdayModifier(day));
    }
    public void setWeekdayModifier(int day, double modifier) {
        simulation.getModifier().setWeekdayModifier(day, modifier);
    }

    public String getHourModifier(int hour) {
        return Double.toString(simulation.getModifier().getHourModifier(hour));
    }
    public void setHourModifier(int hour, double modifier) {
        simulation.getModifier().setHourModifier(hour, modifier);
    }

    public boolean simulationHasStarted() {
        return simulation.hasStarted();
    }
}
