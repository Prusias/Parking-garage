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

    public static final int EVENT_ID_PAUSE = 1;
    public static final int EVENT_ID_RESUME = 2;
    public static final int EVENT_ID_SETTINGS = 3;
    public static final int EVENT_ID_QUIT = 4;

    public SimulationMenuController(SimulationMenuModel model) {
        super(model);
    }

    @Override
    protected boolean event(View view, int event_id) {
        SimulationMenuModel simulationMenuModel = (SimulationMenuModel) model;
        switch (event_id) {
            case EVENT_ID_PAUSE:
                simulationMenuModel.pauseSimulation();
                return true;
            case EVENT_ID_RESUME:
                simulationMenuModel.resumeSimulation();
                return true;
            case EVENT_ID_SETTINGS:
                JFrame frame = new JFrame();
                frame.setTitle("Test");
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setSize(200, 200);
                frame.setVisible(true);
                return true;
            case EVENT_ID_QUIT:
                System.exit(0);
        }
        return false;
    }
}
