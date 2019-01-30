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
        /*
        GarageModel garageModel = new GarageModel();
        garageModel.createGarage(3,4,22);
        System.out.println(garageModel.getNumberOfFreeSpots());
        garageModel.vehicleToQueue(Vehicle.Type.CAR, Vehicle.PaymentType.RESERVATION, 25);
        System.out.println(garageModel.getParkingSpot(2,2,3).getWeight());
        testModel.addView(testView);
        testView.setController(testController);
        */
        Simulation simulation = new Simulation();

        SimulationMenuModel simulationMenuModel = new SimulationMenuModel();
        SimulationMenuController simulationMenuController = new SimulationMenuController(simulationMenuModel);
        SimulationMenuView simulationMenuView = new SimulationMenuView();
        simulationMenuView.setController(simulationMenuController);
        simulationMenuModel.addView(simulationMenuView);

        GarageModel garageModel = new GarageModel();
        garageModel.setGarageSetting("amountOfFloors", 3);
        garageModel.setGarageSetting("amountOfRows", 5);
        garageModel.setGarageSetting("amountOfSpots", 20);
        garageModel.setGarageSetting("priceInEuro", 0.5);
        garageModel.setGarageSetting("averageVehicleDurationInMinutes", 120);
        garageModel.setGarageSetting("maxVehicleDurationInMinutes", 1440);
        garageModel.setGarageSetting("minVehicleDurationInMinutes", 10);
        garageModel.setGarageSetting("averageReservationDurationInMinutes", 120);
        garageModel.setGarageSetting("maxReservationDurationInMinutes", 240);
        garageModel.setGarageSetting("minReservationDurationInMinutes", 30);
        garageModel.setGarageSetting("subscriptionSpots", 40);
        garageModel.setGarageSetting("reservedSpots", 20);
        garageModel.setGarageSetting("electricSpots", 10);
        garageModel.setGarageSetting("motorcycleSpots", 10);
        garageModel.setGarageSetting("ticketQueueSpeed", 5);
        garageModel.setGarageSetting("subscriptionQueueSpeed", 5);
        garageModel.setGarageSetting("exitQueueSpeed", 3);
        GarageController garageController = new GarageController(garageModel);
        GarageView garageView = new GarageView();
        garageView.setController(garageController);
        garageModel.addView(garageView);
        simulation.setGarageModel(garageModel);

        VehicleGraphModel vehicleGraphModel = new VehicleGraphModel();
        VehicleGraphView vehicleGraphView = new VehicleGraphView();
        vehicleGraphModel.addView(vehicleGraphView);
        simulation.setVehicleGraphModel(vehicleGraphModel);

        JFrame frame = new JFrame();
        frame.setTitle("Garage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(simulationMenuView);
        frame.setJMenuBar(menuBar);

        container.add(simulation.getSimulationInfoView());
        container.add(garageView);
        container.add(vehicleGraphView);
        frame.pack();
        frame.setSize(800,800);
        frame.setVisible(true);

        //TODO: Why does this work here and not elsewhere
        simulationMenuModel.setSimulation(simulation);
        //simulation.start();
    }
}
