import Controllers.SimulatorController;

/**
 * Main class for the ParkingGarage
 * @author Mike van der Velde
 * @version 0.2
 * @since 0.2
 */
public class Main {

    /**
     * Initialize the simulation
     */
    public static void main(String[] args) {
        SimulatorController sim = new SimulatorController(3, 6, 30);
        sim.run();
    }
}
