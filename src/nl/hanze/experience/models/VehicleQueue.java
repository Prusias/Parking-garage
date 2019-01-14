package nl.hanze.experience.models;

import java.util.LinkedList;
import java.util.Queue;

public class VehicleQueue {
    private Queue<Vehicle> queue = new LinkedList<>();

    public boolean addVehicle(Vehicle vehicle) {
        return queue.add(vehicle);
    }

    public Vehicle removeVehicle() {
        return queue.poll();
    }

    public int vehiclesInQueue(){
    	return queue.size();
    }
}
