package nl.hanze.experience.models;

import java.util.ArrayList;
import nl.hanze.experience.controllers.Controller;

/**
 * @author Mike van der Velde
 * @version 0.0.3
 * @since 0.0.3
 */
public abstract class Model {

    /**
     * A list of all Controllers that are listening to changes to this Model
      */
    private ArrayList<Controller> listeners;

    /**
     * Constructor
     */
    public Model() {
        listeners = new ArrayList<>();
    }

    /**
     * Adds a controller to the list of controllers to be notified when the Model changes
     * @param c Controller that wants to 'listen' to this Model
     */
    public void addListener(Controller c) {
        listeners.add(c);
    }

    /**
     * Removes a controller from the list of controllers to be notified when the Model changes
     * @param c Controller that wants to stop 'listening' to this Model
     */
    public void removeListener(Controller c) {
        listeners.remove(c);
    }

    /**
     * Notifies all 'listeners' of the Model
     */
    private void notifyListeners() {
        for (Controller c : listeners) {
            c.listen(this);
        }
    }
            }
