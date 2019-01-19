package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SimulationMenuModel;

import javax.swing.*;

import static nl.hanze.experience.parkinggarage.controllers.SimulationMenuController.*;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 *
 * TODO: Decide whether or not this object should be a JMenuBar instead of returning one.
 */
public class SimulationMenuView extends JMenuView {

    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;

    public SimulationMenuView() {
        this.setText("Simulation");

        pauseMenuItem = new JMenuItem("Pause");
        pauseMenuItem.addActionListener(e -> notifyController(EVENT_ID_PAUSE));
        this.add(pauseMenuItem);

        resumeMenuItem = new JMenuItem("Resume");
        resumeMenuItem.addActionListener(e -> notifyController(EVENT_ID_RESUME));
        resumeMenuItem.setEnabled(false);
        this.add(resumeMenuItem);

        JMenuItem settingsMenuItem = new JMenuItem("Settings");
        settingsMenuItem.addActionListener(e -> notifyController(EVENT_ID_SETTINGS));
        this.add(settingsMenuItem);

        this.addSeparator();

        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(e -> notifyController(EVENT_ID_QUIT));
        this.add(quitMenuItem);
    }

    @Override
    public void update(Model model) {
        if (!(model instanceof SimulationMenuModel)) {
            throw new IllegalArgumentException("Wrong model given to view.update");
        }
        SimulationMenuModel simulationMenuModel = (SimulationMenuModel) model;

        if (simulationMenuModel.simulationIsPaused()) {
            pauseMenuItem.setEnabled(false);
            resumeMenuItem.setEnabled(true);
        } else {
            pauseMenuItem.setEnabled(true);
            resumeMenuItem.setEnabled(false);
        }

    }


}
