package nl.hanze.experience;

import nl.hanze.experience.objects.Vehicle;
import nl.hanze.experience.parkinggarage.controllers.*;
import nl.hanze.experience.parkinggarage.models.*;
import nl.hanze.experience.parkinggarage.views.*;
import nl.hanze.experience.simulation.Simulation;

import javax.swing.*;
import java.awt.*;

/**
 * nl.hanze.experience.Main class for the ParkingGarage
 * @author Mike van der Velde
 * @version 0.0.4
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

        GarageModel garageModel = new GarageModel();
        garageModel.createGarage(3,4,22);
        System.out.println(garageModel.getNumberOfFreeSpots());
        garageModel.vehicleToQueue(Vehicle.Type.CAR, Vehicle.PaymentType.RESERVATION, 25);

        testModel.addView(testView);
        testView.setController(testController);

        Simulation simulation = new Simulation();

        MenuModel menuModel = new MenuModel();
        menuModel.setSimulation(simulation);
        MenuController menuController = new MenuController(menuModel);
        MenuView menuView = new MenuView();
        menuView.setController(menuController);
        menuModel.addView(menuView);

        JFrame frame = new JFrame();
        frame.setTitle("Garage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        frame.setJMenuBar(menuView.getMenuBar());
        container.add(simulation.getSimulationInfoView());
        container.add(testView);
        frame.pack();
        frame.setSize(800,800);
        frame.setVisible(true);

        simulation.start();
    }
}
