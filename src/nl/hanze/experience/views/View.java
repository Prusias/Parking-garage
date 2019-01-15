package nl.hanze.experience.views;

import nl.hanze.experience.controllers.Controller;

import javax.swing.*;

/**
 * @author Mike van der Velde
 * @version 0.0.3
 * @since 0.0.3
 */
public abstract class View extends JPanel {

    protected Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

//    public void setController(Controller controller) {
//        if (this.controller!=null) {
//            throw new IllegalStateException("Controller already set.");
//        }
//        this.controller = controller;
//    }

    //TODO: Change this?
    private void notifyController(int event_id) {
        if (controller==null) {
            throw new IllegalStateException("View does not have a controller.");
        }
        controller.listen(this, event_id);
    }
}
