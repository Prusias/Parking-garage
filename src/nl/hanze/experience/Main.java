package nl.hanze.experience;

import nl.hanze.experience.parkinggarage.controllers.GarageController;
import nl.hanze.experience.parkinggarage.controllers.SimulationMenuController;
import nl.hanze.experience.parkinggarage.models.*;
import nl.hanze.experience.parkinggarage.views.*;
import nl.hanze.experience.simulation.Simulation;

import javax.swing.*;
import java.awt.*;

/**
 * Main class for the ParkingGarage
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
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
        garageModel.setGarageSetting("amountOfRows", 8);
        garageModel.setGarageSetting("amountOfSpots", 22);
        garageModel.setGarageSetting("priceInEuro", 0.5);
        garageModel.setGarageSetting("averageVehicleDurationInMinutes", 240);
        garageModel.setGarageSetting("maxVehicleDurationInMinutes", 1440);
        garageModel.setGarageSetting("minVehicleDurationInMinutes", 10);
        garageModel.setGarageSetting("averageReservationDurationInMinutes", 240);
        garageModel.setGarageSetting("maxReservationDurationInMinutes", 240);
        garageModel.setGarageSetting("minReservationDurationInMinutes", 30);
        garageModel.setGarageSetting("subscriptionSpots", 40);
        garageModel.setGarageSetting("electricSpots", 10);
        garageModel.setGarageSetting("motorcycleSpots", 10);
        garageModel.setGarageSetting("ticketQueueSpeed", 5);
        garageModel.setGarageSetting("subscriptionQueueSpeed", 5);
        garageModel.setGarageSetting("exitQueueSpeed", 3);
        garageModel.setGarageSetting("floorWeight", 1.5);
        garageModel.setGarageSetting("rowWeight", .7);
        garageModel.setGarageSetting("spotWeight", .2);
        garageModel.setGarageSetting("reservationReservedMinutes", 15);
        garageModel.setGarageSetting("reservationKeptReservedMinutes", 30);
        garageModel.setGarageSetting("amountOfTicketQueues", 1);
        garageModel.setGarageSetting("amountOfSubscriptionQueues", 1);
        garageModel.setGarageSetting("amountOfExitQueues", 1);
        garageModel.setGarageSetting("reservationPrice", 5.0);
        garageModel.setGarageSetting("simulationSleepTime", 0);

        GarageController garageController = new GarageController(garageModel);
        GarageView garageView = new GarageView();
        garageView.setController(garageController);
        garageModel.addView(garageView);
        simulation.setGarageModel(garageModel);

        VehicleGraphModel vehicleGraphModel = new VehicleGraphModel();
        VehicleGraphView vehicleGraphView = new VehicleGraphView();
        vehicleGraphModel.addView(vehicleGraphView);
        simulation.setVehicleGraphModel(vehicleGraphModel);

        VehiclePieModel vehiclePieModel = new VehiclePieModel();
        VehiclePieView vehiclePieView = new VehiclePieView();
        vehiclePieModel.addView(vehiclePieView);
        simulation.setVehiclePieModel(vehiclePieModel);

        QueueGraphModel queueGraphModel = new QueueGraphModel();
        QueueGraphView queueGraphView = new QueueGraphView();
        queueGraphModel.addView(queueGraphView);
        simulation.setQueueGraphModel(queueGraphModel);

        DailyIncomeGraphModel dailyIncomeGraphModel = new DailyIncomeGraphModel();
        DailyIncomeGraphView dailyIncomeGraphView = new DailyIncomeGraphView();
        dailyIncomeGraphModel.addView(dailyIncomeGraphView);
        simulation.setDailyIncomeGraphModel(dailyIncomeGraphModel);

        JFrame frame = new JFrame();
        frame.setTitle("Garage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        //container.setSize(800, 800);
        //container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(simulationMenuView);
        frame.setJMenuBar(menuBar);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 0;
        c.ipady = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        container.add(simulation.getSimulationInfoView(), c);
        // GarageView
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 600;
        c.ipady = 700;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        container.add(garageView, c);
        // Graphs
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 700;
        c.ipady = 450;
        c.gridx = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.gridy = 1;
        TabbedView tabbedView = new TabbedView();
        tabbedView.addPanel(vehicleGraphView, "Vehicles in garage");
        tabbedView.addPanel(vehiclePieView, "Vehicle types in garage");
        tabbedView.addPanel(queueGraphView, "Queue size");
        tabbedView.addPanel(dailyIncomeGraphView, "Daily income");
        container.add(tabbedView, c);

        container.setBackground(Color.WHITE);
        frame.pack();
        frame.setSize(new Dimension(1600, 900));
        frame.setVisible(true);

        simulationMenuModel.setSimulation(simulation);
    }
}