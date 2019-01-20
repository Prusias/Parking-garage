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



}
