package Views;

import Controllers.SimulatorController;
import Models.Car;
import Models.Location;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends JFrame {
    private CarParkView carParkView;
    protected SimulatorController simulatorController;

    public SimulatorView(SimulatorController simulatorController) {
        this.simulatorController = simulatorController;
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
