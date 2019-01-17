package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.Model;

public class SimulationInfoModel extends Model {

    private int tickCount;

    public int getTickCount() {
        return tickCount;
    }

    public void setTickCount(int tickCount) {
        this.tickCount = tickCount;
        notifyView();
    }
}
