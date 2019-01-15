package nl.hanze.experience;

/**
 * nl.hanze.experience.Main class for the ParkingGarage
 * @author Mike van der Velde
 * @version 0.0.3
 * @since 0.0.2
 */
public class Main {

    /**
     * Initialize the simulation
     */
    public static void main(String[] args) {
        Simulation sim = new Simulation(3, 6, 30);
        sim.run();
    }
}
