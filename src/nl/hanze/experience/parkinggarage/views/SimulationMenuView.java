package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SimulationMenuModel;

import javax.swing.*;

import java.awt.*;

import static nl.hanze.experience.parkinggarage.controllers.SimulationMenuController.EventId;

/**
 * The view for the simulator drop menu where the graphical operations regarding the drop down menu happens
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 *
 * TODO: Decide whether or not this object should be a JMenuBar instead of returning one.
 */
public class SimulationMenuView extends JMenuView {

    private JMenuItem startMenuItem;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem do1Tick;
    private JMenuItem do100Ticks;
    private JMenuItem toggleViewUpdates;

    /**
     * Make new drop menu in the simlation
     */
    public SimulationMenuView() {
        this.setBackground(Color.WHITE);
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

        do1Tick = new JMenuItem("Run 1 ticks");
        do1Tick.addActionListener(e -> notifyController(EventId.DO1));
        do1Tick.setEnabled(false);
        this.add(do1Tick);

        do100Ticks = new JMenuItem("Run 100 ticks");
        do100Ticks.addActionListener(e -> notifyController(EventId.DO100));
        do100Ticks.setEnabled(false);
        this.add(do100Ticks);

        this.addSeparator();

        toggleViewUpdates = new JMenuItem("Toggle view updates");
        toggleViewUpdates.addActionListener(e -> notifyController(EventId.TOGGLEUPDATES));
        toggleViewUpdates.setEnabled(true);
        this.add(toggleViewUpdates);

        this.addSeparator();

        JMenuItem settingsMenuItem = new JMenuItem("Settings");
        settingsMenuItem.addActionListener(e -> notifyController(EventId.SETTINGS));
        this.add(settingsMenuItem);

        this.addSeparator();

        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(e -> notifyController(EventId.QUIT));
        this.add(quitMenuItem);
    }

    /**
     * updates the information in the menu
     * @param model Model that contains the information
     */
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
                do1Tick.setEnabled(true);
                do100Ticks.setEnabled(true);
            } else {
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
                do1Tick.setEnabled(false);
                do100Ticks.setEnabled(false);
            }
        } else {
            pauseMenuItem.setEnabled(false);
            resumeMenuItem.setEnabled(false);
            do1Tick.setEnabled(false);
            do100Ticks.setEnabled(false);
        }


    }


}
