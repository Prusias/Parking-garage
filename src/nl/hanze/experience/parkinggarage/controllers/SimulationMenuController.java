package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SimulationMenuModel;


/**
 * The control class for the simulation drop down menu where are the central control happens
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public class SimulationMenuController extends Controller {
    public enum EventId { START, PAUSE, RESUME, SETTINGS, QUIT }

    /**
     * Make new controller for the simulator menu
     * @param model A model that to control
     */
    public SimulationMenuController(SimulationMenuModel model) {
        super(model);
    }

    /**
     * Decide which action to execute according to what came from the view
     * @param view View where event comes from
     * @param event_enum enum of event
     * @param <E>
     * @return Boolean with true if the action is one of the defined actions in the simulator
     */
    @Override
    protected <E extends Enum<E>> boolean event(View view, Enum<E> event_enum) {
        SimulationMenuModel simulationMenuModel = (SimulationMenuModel) model;
        EventId eventId = (EventId) event_enum;
        switch (eventId) {
            case START:
                simulationMenuModel.startSimulation();
                return true;
            case PAUSE:
                simulationMenuModel.pauseSimulation();
                return true;
            case RESUME:
                simulationMenuModel.resumeSimulation();
                return true;
            case SETTINGS:
                simulationMenuModel.openSettings();
                return true;
            case QUIT:
                System.exit(0);
        }
        return false;
    }
}
