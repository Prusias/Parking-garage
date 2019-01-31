package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;
import nl.hanze.experience.parkinggarage.views.SimulationInfoView;

/**
 * The control class for the simulator information where all the central control for the simulation information happens
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class SimulationInfoController extends Controller {
    /**
     * Make new simulation information controller
     * @param model A model to control
     */
    public SimulationInfoController(SimulationInfoModel model) {super(model); }

    /**
     * Decide which event to execute
     * @param view View where event comes from
     * @param event_enum enum of event
     * @param <E>
     * @return
     */
    @Override
    protected <E extends Enum<E>> boolean event(View view, Enum<E> event_enum) {
        return false;
    }

}
