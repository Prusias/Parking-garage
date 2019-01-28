package nl.hanze.experience.simulation;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.objects.ParkingSpot;
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
    private final static int simulationSleepPerTick = 100;

    private Random random;
    private long seed;

    private Modifier modifier;

    private SimulationInfoModel simulationInfoModel;
    private SimulationInfoView simulationInfoView;

    //private SimulationInfoController simulationInfoController;
    private GarageModel garageModel;
    private ReservationsQueue reservationsQueue;


    public Simulation() {
        simulationInfoModel = new SimulationInfoModel();
        simulationInfoModel.setSimulation(this); // In order to let the model interact with the simulation
        simulationInfoView = new SimulationInfoView();
        SimulationInfoController simulationInfoController = new SimulationInfoController(simulationInfoModel);

        simulationInfoModel.addView(simulationInfoView);
        simulationInfoView.setController(simulationInfoController);

        reservationsQueue = new ReservationsQueue();

        seed = ThreadLocalRandom.current().nextLong();
        random = new Random(seed);

        modifier = new Modifier();
    }

    public void start() {
        if (garageModel == null) {
            throw new IllegalStateException("Simulation - garageModel has not been set!");
        }

        garageModel.initializeGarage();
        //parkingSpotWeightedQueue.Fill(garageModel.getParkingSpots());

        simulationThread = new SimulationThread();
        Thread thread = new Thread(simulationThread);
        thread.start();

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
                handleReservationsQueue();
                handleSubscriptionQueue();
                handleTicketQueue();

                tickCount++;
                //System.out.println(tickCount);
                try {
                    Thread.sleep(simulationSleepPerTick);

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

            // Take leaving cars because of queue length into a count
            if (paymentType == PaymentType.TICKET || paymentType == PaymentType.RESERVATION) {
                double queueSizeValue = modifier.getTicketQueueSizeModifier() * garageModel.getTicketQueueSize();
                //System.out.println("Size: " + queueSizeValue + " Max: " + modifier.getTicketQueueMaxSize());
                if (queueSizeValue > modifier.getTicketQueueMaxSize()) {
                    return;
                }
            }
            if (paymentType == PaymentType.SUBSCRIPTION) {
                double queueSizeValue = modifier.getSubscriptionQueueSizeModifier() * garageModel.getSubscriptionQueueSize();
                if (queueSizeValue > modifier.getSubscriptionQueueMaxSize()) {
                    return;
                }
            }


            // What is the vehicle type?
            // TODO: Remove Sub/Res limit
            if (paymentType != PaymentType.SUBSCRIPTION && paymentType != paymentType.RESERVATION) {
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
            } else {
                type = type = Type.CAR;
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
                double resDeviation = modifier.getReservationDurationModifier();
                int resMax = (int)garageModel.getGarageSetting("maxReservationDurationInMinutes");
                int resMin = (int)garageModel.getGarageSetting("minReservationDurationInMinutes");
                double resStandard = random.nextGaussian();
                int resDuration = (int)((resStandard * (resDeviation * 100)) + resMax /2);
                if (resDuration < resMin) {
                    resDuration = resMin;
                }

                Reservation reservation = new Reservation(vehicle, simulationThread.tickCount + resDuration, duration);
                //System.out.println("Spawned a " + vehicle.getType() + " Reservation Time left: " + reservation.getTimeOfArrival());
                reservationsQueue.add(reservation);
            } else if (paymentType == PaymentType.SUBSCRIPTION) {
                garageModel.addToSubscriptionQueue(vehicle);
            } else {
                garageModel.addToTicketQueue(vehicle);
            }

            // By now we know we spawned a car.
            System.out.println("Spawned a " + vehicle.getType() + " With PaymentType: " + vehicle.getPaymentType() + " Time left: " + vehicle.getDuration());
            generateCars();
        }
    }

    private void handleReservationsQueue() {
        Reservation reservation = reservationsQueue.peek();
        if (reservation != null) {
            if (reservation.getTimeOfArrival() == simulationThread.tickCount) {
                Vehicle vehicle = reservationsQueue.poll().getVehicle();
                garageModel.addToTicketQueue(vehicle);
            }
        }
    }

    // TODO: Handle Vehicle.Type
    private void handleSubscriptionQueue() {
        int count = 0;
        while (count < (int)garageModel.getGarageSetting("subscriptionQueueSpeed")) {
            count++;
            Vehicle peek = garageModel.peekVehicleFromSubscriptionQueue();
            // Is there a vehicle in the queue?
            if (peek == null) {
                return;
            }
            Vehicle.Type type = peek.getType();
            ParkingSpot parkingSpot = null;
            // Check which spot the vehicle will go to
            if (garageModel.getNumberOfFreeSubscriptionSpots() > 0) {
                parkingSpot = getFreeParkingSpot(type, PaymentType.SUBSCRIPTION);
                garageModel.setNumberOfFreeSubscriptionSpots(garageModel.getNumberOfFreeSubscriptionSpots() - 1);
            }
            //TODO: Sub cars go to empty ticket queue?

            // Check if there was a free parking spot
            if (parkingSpot == null) {
                // There was no free parking spot available
                continue;
            }
            setNeighbouringParkingSpotWeight(parkingSpot);

            Vehicle vehicle = garageModel.pollVehicleFromSubscriptionQueue();
            parkingSpot.setVehicle(vehicle);
            garageModel.notifyView();
        }
    }

    // TODO: Handle Reservations
    private void handleTicketQueue() {
        int count = 0;
        while (count < (int)garageModel.getGarageSetting("ticketQueueSpeed")) {
            count++;
            Vehicle peek = garageModel.peekVehicleFromTicketQueue();
            // Is there a vehicle in the queue?
            if (peek == null) {
                return;
            }
            Vehicle.Type type = peek.getType();
            ParkingSpot parkingSpot = null;

            if (type == Type.MOTORCYCLE) {
                if (garageModel.getNumberOfFreeMotorcycleSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(type, PaymentType.TICKET);
                    garageModel.setNumberOfFreeMotorcycleSpots(garageModel.getNumberOfFreeMotorcycleSpots() - 1);
                } else if (garageModel.getNumberOfFreeRegularSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(Type.CAR, PaymentType.TICKET);
                    garageModel.setNumberOfFreeRegularSpots(garageModel.getNumberOfFreeRegularSpots() - 1);
                }
            } else if (type == Type.ELECTRIC_CAR) {
                if (garageModel.getNumberOfFreeElectricSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(type, PaymentType.TICKET);
                    garageModel.setNumberOfFreeElectricSpots(garageModel.getNumberOfFreeElectricSpots() - 1);
                } else if (garageModel.getNumberOfFreeRegularSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(Type.CAR, PaymentType.TICKET);
                    garageModel.setNumberOfFreeRegularSpots(garageModel.getNumberOfFreeRegularSpots() - 1);
                }
            } else {
                if (garageModel.getNumberOfFreeRegularSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(Type.CAR, PaymentType.TICKET);
                    garageModel.setNumberOfFreeRegularSpots(garageModel.getNumberOfFreeRegularSpots() - 1);
                }
            }

            // Check if there was a free parking spot
            if (parkingSpot == null) {
                // There was no free parking spot available
                continue;
            }
            setNeighbouringParkingSpotWeight(parkingSpot);

            Vehicle vehicle = garageModel.pollVehicleFromTicketQueue();
            parkingSpot.setVehicle(vehicle);
            garageModel.notifyView();
        }
    }

    private void setNeighbouringParkingSpotWeight(ParkingSpot parkingSpot) {
        ParkingSpot parkingSpotLeft = garageModel.getParkingSpotLeft(parkingSpot);
        ParkingSpot parkingSpotRight = garageModel.getParkingSpotRight(parkingSpot);
        double weightModifier = modifier.getNeighbouringParkingSpotWeightModifier();
        for (int i = 1; i <= 4; i++) {
            if (parkingSpotLeft != null) {
                parkingSpotLeft.setWeight(parkingSpotLeft.getWeight() + weightModifier / i);
                parkingSpotLeft = garageModel.getParkingSpotLeft(parkingSpotLeft);
            } else {
                break;
            }
        }
        for (int i = 1; i <= 4; i++) {
            if (parkingSpotRight != null) {
                parkingSpotRight.setWeight(parkingSpotRight.getWeight() + weightModifier / i);
                parkingSpotRight = garageModel.getParkingSpotRight(parkingSpotRight);
            } else {
                break;
            }
        }
    }

    private ParkingSpot getFreeParkingSpot(Vehicle.Type type, Vehicle.PaymentType paymentType) {
        ParkingSpot[][][] parkingSpots = garageModel.getParkingSpots();
        double  weight = Double.MAX_VALUE;
        ParkingSpot parkingSpot = null;
        for (int floor=0; floor < parkingSpots.length; floor++) {
            for (int row=0; row < parkingSpots[floor].length; row++) {
                for (int spot=0; spot < parkingSpots[floor][row].length; spot++) {
                    if (parkingSpots[floor][row][spot].getVehicle() == null) {
                        if (parkingSpots[floor][row][spot].getType() == type && parkingSpots[floor][row][spot].getPaymentType() == paymentType) {
                            if (parkingSpots[floor][row][spot].getWeight() < weight) {
                                parkingSpot = parkingSpots[floor][row][spot];
                                weight = parkingSpot.getWeight();
                            }
                        }
                    }
                }
            }
        }
        return parkingSpot;
    }
}

