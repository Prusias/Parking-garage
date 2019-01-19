package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.simulation.Simulation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class SimulationInfoModel extends Model {
    private Simulation simulation;
    private int tickCount;
    private LocalDateTime localDateTime = LocalDateTime.of(1, 1,1, 3, 0, 0);

    public int getTickCount() {
        return tickCount;
    }

    public void setTickCount(int tickCount) {
        this.tickCount = tickCount;
        //notifyView(); increaseTime is called directly after this function, So we don't want to update the view twice.
    }

    public void increaseTime(int minutes ) {
        localDateTime = localDateTime.plusMinutes(minutes);
        notifyView();
    }

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
        return formatter.format(localDateTime);
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
}
