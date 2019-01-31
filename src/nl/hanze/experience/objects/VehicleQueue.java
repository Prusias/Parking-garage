package nl.hanze.experience.objects;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The class for the vehicle queue
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class VehicleQueue {
    private Queue<Vehicle> queue;

    /**
     * Make new vehicle queue
     */
    public VehicleQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Add a vehicle to the queue
     * @param vehicle a vehicle to add to the queue
     */
    public void addVehicle(Vehicle vehicle) {
        queue.add(vehicle);
    }

    /**
     * Poll the vehicle on the top from the queue
     * @return Vehicle that is on the top of the queue
     */
    public Vehicle poll() {
        return queue.poll();
    }

    /**
     * See what vehicle is on the top of the queue without deleting it
     * @return Vehicle that is on the top of the queue
     */
    public Vehicle peek() {
        return  queue.peek();
    }

    /**
     * Returns the size of the queue
     * @return Integer size of the queue
     */
    public int Size() { return queue.size(); }
}
