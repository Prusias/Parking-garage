package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.MenuModel;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class MenuController extends Controller {

    public static final int EVENT_ID_PAUSE = 1;
    public static final int EVENT_ID_RESUME = 2;
    public static final int EVENT_ID_SETTINGS = 3;

    public MenuController(MenuModel model) {
        super(model);
    }

    @Override
    protected boolean event(View view, int event_id) {
        MenuModel menuModel = (MenuModel) model;
        switch (event_id) {
            case EVENT_ID_PAUSE:
                menuModel.pauseSimulation();
                return true;
            case EVENT_ID_RESUME:
                menuModel.resumeSimulation();
                return true;
            case EVENT_ID_SETTINGS:

                return true;



        }
        return false;
    }
}
