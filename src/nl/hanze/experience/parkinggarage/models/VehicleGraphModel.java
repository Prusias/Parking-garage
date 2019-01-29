package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.time.*;

import java.time.LocalDateTime;

public class VehicleGraphModel extends Model {

    private XYDataset xyDataset;
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

    public XYDataset getXYDataset() {
        return xyDataset;
    }

    public void updateTotalVehicleTimeSeries(LocalDateTime localDateTime, int value) {
        totalVehicleTimeSeries.add(getHour(localDateTime), value);
    }
    public void updateSubscriptionVehicleTimeSeries(LocalDateTime localDateTime, int value) {
        subscriptionVehicleTimeSeries.add(getHour(localDateTime), value);
    }
    public void updateTicketVehicleTimeSeries(LocalDateTime localDateTime, int value) {
        ticketVehicleTimeSeries.add(getHour(localDateTime), value);
    }
    public void updateReservationVehicleTimeSeries(LocalDateTime localDateTime, int value) {
        reservationVehicleTimeSeries.add(getHour(localDateTime), value);
    }

    private Hour getHour(LocalDateTime localDateTime) {
        return new Hour(localDateTime.getHour(), localDateTime.getDayOfMonth(), localDateTime.getMonth().getValue(), localDateTime.getYear());
    }


}
