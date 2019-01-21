package nl.hanze.experience.simulation;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.controllers.SimulationInfoController;
import nl.hanze.experience.parkinggarage.models.GarageModel;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;
import nl.hanze.experience.parkinggarage.views.SimulationInfoView;

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
    private HashMap<String, Object> garageSettings = new HashMap<>();
    //private SimulationInfoController simulationInfoController;
    private GarageModel garageModel;

    public Simulation() {
        simulationInfoModel = new SimulationInfoModel();
        simulationInfoModel.setSimulation(this); // In order to let the model interact with the simulation
        simulationInfoView = new SimulationInfoView();
        SimulationInfoController simulationInfoController = new SimulationInfoController(simulationInfoModel);

        simulationInfoModel.addView(simulationInfoView);
        simulationInfoView.setController(simulationInfoController);

        setGarageSetting("amountOfFloors", 3);
        setGarageSetting("amountOfRows", 5);
        setGarageSetting("amountOfSpots", 20);
        setGarageSetting("priceInEuro", 0.5f);
    }

    //TODO: Are these 2 functions needed?
    public HashMap getGarageSettings() {
        return garageSettings;
    }

    public void setGarageSettings(int floors, int rows, int spots) {
        garageSettings.put("amountOfFloors", floors);
        garageSettings.put("amountOfRows", rows);
        garageSettings.put("amountOfSpots", spots);
    }

    public Object getGarageSetting(String key) {
        if (!garageSettings.containsKey(key)) {
            throw new IllegalStateException("Simulation - garageSettings key does not exist");
        }
        return garageSettings.get(key);
    }

    public void setGarageSetting(String key, Object value) {
        garageSettings.put(key, value);
    }

    public void start() {
        simulationThread = new SimulationThread();
        Thread thread = new Thread(simulationThread);
        thread.start();
        garageModel = new GarageModel();
        garageModel.createGarage(
                (int)garageSettings.get("amountOfFloors"),
                (int)garageSettings.get("amountOfRows"),
                (int)garageSettings.get("amountOfSpots")
        );
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
            //throw new IllegalStateException("Simulation has never been started");
            return false;
        }
        return simulationThread.paused;
    }

    public boolean hasStarted() {
        if (simulationThread == null) {
            return false;
        }
        return true;
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

