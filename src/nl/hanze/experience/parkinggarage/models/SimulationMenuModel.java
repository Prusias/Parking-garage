package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.parkinggarage.controllers.SettingController;
import nl.hanze.experience.parkinggarage.views.SettingView;
import nl.hanze.experience.simulation.Simulation;

/**
 * The model class for the simulator drop down menu where all the logical operation regarding that menu happen
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class SimulationMenuModel extends Model {
    private Simulation simulation;
    private SettingView settingView;

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * The option that starts the simulation
     */
    public void startSimulation() {
        if (simulation == null) {
            throw new IllegalStateException("Simulation has not been set");
        }
        simulation.start();
        notifyView();
    }

    /**
     * the option to pause the simulation
     */
    public void pauseSimulation() {
        if (simulation == null) {
            throw new IllegalStateException("Simulation has not been set");
        }
        simulation.pause();
        notifyView();
    }

    /**
     * the option to resume the simulation
     */
    public void resumeSimulation() {
        if (simulation == null) {
            throw new IllegalStateException("Simulation has not been set");
        }
        simulation.resume();
        notifyView();
    }

    /**
     * The settings option in the drop down menu
     */
    public void openSettings() {
        if (settingView == null) {
            SettingModel settingModel = new SettingModel();
            settingView = new SettingView();
            SettingController settingController = new SettingController(settingModel);
            settingModel.addView(settingView);
            settingModel.setSimulation(simulation);
            settingView.setController(settingController);
            settingView.update(settingModel);
        }
        settingView.setVisible(true);
        notifyView();
    }

    /**
     * Tests if the simulation is paused
     * @return Boolean with true if the simulation is paused
     */
    public boolean simulationIsPaused() {
        return simulation.isPaused();
    }

    /**
     * Tests if the simulation has already started
     * @return Boolean if simulation is running
     */
    public boolean simulationHasStarted() { return simulation.hasStarted(); }

    public void Do1Tick() {
        simulation.doTick();
    }
    public void Do100Ticks() {
        for (int i = 0; i < 100; i++) {
            simulation.doTick();
        }
    }

}
