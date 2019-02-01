package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.time.LocalDateTime;

/**
 * The model class for the Daily income graph where all the logical operation regarding the graph representing the daily income happen
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class DailyIncomeGraphModel extends Model {

    private TimeSeriesCollection xyDataset;
    private TimeSeries dailyIncome;

    /**
     * Make new vehicle graph model
     */
    public DailyIncomeGraphModel() {
        dailyIncome = new TimeSeries("Total amount of vehicles");

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(dailyIncome);

        xyDataset = dataset;
    }

    public TimeSeriesCollection getXYDataset() {
        return xyDataset;
    }

    /**
     * updates the information in the graph model
     * @param localDateTime The new time and date
     * @param income Total income of the last day
     */
    public void updateTimeSeries(LocalDateTime localDateTime, double income) {
        Hour hour = new Hour(localDateTime.getHour(), localDateTime.getDayOfMonth(), localDateTime.getMonth().getValue(), localDateTime.getYear());
        dailyIncome.add(hour, income);
    }

}
