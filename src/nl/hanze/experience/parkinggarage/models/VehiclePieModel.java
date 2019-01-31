package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.time.LocalDateTime;

/**
 * The model class for the vehicle graph where all the logical operation regarding the graph representing the vehicle happen
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public class VehiclePieModel extends Model {

    private DefaultPieDataset defaultPieDataset;

    /**
     * Make new vehicle graph model
     */
    public VehiclePieModel() {
        defaultPieDataset = new DefaultPieDataset();
        defaultPieDataset.insertValue(0, "Cars with a subscription", 0);
        defaultPieDataset.insertValue(1, "Cars with a reservation", 0);
        defaultPieDataset.insertValue(2, "Cars with a ticket", 0);
        defaultPieDataset.insertValue(3, "Electric cars with a ticket", 0);
        defaultPieDataset.insertValue(4, "Motorcycles with a ticket", 0);
    }

    public DefaultPieDataset getDefaultPieDataset() {
        return defaultPieDataset;
}

    /**
     * updates the information in the piechart model
     *
     */
    public void updatePieDataset(int regularCar, int electricCar, int motor, int sub, int res) {
        defaultPieDataset.setValue("Cars with a subscription", sub);
        defaultPieDataset.setValue("Cars with a reservation", res);
        defaultPieDataset.setValue("Cars with a ticket", regularCar);
        defaultPieDataset.setValue("Electric cars with a ticket", electricCar);
        defaultPieDataset.setValue("Motorcycles with a ticket", motor);
    }


}
