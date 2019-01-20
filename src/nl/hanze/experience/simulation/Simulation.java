package nl.hanze.experience.simulation;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.controllers.SimulationInfoController;
import nl.hanze.experience.parkinggarage.models.GarageModel;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;
import nl.hanze.experience.parkinggarage.views.SimulationInfoView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class Simulation {
    private SimulationThread simulationThread;
    
    private SimulationInfoModel simulationInfoModel;
    private SimulationInfoView simulationInfoView;
    private HashMap<String, Integer> garageSettings = new HashMap<>();
    //private SimulationInfoController simulationInfoController;
    private GarageModel garage;

    public Simulation() {
        simulationInfoModel = new SimulationInfoModel();
        simulationInfoModel.setSimulation(this); // In order to let the model interact with the simulation
        simulationInfoView = new SimulationInfoView();
        SimulationInfoController simulationInfoController = new SimulationInfoController(simulationInfoModel);

        simulationInfoModel.addView(simulationInfoView);
        simulationInfoView.setController(simulationInfoController);

        garageSettings.put("floors",3);
        garageSettings.put("rows",5);
        garageSettings.put("spots",20);
    }

    public HashMap getGarageSettings() {
        return garageSettings;
    }

    public void setGarageSettings(int floors, int rows, int spots) {
        garageSettings.put("floors", floors);
        garageSettings.put("rows", rows);
        garageSettings.put("spots", spots);
    }

    public void start() {
        simulationThread = new SimulationThread();
        simulationThread.run();
        garage = new GarageModel();
        garage.createGarage(garageSettings.get("floors"),garageSettings.get("rows"),garageSettings.get("spots"));
    }
    public void pause() {
        if (simulationThread == null) {
            throw new IllegalStateException("Simulation has never been started");
        }
        simulationThread.pause();
    }
    public void resume() {
        if (simulationThread == null) {
            throw new IllegalStateException("Simulation has never been started");
        }
        simulationThread.resume();
    }

    public boolean isPaused() {
        if (simulationThread == null) {
            throw new IllegalStateException("Simulation has never been started");
        }
        return simulationThread.paused;
    }


    class SimulationThread implements Runnable {

        private final Object pauseLock = new Object();
        private volatile boolean exit = false;
        private volatile boolean paused = false;
        private int tickCount = 0;

        @Override
        public void run() {
            while(!exit) {
                if (paused) {
                    synchronized (pauseLock) {
                        try {
                            pauseLock.wait(); // will cause this Thread to block until
                            // another thread calls pauseLock.notifyAll()
                            // Note that calling wait() will
                            // relinquish the synchronized lock that this
                            // thread holds on pauseLock so another thread
                            // can acquire the lock to call notifyAll()
                            // (link with explanation below this code)
                        } catch (InterruptedException ex) {
                            break;
                        }
                    }
                }
                simulationInfoModel.setTickCount(tickCount);
                simulationInfoModel.increaseTime(1);
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
        public void pause() {
            paused = true;
        }
        public void resume() {
            synchronized (pauseLock) {
                paused = false;
                pauseLock.notifyAll(); // Unblocks thread
            }
        }
    }

    public SimulationInfoView getSimulationInfoView() {
        return simulationInfoView;
    }
}

