package Views;

import Controllers.SimulatorController;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends JFrame {
    private CarParkView carParkView;
    private SimulatorController simController;

    public SimulatorController getSimulatorController() {
        return simController;
    }

    public SimulatorView(SimulatorController simulatorController) {
        this.simController = simulatorController;
        carParkView = new CarParkView(this);

        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        pack();
        setVisible(true);

        updateView();
    }

    public void updateView() {
        carParkView.updateView();
    }
}
