package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SimulationMenuModel;

import javax.swing.*;

import static nl.hanze.experience.parkinggarage.controllers.SimulationMenuController.EventId;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 *
 * TODO: Decide whether or not this object should be a JMenuBar instead of returning one.
 */
public class SimulationMenuView extends JMenuView {

    private JMenuItem startMenuItem;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;

    public SimulationMenuView() {
        this.setText("Simulation");

        startMenuItem = new JMenuItem("Start");
        startMenuItem.addActionListener(e -> notifyController(EventId.START));
        this.add(startMenuItem);

        pauseMenuItem = new JMenuItem("Pause");
        pauseMenuItem.addActionListener(e -> notifyController(EventId.PAUSE));
        pauseMenuItem.setEnabled(false);
        this.add(pauseMenuItem);


        resumeMenuItem = new JMenuItem("Resume");
        resumeMenuItem.addActionListener(e -> notifyController(EventId.RESUME));
        resumeMenuItem.setEnabled(false);
        this.add(resumeMenuItem);

        this.addSeparator();

        JMenuItem settingsMenuItem = new JMenuItem("Settings");
        settingsMenuItem.addActionListener(e -> notifyController(EventId.SETTINGS));
        this.add(settingsMenuItem);

        this.addSeparator();

        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(e -> notifyController(EventId.QUIT));
        this.add(quitMenuItem);
    }

    @Override
    public void update(Model model) {
        if (!(model instanceof SimulationMenuModel)) {
            throw new IllegalArgumentException("Wrong model given to view.update");
        }
        SimulationMenuModel simulationMenuModel = (SimulationMenuModel) model;
        if (simulationMenuModel.simulationHasStarted()) {
            startMenuItem.setEnabled(false);
            if (simulationMenuModel.simulationIsPaused()) {
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            } else {
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        } else {
            pauseMenuItem.setEnabled(false);
            resumeMenuItem.setEnabled(false);
        }


    }


}
