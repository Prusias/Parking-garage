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

    public SimulationInfoController(SimulationInfoModel model) {super(model); }

    @Override
    protected <E extends Enum<E>> boolean event(View view, Enum<E> event_enum) {
        return false;
    }

}
