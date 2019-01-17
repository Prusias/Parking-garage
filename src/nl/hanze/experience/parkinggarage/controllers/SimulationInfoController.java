package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;
import nl.hanze.experience.parkinggarage.views.SimulationInfoView;


public class SimulationInfoController extends Controller {

    public SimulationInfoController(SimulationInfoModel model) {super(model); }

    @Override
    protected boolean event(View view, int event_id) {
        return false;
    }

}
