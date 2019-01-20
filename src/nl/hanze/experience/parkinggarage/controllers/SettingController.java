package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SettingModel;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class SettingController extends Controller {

    public SettingController(SettingModel model) {
        super(model);
    }

    @Override
    protected <E extends Enum<E>> boolean event(View view, Enum<E> event_enum) {
        return false;
    }
}
