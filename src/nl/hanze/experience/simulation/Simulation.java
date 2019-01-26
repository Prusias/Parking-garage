package nl.hanze.experience.simulation;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.controllers.SimulationInfoController;
import nl.hanze.experience.parkinggarage.models.GarageModel;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;
import nl.hanze.experience.parkinggarage.views.GarageView;
import nl.hanze.experience.parkinggarage.views.SimulationInfoView;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class Simulation {
    private SimulationThread simulationThread;

    private Random random;
    private long seed;

    private Modifier modifier;

    private SimulationInfoModel simulationInfoModel;
    private SimulationInfoView simulationInfoView;

    //private SimulationInfoController simulationInfoController;
    private GarageModel garageModel;

    public Simulation() {
        simulationInfoModel = new SimulationInfoModel();
        simulationInfoModel.setSimulation(this); // In order to let the model interact with the simulation
        simulationInfoView = new SimulationInfoView();
        SimulationInfoController simulationInfoController = new SimulationInfoController(simulationInfoModel);

        simulationInfoModel.addView(simulationInfoView);
        simulationInfoView.setController(simulationInfoController);

        seed = ThreadLocalRandom.current().nextLong();
        random = new Random(seed);

        modifier = new Modifier();
    }

    public void start() {
        if (garageModel == null) {
            throw new IllegalStateException("Simulation - garageModel has not been set!");
        }
        simulationThread = new SimulationThread();
        Thread thread = new Thread(simulationThread);
        thread.start();
        garageModel.initializeGarage();
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

    public GarageModel getGarageModel() {
        return garageModel;
    }
    public void setGarageModel(GarageModel garageModel) {
        this.garageModel = garageModel;
    }

    /**
     * Get the Random object used by the simulation
     * @return Random
     */
    public Random getRandom() {
        return random;
    }
    /**
     * Set the seed of the simulation
     * @param seed seed for the simulation
     */
    public void setSeed(long seed) {
        random = new Random(seed);
        this.seed = seed;
    }
    /**
     * Get the seed of the simulation
     * @return seed for the simulation
     */
    public long getSeed() {
        return seed;
    }

    public Modifier getModifier() {
        return modifier;
    }
}

