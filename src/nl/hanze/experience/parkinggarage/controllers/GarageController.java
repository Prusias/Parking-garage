package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.Controller;
import nl.hanze.experience.mvc.View;
import nl.hanze.experience.parkinggarage.models.GarageModel;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class GarageController extends Controller {

    public GarageController(GarageModel model) {super(model); }

    @Override
    protected <E extends Enum<E>> boolean event(View view, Enum<E> event_enum) {
        return false;
    }

}
