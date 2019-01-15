package nl.hanze.experience.controllers;

import java.util.ArrayList;
import nl.hanze.experience.models.Model;
import nl.hanze.experience.views.View;

/**
 * @author Mike van der Velde
 * @version 0.0.3
 * @since 0.0.3
 */
public abstract class Controller {

    protected ArrayList<Model> models;
    protected ArrayList<View> views;

    public Controller() {
        views = new ArrayList<>();
        models = new ArrayList<>();

//        for(Model m : models) {
//            m.addListener(this);
//        }
//        for(View v : views) {
//            v.setController(this);
//        }
    }

    public void listen(Model model) { }

    public void listen(View view, int event_id) { }

    //protected abstract void update(Model model);

}
