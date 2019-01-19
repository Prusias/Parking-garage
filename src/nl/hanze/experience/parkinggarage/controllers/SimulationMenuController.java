package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SimulationMenuModel;

import javax.swing.*;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class SimulationMenuController extends Controller {
    public enum EventId { START, PAUSE, RESUME, SETTINGS, QUIT }

    public SimulationMenuController(SimulationMenuModel model) {
        super(model);
    }

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
                JFrame frame = new JFrame();
                frame.setTitle("Test");
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setSize(200, 200);
                frame.setVisible(true);
                return true;
            case QUIT:
                System.exit(0);
        }
        return false;
    }
}
