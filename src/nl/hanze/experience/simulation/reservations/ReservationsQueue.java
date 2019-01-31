package nl.hanze.experience.simulation.reservations;

import nl.hanze.experience.objects.Reservation;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Class to make a reservation queue
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class ReservationsQueue {
    private PriorityQueue<Reservation> queue;
    /**
     * make new reservation queue
     */
    public ReservationsQueue() {
        ReservationComparator comparator = new ReservationComparator();
        queue = new PriorityQueue<>(999, comparator);
    }

    /**
     * Add reservation to the reservation queue
     * @param reservation
     */
    public void add(Reservation reservation) {
        queue.add(reservation);
    }
    /**
     * Poll a reservation of the reservation queue
     * @return Polled reservation from the reservation queue
     */
    public Reservation poll() {
        return queue.poll();
    }
    /**
     * Shows the reservation at the top in the reservation queue
     * @return The reservation on the top in the reservation queue
     */
    public Reservation peek() {
        return  queue.peek();
    }
    private class ReservationComparator implements Comparator<Reservation> {
        /**
         * Compare two reservations's time of arrival with each other
         * @param x Reservation to compare
         * @param y Reservation to compare
         * @return 1 if x arrives after y, -1 if x arrives before y and 0 if they arrive together
         */
        @Override
        public int compare(Reservation x, Reservation y) {
            if (x.getTimeOfArrival() < y.getTimeOfArrival()) {
                return -1;
            }
            if (x.getTimeOfArrival() > y.getTimeOfArrival()) {
                return 1;
            }
            return 0;
        }
    }
    public int size() {
        return queue.size();
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
