package nl.hanze.experience.mvc;

/**
 * The controller class for the simulation
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public abstract class Controller {

    protected final Model model;

    /**
     * Constructor
     * @param model Model to be connected with the controller
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * Called from class View to notify controller of events
     * @param view View where event comes from
     * @param event_enum enum of event
     */
    void notify(View view, Enum event_enum) {
        if (!event(view, event_enum)) {
            throw new IllegalStateException("Event (event_id="+event_enum+") not handled.");
        }
    }

    /**
     * Eventhandler of controller
     * @param view View where event comes from
     * @param event_enum enum of event
     * @return true if event is handled
     */
    protected abstract <E extends Enum<E>> boolean event(View view, Enum<E> event_enum);


}
