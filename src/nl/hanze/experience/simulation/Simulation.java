package nl.hanze.experience.simulation;

import nl.hanze.experience.objects.ParkingSpot;
import nl.hanze.experience.objects.Reservation;
import nl.hanze.experience.objects.Vehicle;
import nl.hanze.experience.parkinggarage.controllers.SimulationInfoController;
import nl.hanze.experience.parkinggarage.models.GarageModel;
import nl.hanze.experience.parkinggarage.models.VehicleGraphModel;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;
import nl.hanze.experience.parkinggarage.models.VehiclePieModel;
import nl.hanze.experience.parkinggarage.views.SimulationInfoView;


import static nl.hanze.experience.objects.Vehicle.*;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/**
 * The simulation class
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public class Simulation {
    private SimulationThread simulationThread;
    private final static int simulationSleepPerTick = 0;

    private Random random;
    private long seed;

    private Modifier modifier;

    private SimulationInfoModel simulationInfoModel;
    private SimulationInfoView simulationInfoView;

    //private SimulationInfoController simulationInfoController;
    private GarageModel garageModel;
    private VehicleGraphModel vehicleGraphModel;
    private VehiclePieModel vehiclePieModel;
    private ReservationsQueue reservationsQueue;
    private timeOfLeavingQueue timeOfLeavingQueue;

    /**
     * Make new simulation
     */
    public Simulation() {
        simulationInfoModel = new SimulationInfoModel();
        simulationInfoModel.setSimulation(this); // In order to let the model interact with the simulation
        simulationInfoView = new SimulationInfoView();
        SimulationInfoController simulationInfoController = new SimulationInfoController(simulationInfoModel);

        simulationInfoModel.addView(simulationInfoView);
        simulationInfoView.setController(simulationInfoController);

        reservationsQueue = new ReservationsQueue();
        timeOfLeavingQueue = new timeOfLeavingQueue();

        seed = ThreadLocalRandom.current().nextLong();
        random = new Random(seed);

        modifier = new Modifier();
    }
    /**
     * Starts the simulation
     */
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
    /**
     * Pauses the simulation
     */
    public void pause() {
        if (simulationThread == null) {
            throw new IllegalStateException("Simulation has never been started");
        }
        simulationThread.pause();
    }
    /**
     * Resums the simulation if it is paused
     */
    public void resume() {
        if (simulationThread == null) {
            throw new IllegalStateException("Simulation has never been started");
        }
        simulationThread.resume();
    }
    /**
     * Tests if the simualtion is paused
     * @return boolean with the value of true if simulation is paused
     */
    public boolean isPaused() {
        if (simulationThread == null) {
            //throw new IllegalStateException("Simulation has never been started");
            return false;
        }
        return simulationThread.paused;
    }
    /**
     * Tests if the simuation is running
     * @return boolean with the value of true if the simulation is running
     */
    public boolean hasStarted() {
        if (simulationThread == null) {
            return false;
        }
        return true;
    }
    /**
     * Manage simulation threads
     */
    class SimulationThread implements Runnable {

        private final Object pauseLock = new Object();
        private volatile boolean exit = false;
        private volatile boolean paused = false;
        private int tickCount = 0;
        /**
         * Execute threads in the simulation
         */
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
                System.out.println("Tick: " + tickCount);
                generateVehicles();
                handleReservationsQueue();
                handleSubscriptionQueue();
                //System.out.println("SubscriptionQueueSize: " + garageModel.getSubscriptionQueueSize());
                handleTicketQueue();
                //System.out.println("TicketQueueSize: " + garageModel.getTicketQueueSize());
                handleLeavingVehicles();
                if (tickCount % 60 == 0) {
                    handleGraphs();
                }
                if (tickCount % 1440 == 0) {
                    garageModel.createLogToDailyMonyLog();
                }

                tickCount++;
                //System.out.println(tickCount);
                try {
                    Thread.sleep(simulationSleepPerTick);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        /**
         * Stop the thread
         */
        public void stop() {
            exit = true;
        }
        /**
         * Pause the thread
         */
        public void pause() {
            paused = true;
        }
        /**
         * Resume the thread
         */
        public void resume() {
            synchronized (pauseLock) {
                paused = false;
                pauseLock.notifyAll(); // Unblocks thread
            }
        }
    }
    /**
     * Get the Simulation information
     * @return information over the simulation
     */
    public SimulationInfoView getSimulationInfoView() {
        return simulationInfoView;
    }

    public GarageModel getGarageModel() {
        return garageModel;
    }
    public void setGarageModel(GarageModel garageModel) {
        this.garageModel = garageModel;
    }

    public void setVehicleGraphModel(VehicleGraphModel vehicleGraphModel) {
        this.vehicleGraphModel = vehicleGraphModel;
    }
    public void setVehiclePieModel(VehiclePieModel vehiclePieModel) {
        this.vehiclePieModel = vehiclePieModel;
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
    /**
     * Generates vehicles when the simulation starts
     */
    private void generateVehicles() {
        LocalDateTime localDateTime = garageModel.getLocalDateTime();
        int day = localDateTime.getDayOfWeek().getValue();
        int hour = localDateTime.getHour();
        PaymentType paymentType;
        Type type;

        // Calculate the chance of spawning a vehicle based on the modifiers
        double chanceModifier = ( modifier.getWeekdayModifier(day -1) + modifier.getHourModifier(hour) ) /2;
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
            int average = (int)garageModel.getGarageSetting("averageVehicleDurationInMinutes");
            int max = (int)garageModel.getGarageSetting("maxVehicleDurationInMinutes");
            int min = (int)garageModel.getGarageSetting("minVehicleDurationInMinutes");
            double standard = random.nextGaussian();
            int duration = (int)((standard * (deviation * 100)) + average);
            if (duration < min) {
                duration = min;
            } else if (duration > max) {
                duration = max;
            }

            Vehicle vehicle = new Vehicle(type, paymentType, duration);

            if (paymentType == PaymentType.RESERVATION) {
                double resDeviation = modifier.getReservationDurationModifier();
                int resAverage = (int)garageModel.getGarageSetting("averageReservationDurationInMinutes");
                int resMax = (int)garageModel.getGarageSetting("maxReservationDurationInMinutes");
                int resMin = (int)garageModel.getGarageSetting("minReservationDurationInMinutes");
                double resStandard = random.nextGaussian();
                int resDuration = (int)((resStandard * (resDeviation * 100)) + resAverage);
                if (resDuration < resMin) {
                    resDuration = resMin;
                } else if (resDuration > resMax) {
                    resDuration = resMax;
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
            //System.out.println("Spawned a " + vehicle.getType() + " With PaymentType: " + vehicle.getPaymentType() + " Time left: " + vehicle.getDuration());
            //generateVehicles();
        }
    }
    /**
     * Managing the reservations in the queue
     */
    private void handleReservationsQueue() {
        Reservation reservation = reservationsQueue.peek();
        while (reservation != null) {
            if (reservation.getTimeOfArrival() >= simulationThread.tickCount) {
                Vehicle vehicle = reservationsQueue.poll().getVehicle();
                garageModel.addToTicketQueue(vehicle);
                reservation = reservationsQueue.peek();
            }
        }
    }

    // TODO: Handle Vehicle.Type
    /**
     * Handling the subscription queue
     */
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
                garageModel.setTotalSubVehicles(garageModel.getTotalSubVehicles() + 1);
            }
            //TODO: Sub cars go to empty ticket queue?

            // Check if there was a free parking spot
            if (parkingSpot == null) {
                // There was no free parking spot available
                continue;
            }
            setNeighbouringParkingSpotWeight(parkingSpot, false);

            Vehicle vehicle = garageModel.pollVehicleFromSubscriptionQueue();
            vehicle.setTimeOfLeaving(vehicle.getDuration() + simulationThread.tickCount);
            timeOfLeavingQueue.add(vehicle);
            vehicle.setParkingSpot(parkingSpot);
            parkingSpot.setVehicle(vehicle);
            garageModel.notifyView();
        }
    }

    // TODO: Handle Reservations
    /**
     * handling the ticket queue
     */
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
            PaymentType paymentType = peek.getPaymentType();
            ParkingSpot parkingSpot = null;

            if (type == Type.MOTORCYCLE) {
                if (garageModel.getNumberOfFreeMotorcycleSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(type, PaymentType.TICKET);
                    garageModel.setNumberOfFreeMotorcycleSpots(garageModel.getNumberOfFreeMotorcycleSpots() - 1);
                } else if (garageModel.getNumberOfFreeRegularTicketSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(Type.CAR, PaymentType.TICKET);
                    garageModel.setNumberOfFreeRegularTicketSpots(garageModel.getNumberOfFreeRegularTicketSpots() - 1);
                }
            } else if (type == Type.ELECTRIC_CAR) {
                if (garageModel.getNumberOfFreeElectricSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(type, PaymentType.TICKET);
                    garageModel.setNumberOfFreeElectricSpots(garageModel.getNumberOfFreeElectricSpots() - 1);
                } else if (garageModel.getNumberOfFreeRegularTicketSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(Type.CAR, PaymentType.TICKET);
                    garageModel.setNumberOfFreeRegularTicketSpots(garageModel.getNumberOfFreeRegularTicketSpots() - 1);
                }
            }else  if (paymentType == PaymentType.RESERVATION) {
                if (garageModel.getNumberOfFreeReservedSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(type, paymentType);
                    garageModel.setNumberOfFreeReservedSpots(garageModel.getNumberOfFreeReservedSpots() - 1);
                } else if (garageModel.getNumberOfFreeRegularTicketSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(Type.CAR, PaymentType.TICKET);
                    garageModel.setNumberOfFreeRegularTicketSpots(garageModel.getNumberOfFreeRegularTicketSpots() - 1);
                }
            } else {
                if (garageModel.getNumberOfFreeRegularTicketSpots() > 0) {
                    parkingSpot = getFreeParkingSpot(Type.CAR, PaymentType.TICKET);
                    garageModel.setNumberOfFreeRegularTicketSpots(garageModel.getNumberOfFreeRegularTicketSpots() - 1);
                }
            }

            if (peek.getPaymentType() == PaymentType.RESERVATION) {
                garageModel.setTotalResVehicles(garageModel.getTotalResVehicles() + 1);
            } else {
                garageModel.setTotalTicVehicles(garageModel.getTotalTicVehicles() + 1);
            }

            // Check if there was a free parking spot
            if (parkingSpot == null) {
                // There was no free parking spot available
                continue;
            }
            setNeighbouringParkingSpotWeight(parkingSpot, false);

            Vehicle vehicle = garageModel.pollVehicleFromTicketQueue();
            vehicle.setTimeOfLeaving(vehicle.getDuration() + simulationThread.tickCount);
            timeOfLeavingQueue.add(vehicle);
            vehicle.setParkingSpot(parkingSpot);
            parkingSpot.setVehicle(vehicle);
            garageModel.notifyView();
        }
    }
    /**
     * set the weight for the nabouring parking spot
     * @param parkingSpot Current parking spot
     * @param leaving boolean if vehicle in the spot is leaving
     */
    private void setNeighbouringParkingSpotWeight(ParkingSpot parkingSpot, boolean leaving) {
        ParkingSpot parkingSpotLeft = garageModel.getParkingSpotLeft(parkingSpot);
        ParkingSpot parkingSpotRight = garageModel.getParkingSpotRight(parkingSpot);
        double weightModifier = modifier.getNeighbouringParkingSpotWeightModifier();
        if (leaving) {
            weightModifier = weightModifier * -1;
        }
        for (int i = 1; i <= 6; i++) {
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
    /**
     * Depending on the vehicle type gets a free parking spot
     * @param type type of the vehicle
     * @param paymentType what kind of payment type (subscription, ticket or reservation)
     * @return A free parking spot
     */
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
    /**
     * Handling leaving vehicles
     */
    private void handleLeavingVehicles() {
        Vehicle vehicle = timeOfLeavingQueue.peek();
        int count = 0;
        while (count < (int)garageModel.getGarageSetting("ticketQueueSpeed") && vehicle != null) {
            count++;
            if (vehicle.getTimeOfLeaving() <= simulationThread.tickCount) {
                // Is it time for the vehicle to leave?
                // Remove the vehicle from the Queue
                vehicle = timeOfLeavingQueue.poll();

                //TODO: Handle payment/ done?
                garageModel.vehiclePay(vehicle);
                ParkingSpot parkingSpot = vehicle.getParkingSpot();
                vehicle.setParkingSpot(null);
                parkingSpot.setVehicle(null);
                setNeighbouringParkingSpotWeight(parkingSpot, true);

                // TODO: Remove Sub/Res limit
                if(parkingSpot.getPaymentType() == PaymentType.SUBSCRIPTION) {
                    garageModel.setNumberOfFreeSubscriptionSpots(garageModel.getNumberOfFreeSubscriptionSpots() + 1);

                } else if (parkingSpot.getPaymentType() == PaymentType.RESERVATION) {
                    garageModel.setNumberOfFreeReservedSpots(garageModel.getNumberOfFreeReservedSpots() + 1);

                } else {
                    if(parkingSpot.getType() == Type.MOTORCYCLE) {
                        garageModel.setNumberOfFreeMotorcycleSpots(garageModel.getNumberOfFreeMotorcycleSpots() + 1);
                    } else if (parkingSpot.getType() == Type.ELECTRIC_CAR) {
                        garageModel.setNumberOfFreeElectricSpots(garageModel.getNumberOfFreeElectricSpots() + 1);
                    } else {
                        garageModel.setNumberOfFreeRegularTicketSpots(garageModel.getNumberOfFreeRegularTicketSpots() + 1) ;
                    }
                }

                if(vehicle.getPaymentType() == PaymentType.SUBSCRIPTION) {
                    garageModel.setTotalSubVehicles(garageModel.getTotalSubVehicles() - 1);
                } else if (vehicle.getPaymentType() == PaymentType.RESERVATION) {
                    garageModel.setTotalResVehicles(garageModel.getTotalResVehicles() - 1);
                } else {
                    garageModel.setTotalTicVehicles(garageModel.getTotalTicVehicles() - 1);
                }

                //System.out.println("Vehicle of Type: " + vehicle.getType() + " and PaymentType " + vehicle.getPaymentType() + " left" );
                garageModel.notifyView();
            }
            // For the next loop
            vehicle = timeOfLeavingQueue.peek();
        }
    }
    /**
     * Handling the graphs
     */
    private void handleGraphs() {
        if (vehicleGraphModel == null) {
            throw new IllegalStateException("Simulation - vehicleGraphModel not set");
        }
        if (vehiclePieModel == null) {
            throw new IllegalStateException("Simulation - vehiclePieModel not set");
        }
        vehicleGraphModel.updateTimeSeries(
                garageModel.getLocalDateTime(),
                garageModel.getTotalSubVehicles(),
                garageModel.getTotalResVehicles(),
                garageModel.getTotalTicVehicles()
        );
        vehicleGraphModel.notifyView();

        vehiclePieModel.updatePieDataset(
                garageModel.getTotalTicVehicles(),
                0,
                0,
                garageModel.getTotalSubVehicles(),
                garageModel.getTotalResVehicles()
        );
        vehiclePieModel.notifyView();
    }
}

