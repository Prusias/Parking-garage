package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.MenuModel;

import javax.swing.*;
import java.awt.*;

import static nl.hanze.experience.parkinggarage.controllers.MenuController.*;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 *
 * TODO: Decide whether or not this object should be a JMenuBar instead of returning one.
 */
public class MenuView extends View {

    private JMenuBar menuBar;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;

    public MenuView() {
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Simulation");
        menuBar.add(menu);

        pauseMenuItem = new JMenuItem("Pause");
        pauseMenuItem.addActionListener(e -> notifyController(EVENT_ID_PAUSE));
        menu.add(pauseMenuItem);

        resumeMenuItem = new JMenuItem("Resume");
        resumeMenuItem.addActionListener(e -> notifyController(EVENT_ID_RESUME));
        resumeMenuItem.setEnabled(false);
        menu.add(resumeMenuItem);

        JMenuItem settingsMenuItem = new JMenuItem("Settings");
        settingsMenuItem.addActionListener(e -> notifyController(EVENT_ID_SETTINGS));
        menu.add(settingsMenuItem);

        //this.add(menuBar);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    @Override
    protected void update(Model model) {
        if (!(model instanceof MenuModel)) {
            throw new IllegalArgumentException("Wrong model given to view.update");
        }
        MenuModel menuModel = (MenuModel) model;

        if (menuModel.simulationIsPaused()) {
            pauseMenuItem.setEnabled(false);
            resumeMenuItem.setEnabled(true);
        } else {
            pauseMenuItem.setEnabled(true);
            resumeMenuItem.setEnabled(false);
        }

    }


}
