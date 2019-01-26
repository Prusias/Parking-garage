package nl.hanze.experience.simulation;

/**
 * Class used to contain modifiers for the simulation
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class Modifier {
    // Monday = 0, Sunday = 6
    private double[] weekdayModifier;
    // 00:00-01:00 = 0, 23:00-24:00 = 23
    private double[] hourModifier;

    /**
     * Initialize Modifier with default values
     */
    public Modifier() {
        weekdayModifier = new double[] { 0.8, 1, 1.1, 1, 1.5, 1.2, 0.5 };
        hourModifier = new double[] { 0.15, 0.1, 0.05, 0.05, 0.1, 0.15, 0.2, 0.4, 0.6, 1, 1, 1, 1.2, 1.2, 1.2, 1.2, 1, 1, 1, 1, 0.8, 0.6, 0.3, 0.2};
    }

    /**
     * Get the modifier for a day of the week
     * @param day day of the week
     * @return double modifier
     */
    public double getWeekdayModifier(int day) {
        return weekdayModifier[day];
    }
    /**
     * Set the modifier for a day of the week
     * @param day day of the week
     * @param modifier double modifier
     */
    public void setWeekdayModifier(int day, double modifier) {
        weekdayModifier[day] = modifier;
    }
    /**
     * Get the modifier for an hour on a day of the week
     * @param hour hour of the day
     * @return double modifier
     */
    public double getHourModifier(int hour) {
        return hourModifier[hour];
    }
    /**
     * Set the modifier for an hour on a day of the week
     * @param hour hour of the day
     * @param modifier double modifier
     */
    public void setHourModifier(int hour, double modifier) {
        hourModifier[hour] = modifier;
    }

    public double[] getWeekdayModifier() {
        return weekdayModifier;
    }
    public double[] getHourModifier() {
        return hourModifier;
    }
}
