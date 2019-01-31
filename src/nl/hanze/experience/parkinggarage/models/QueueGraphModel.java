package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.views.QueueGraphView;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.time.*;

import java.time.LocalDateTime;
import java.util.List;

public class QueueGraphModel extends Model{

   private DefaultCategoryDataset set;

    public QueueGraphModel() {

    }

    public void updateDataset(int ticket, int sub) {
        set = new DefaultCategoryDataset();
        set.addValue(ticket, "Ticket", "QueueSize");

    }

    public DefaultCategoryDataset getDataset() {

        return set;
    }
}
