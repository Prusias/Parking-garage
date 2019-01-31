package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import org.jfree.data.category.DefaultCategoryDataset;

public class QueueGraphModel extends Model{

   private DefaultCategoryDataset set;

    public QueueGraphModel() {

    }

    public void updateDataset(int ticketQueueSize, int subscriptionQueueSize, int reservationQueueSize) {
        set = new DefaultCategoryDataset();
        set.addValue(subscriptionQueueSize, "Amount of cars with a subscription", "");
        set.addValue(ticketQueueSize, "Amount of cars with a ticket", "");
        set.addValue(reservationQueueSize, "Amount of reservations", "");
    }

    public DefaultCategoryDataset getDataset() {
        return set;
    }
}
