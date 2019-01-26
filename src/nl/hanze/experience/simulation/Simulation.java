package nl.hanze.experience.simulation;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.objects.Reservation;
import nl.hanze.experience.objects.Vehicle;
import nl.hanze.experience.parkinggarage.controllers.SimulationInfoController;
import nl.hanze.experience.parkinggarage.models.GarageModel;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;
import nl.hanze.experience.parkinggarage.views.GarageView;
import nl.hanze.experience.parkinggarage.views.SimulationInfoView;

import static nl.hanze.experience.objects.Vehicle.*;

import java.time.LocalDateTime;
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
                simulationInfoModel.increaseTime();
                generateCars();
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

    private void generateCars() {
        LocalDateTime localDateTime = garageModel.getLocalDateTime();
        int day = localDateTime.getDayOfWeek().getValue();
        int hour = localDateTime.getHour();
        PaymentType paymentType;
        Type type;

        // Calculate the chance of spawning a vehicle based on the modifiers
        double chanceModifier = ( modifier.getWeekdayModifier(day) + modifier.getHourModifier(hour) ) /2;
        double chance = random.nextInt(101) * chanceModifier;
        double neededChance = 100 / modifier.getBaseVehicleModifier();
        if (chance > neededChance) {

            // We are spawning a vehicle, Does it have a subscription or a reservation?
            int ticketChance = (int)(modifier.getBaseVehicleModifier() * 100);
            int subscriptionChance = (int)(modifier.getSubscriptionVehicleModifier() * 100);
            int reservationChance = (int)(modifier.getReservationVehicleModifier() * 100);
            int vehicleChance = random.nextInt(ticketChance + subscriptionChance + reservationChance + 1);
            if (vehicleChance > subscriptionChance + reservationChance) {
                paymentType = PaymentType.TICKET;
            } else if (vehicleChance > reservationChance) {
                paymentType = PaymentType.SUBSCRIPTION;
            } else {
                paymentType = PaymentType.RESERVATION;
            }

            // What is the vehicle type?
            int regularCarChance = (int)(modifier.getRegularCarModifier() * 100);
            int electricCarChance = (int)(modifier.getElectricCarModifier() * 100);
            int motorcycleChance = (int)(modifier.getMotorcycleModifier() * 100);
            int typeChance = random.nextInt(regularCarChance + electricCarChance + motorcycleChance + 1);
            if (typeChance > motorcycleChance + electricCarChance) {
                type = Type.CAR;
            } else if (typeChance > electricCarChance) {
                type = Type.ELECTRIC_CAR;
            } else {
                type = Type.MOTORCYCLE;
            }

            double deviation = modifier.getParkingDurationModifier();
            int max = (int)garageModel.getGarageSetting("maxVehicleDurationInMinutes");
            int min = (int)garageModel.getGarageSetting("minVehicleDurationInMinutes");
            double standard = random.nextGaussian();
            int duration = (int)((standard * (deviation * 100)) + max /2);
            if (duration < min) {
                duration = min;
            }

            Vehicle vehicle = new Vehicle(type, paymentType, duration);

            if (paymentType == PaymentType.RESERVATION) {
                double resDeviation = modifier.getReservationVehicleModifier();
                int resMax = (int)garageModel.getGarageSetting("maxReservationDurationInMinutes");
                int resMin = (int)garageModel.getGarageSetting("minReservationDurationInMinutes");
                double resStandard = random.nextGaussian();
                int resDuration = (int)((resStandard * (resDeviation * 100)) + resMax /2);
                if (resDuration < resMin) {
                    resDuration = resMin;
                }

                Reservation reservation = new Reservation(vehicle, resDuration, duration);
                System.out.println("Spawned a " + vehicle.getType() + " Reservation Time left: " + reservation.getTimeOfArrival());

            } else if (paymentType == PaymentType.SUBSCRIPTION) {


            } else {

            }

            System.out.println("Spawned a " + vehicle.getType() + " Time left: " + vehicle.getDuration());
        }
    }
}

