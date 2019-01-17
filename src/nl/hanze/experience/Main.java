package nl.hanze.experience;

import nl.hanze.experience.parkinggarage.controllers.*;
import nl.hanze.experience.parkinggarage.models.*;
import nl.hanze.experience.parkinggarage.views.*;
import nl.hanze.experience.simulation.Simulation;

import javax.swing.*;
import java.awt.*;

/**
 * nl.hanze.experience.Main class for the ParkingGarage
 * @author Mike van der Velde
 * @version 0.0.3
 * @since 0.0.2
 */
public class Main {

    /**
     * Initialize the simulation
     */
    public static void main(String[] args) {
        TestModel testModel = new TestModel();
        TestView testView = new TestView();
        TestController testController = new TestController(testModel);

        testModel.addView(testView);
        testView.setController(testController);

        Simulation simulation = new Simulation();

        JFrame frame = new JFrame();
        frame.setTitle("Garage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(simulation.getSimulationInfoView());
        container.add(testView);
        frame.pack();
        frame.setSize(800,800);
        frame.setVisible(true);

        simulation.start();
    }
}
