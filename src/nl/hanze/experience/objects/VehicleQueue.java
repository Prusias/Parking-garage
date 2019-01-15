package nl.hanze.experience.objects;

import java.util.LinkedList;
import java.util.Queue;

public class VehicleQueue {
    private Queue<Vehicle> queue;

    public VehicleQueue() {
        queue = new LinkedList<>();
    }

    public void add(Vehicle vehicle) {
        queue.add(vehicle);
    }
    public Vehicle poll() {
        return queue.poll();
    }
    public Vehicle peek() {
        return  queue.peek();
    }

}
