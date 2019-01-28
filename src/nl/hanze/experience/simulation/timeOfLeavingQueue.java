package nl.hanze.experience.simulation;

import nl.hanze.experience.objects.Vehicle;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class timeOfLeavingQueue {
    private PriorityQueue<Vehicle> queue;

    public timeOfLeavingQueue() {
        VehicleComparator comparator = new VehicleComparator();
        queue = new PriorityQueue<Vehicle>(999, comparator);
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

    private class VehicleComparator implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle x, Vehicle y) {
            if (x.getTimeOfLeaving() < y.getTimeOfLeaving()) {
                return -1;
            }
            if (x.getTimeOfLeaving() > y.getTimeOfLeaving()) {
                return 1;
            }
            return 0;
        }
    }
}

//
//    EXAMPLE OF USING A PRIORITY QUEUE
//
//    ReservationsQueue queue = new ReservationsQueue();
//    Reservation rev1 = new Reservation(null, 10, 10);
//    Reservation rev2 = new Reservation(null, 20, 20);
//    Reservation rev3 = new Reservation(null, 30, 30);
//    Reservation rev4 = new Reservation(null, 40, 40);
//    Reservation rev5 = new Reservation(null, 50, 50);
//    Reservation rev6 = new Reservation(null, 60, 60);
//
//        queue.add(rev2);
//                queue.add(rev5);
//                queue.add(rev3);
//                queue.add(rev1);
//                queue.add(rev4);
//                queue.add(rev6);
//
//                for (int i = 0; i < 60; i++) {
//        Reservation peek = queue.peek();
//        if (peek.getTimeOfArrival() == i) {
//        Reservation poll = queue.poll();
//        System.out.println("Time of arrival: " + poll.getDuration());
//        }
//        System.out.println("Step: " + i);
//        }
