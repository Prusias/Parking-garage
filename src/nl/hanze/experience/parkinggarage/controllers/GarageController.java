package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.Controller;
import nl.hanze.experience.mvc.View;
import nl.hanze.experience.parkinggarage.models.GarageModel;

/**
 * The control class for the garage where all the central control happens
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public class GarageController extends Controller {
    /**
     * Make new garage controller
     * @param model A model to control
     */
    public GarageController(GarageModel model) {super(model); }

    /**
     * Deside which event to execute
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
