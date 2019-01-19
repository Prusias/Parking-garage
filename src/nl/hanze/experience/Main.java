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
 * @version 0.0.5
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
        System.out.println(garageModel.getParkingSpot(2,2,3).getWeight());
        testModel.addView(testView);
        testView.setController(testController);

        Simulation simulation = new Simulation();

        SimulationMenuModel simulationMenuModel = new SimulationMenuModel();
        simulationMenuModel.setSimulation(simulation);
        SimulationMenuController simulationMenuController = new SimulationMenuController(simulationMenuModel);
        SimulationMenuView simulationMenuView = new SimulationMenuView();
        simulationMenuView.setController(simulationMenuController);
        simulationMenuModel.addView(simulationMenuView);

        JFrame frame = new JFrame();
        frame.setTitle("Garage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(simulationMenuView);
        frame.setJMenuBar(menuBar);

        container.add(simulation.getSimulationInfoView());
        container.add(testView);
        frame.pack();
        frame.setSize(800,800);
        frame.setVisible(true);

        simulation.start();
    }
}
