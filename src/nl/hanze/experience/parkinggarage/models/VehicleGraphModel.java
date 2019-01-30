package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.time.*;

import java.time.LocalDateTime;

public class VehicleGraphModel extends Model {

    private TimeSeriesCollection xyDataset;
    private TimeSeries totalVehicleTimeSeries;
    private TimeSeries subscriptionVehicleTimeSeries;
    private TimeSeries ticketVehicleTimeSeries;
    private TimeSeries reservationVehicleTimeSeries;

    public VehicleGraphModel() {
        totalVehicleTimeSeries = new TimeSeries("Total amount of vehicles");
        subscriptionVehicleTimeSeries = new TimeSeries("Amount of vehicles with a subscription");
        reservationVehicleTimeSeries = new TimeSeries("Amount of vehicles with a reservation");
        ticketVehicleTimeSeries = new TimeSeries("Amount of vehicles with a ticket");

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(totalVehicleTimeSeries);
        dataset.addSeries(subscriptionVehicleTimeSeries);
        dataset.addSeries(ticketVehicleTimeSeries);
        dataset.addSeries(reservationVehicleTimeSeries);

        xyDataset = dataset;
    }

    public TimeSeriesCollection getXYDataset() {
        return xyDataset;
    }

    public void updateTimeSeries(LocalDateTime localDateTime, int subValue, int ticValue, int resValue) {
        Hour hour = new Hour(localDateTime.getHour(), localDateTime.getDayOfMonth(), localDateTime.getMonth().getValue(), localDateTime.getYear());
        totalVehicleTimeSeries.add(hour, subValue + ticValue + resValue);
        subscriptionVehicleTimeSeries.add(hour, subValue);
        ticketVehicleTimeSeries.add(hour, ticValue);
        reservationVehicleTimeSeries.add(hour, resValue);
    }


}
