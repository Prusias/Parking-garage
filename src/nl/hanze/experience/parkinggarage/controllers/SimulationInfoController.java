package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;
import nl.hanze.experience.parkinggarage.views.SimulationInfoView;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class SimulationInfoController extends Controller {

    public static final int EVENT_ID_PAUSE = 1;
    public static final int EVENT_ID_RESUME = 2;

    public SimulationInfoController(SimulationInfoModel model) {super(model); }

    @Override
    protected boolean event(View view, int event_id) {
        if (event_id == EVENT_ID_PAUSE) {
            SimulationInfoModel simulationInfoModel = (SimulationInfoModel) model;
            simulationInfoModel.pauseSimulation();
            return true;
        } else if (event_id == EVENT_ID_RESUME) {
            SimulationInfoModel simulationInfoModel = (SimulationInfoModel) model;
            simulationInfoModel.resumeSimulation();
            return true;
        }
        return false;
    }

}
