package nl.hanze.experience.simulation.reservations;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ReservationTimeQueue {
    private PriorityQueue<ReservationTime> queue;
    /**
     * make new reservation queue
     */
    public ReservationTimeQueue() {
        ReservationTimeComparator comparator = new ReservationTimeComparator();
        queue = new PriorityQueue<>(999, comparator);
    }

    /**
     * Add reservation to the reservation queue
     * @param reservation
     */
    public void add(ReservationTime reservation) {
        queue.add(reservation);
    }
    /**
     * Poll a reservation of the reservation queue
     * @return Polled reservation from the reservation queue
     */
    public ReservationTime poll() {
        return queue.poll();
    }
    /**
     * Shows the reservation at the top in the reservation queue
     * @return The reservation on the top in the reservation queue
     */
    public ReservationTime peek() {
        return  queue.peek();
    }
    private class ReservationTimeComparator implements Comparator<ReservationTime> {
        /**
         * Compare two reservations's time of arrival with each other
         * @param x Reservation to compare
         * @param y Reservation to compare
         * @return 1 if x arrives after y, -1 if x arrives before y and 0 if they arrive together
         */
        @Override
        public int compare(ReservationTime x, ReservationTime y) {
            if (x.getTime() < y.getTime()) {
                return -1;
            }
            if (x.getTime() > y.getTime()) {
                return 1;
            }
            return 0;
        }
    }



}