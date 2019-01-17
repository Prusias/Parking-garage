package nl.hanze.experience.simulation;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.controllers.SimulationInfoController;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;
import nl.hanze.experience.parkinggarage.views.SimulationInfoView;

import javax.swing.*;
import java.awt.*;

public class Simulation {

    private SimulationInfoModel simulationInfoModel;
    private SimulationInfoView simulationInfoView;
    private SimulationInfoController simulationInfoController;

    public Simulation() {
        simulationInfoModel = new SimulationInfoModel();
        simulationInfoView = new SimulationInfoView();
        simulationInfoController = new SimulationInfoController(simulationInfoModel);

        simulationInfoModel.addView(simulationInfoView);
        simulationInfoView.setController(simulationInfoController);
    }

    public void start() {
        SimulationThread simulationThread = new SimulationThread();
        simulationThread.run();
    }

    class SimulationThread implements Runnable {

        private volatile boolean exit = false;
        private int tickCount = 0;

        @Override
        public void run() {
            while(!exit) {
                simulationInfoModel.setTickCount(tickCount);
                tickCount++;
                //System.out.println(tickCount);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stop() {
            exit = true;
        }
    }

    public SimulationInfoView getSimulationInfoView() {
        return simulationInfoView;
    }
}

