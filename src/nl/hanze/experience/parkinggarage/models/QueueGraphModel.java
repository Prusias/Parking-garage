package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import org.jfree.data.category.DefaultCategoryDataset;

public class QueueGraphModel extends Model{

   private DefaultCategoryDataset set;

    public QueueGraphModel() {

    }

    public void updateDataset(int ticketQueueSize, int subscriptionQueueSize, int reservationQueueSize) {
        set = new DefaultCategoryDataset();
        set.addValue(subscriptionQueueSize, "Subscription", "Amount of cars");
        set.addValue(ticketQueueSize, "TicketQueue", "Amount of cars");
        set.addValue(reservationQueueSize, "ReservationQueue", "Amount of reservations");
    }

    public DefaultCategoryDataset getDataset() {
        return set;
    }
}
