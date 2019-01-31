package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.parkinggarage.controllers.SettingController;
import nl.hanze.experience.parkinggarage.views.SettingView;
import nl.hanze.experience.simulation.Simulation;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class SimulationMenuModel extends Model {
    private Simulation simulation;
    private SettingView settingView;

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public void startSimulation() {
        if (simulation == null) {
            throw new IllegalStateException("Simulation has not been set");
        }
        simulation.start();
        notifyView();
    }
    public void pauseSimulation() {
        if (simulation == null) {
            throw new IllegalStateException("Simulation has not been set");
        }
        simulation.pause();
        notifyView();
    }
    public void resumeSimulation() {
        if (simulation == null) {
            throw new IllegalStateException("Simulation has not been set");
        }
        simulation.resume();
        notifyView();
    }
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

    public boolean simulationIsPaused() {
        return simulation.isPaused();
    }
    public boolean simulationHasStarted() { return simulation.hasStarted(); }


}
