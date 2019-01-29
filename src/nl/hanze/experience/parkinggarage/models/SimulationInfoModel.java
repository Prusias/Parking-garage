package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.simulation.Simulation;

import java.time.format.DateTimeFormatter;

/**
 * @author Mike van der Velde
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

    public void increaseTime() {
        simulation.getGarageModel().increaseTime(1);
        notifyView();
    }

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
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
        return simulation.getGarageModel().moneyMadeInEuro();
    }
}
