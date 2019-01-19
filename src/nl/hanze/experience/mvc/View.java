package nl.hanze.experience.mvc;

import javax.swing.JPanel;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public interface View {
    //TODO: Should this be part of the constructor?
    /**
     * Attach controller to view
     * @param controller
     */
    void setController(Controller controller);

    /**
     * Notify controller of event, called by subclasses (concrete views)
     * @param event_id id of event
     */
    void notifyController(int event_id);

    /**
     * Notify from model. Called by class Model
     * @param model Model
     */
    default void notify(Model model) {
        update(model);
    }

    /**
     * Updatehandler
     * @param model Model
     */
    void update(Model model);

}
