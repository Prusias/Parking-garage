package nl.hanze.experience.mvc;

import javax.swing.*;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public abstract class JPanelView extends JPanel implements View {
    private Controller controller;

    //TODO: Should this be part of the constructor?
    /**
     * Attach controller to view
     * @param controller
     */
    public void setController(Controller controller) {
        if (this.controller!=null) {
            throw new IllegalStateException("Controller already set.");
        }
        this.controller = controller;
    }

    /**
     * Notify controller of event, called by subclasses (concrete views)
     * @param event_id id of event
     */
    public void notifyController(int event_id) {
        if (controller==null) {
            throw new IllegalStateException("View does not have a controller.");
        }
        controller.notify(this, event_id);
    }

//   Is implemented as default
//    public void notify(Model model) {
//        update(model);
//    }

    /**
     * Updatehandler
     * @param model Model
     */
    public abstract void update(Model model);

}