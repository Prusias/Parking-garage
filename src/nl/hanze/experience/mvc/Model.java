package nl.hanze.experience.mvc;

import java.util.ArrayList;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public abstract class Model {

    private ArrayList<View> views = new ArrayList<>();

    /**
     * Add views to model to be notified by model.
     * @param view View to be added to list of models that will be notified.
     */
    public void addView(View view) {
        views.add(view);
    }

    /**
     * Notify views. Called by subclasses (concrete models)
     */
    public void notifyView() {
        for (View view : views) {
            view.notify(this);
        }
    }

}

