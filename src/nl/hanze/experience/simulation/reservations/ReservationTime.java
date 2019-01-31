package nl.hanze.experience.simulation.reservations;

import nl.hanze.experience.objects.Reservation;

/**
 * Class used to keep track of reservations, reserving spots and checking if people showed up.
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.5
 * @since 0.0.5
 */
public class ReservationTime {
    private Reservation reservation;
    private int time;

    public ReservationTime(Reservation reservation, int time) {
        this.reservation = reservation;
        this.time = time;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public int getTime() {
        return time;
    }
}
