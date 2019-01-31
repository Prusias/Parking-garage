package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.simulation.Simulation;

import java.util.Arrays;

/**
 * The model class for the settings where all the logical operations for the settings are handled
 * @author Mike van der Velde and Zein Bseis
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
        return Double.toString(
                (double)simulation.getGarageModel().getGarageSetting("priceInEuro")
        );
    }
    public void setPriceInEuro(String value) {
        simulation.getGarageModel().setGarageSetting("priceInEuro", Double.valueOf(value));
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

    public String getBaseVehicleModifier(){
        return Double.toString(
                simulation.getModifier().getBaseVehicleModifier());
    }

    public void setBaseVehicleModifier(String modifier){
        simulation.getModifier().setBaseVehicleModifier(Double.valueOf(modifier));
    }

    public String getTicketVehicleModifier(){
        return Double.toString(
                simulation.getModifier().getTicketVehicleModifier());
    }

    public void setTicketVehicleModifier(String modifier){
        simulation.getModifier().setTicketVehicleModifier(Double.valueOf(modifier));
    }
    /**
     * Tests if the if the simulation has started
     * @return Boolean that is true if simulation is running
     */
    public boolean simulationHasStarted() {
        return simulation.hasStarted();
    }
}
