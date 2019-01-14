package nl.hanze.experience.views;

import nl.hanze.experience.controllers.Controller;

import javax.swing.*;

public abstract class View extends JPanel {

    private Controller controller;

    public void setController(Controller controller) {
        if (this.controller!=null) {
            throw new IllegalStateException("Controller already set.");
        }
        this.controller = controller;
    }

    private void notifyController(int event_id) {
        if (controller==null) {
            throw new IllegalStateException("View does not have a controller.");
        }
        controller.listen(this, event_id);
    }
}
