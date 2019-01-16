package nl.hanze.experience.objects;

import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * @author
 * @version 0.0.4
 * @since 0.0.4
 */
public class ReservationPriorityQueue {
    private PriorityQueue<Reservation> queue;

    public ReservationPriorityQueue() {
        ReservationComparator comparator = new ReservationComparator();
        queue = new PriorityQueue<>(999, comparator);
    }

    public void add(Reservation reservation) {
        queue.add(reservation);
    }
    public Reservation poll() {
        return queue.poll();
    }
    public Reservation peek() {
        return  queue.peek();
    }

    private class ReservationComparator implements Comparator<Reservation> {
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
}

//
//    EXAMPLE OF USING A PRIORITY QUEUE
//
//    ReservationPriorityQueue queue = new ReservationPriorityQueue();
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
