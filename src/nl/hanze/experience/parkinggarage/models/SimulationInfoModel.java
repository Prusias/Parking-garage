package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.simulation.Simulation;

import java.time.format.DateTimeFormatter;

/**
 * The simulation information model where all the logical operations of the simulation information are made
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class SimulationInfoModel extends Model {
    private Simulation simulation;
    private int tickCount;

    public int getTickCount() {
        return tickCount;
    }

    public void setTickCount(int tickCount) {
        this.tickCount = tickCount;
        //notifyView(); increaseTime is called directly after this function, So we don't want to update the view twice.
    }

    /**
     * Increases the time in the simulation by one minute
     */
    public void increaseTime() {
        simulation.getGarageModel().increaseTime(1);
        notifyView();
    }

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm");
        return formatter.format(simulation.getGarageModel().getLocalDateTime());
    }

//    public Simulation getSimulation() {
//        if (simulation == null) {
//            throw new IllegalStateException("Simulation has not been set");
//        }
//        return simulation;
//    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
    public void pauseSimulation() {
        if (simulation == null) {
            throw new IllegalStateException("Simulation has not been set");
        }
        simulation.pause();
    }
    public void resumeSimulation() {
        if (simulation == null) {
            throw new IllegalStateException("Simulation has not been set");
        }
        simulation.resume();
    }

    public int getSubscriptionQueueSize() {
        return simulation.getGarageModel().getSubscriptionQueueSize();
    }
    public int getTicketQueueSize() {
        return simulation.getGarageModel().getTicketQueueSize();
    }
    public double getMoneyMade() {
        return  simulation.getGarageModel().moneyMadeInEuro();
    }

    public double getPotentialMoney(){
        int avgTimeBlock = (int)simulation.getGarageModel().getGarageSetting("averageVehicleDurationInMinutes") / 10;
        return  simulation.getGarageModel().getAmountOfPayingVehicles() * (simulation.getGarageModel().getPriceInEuro() * avgTimeBlock );

    }

    public double getRevenueYesterday() {
        return simulation.getGarageModel().getRevenueYesterday();
    }

    public int getVehiclesDrivenPast() {
        return simulation.getVehiclesDrivenPast();
    }
}
