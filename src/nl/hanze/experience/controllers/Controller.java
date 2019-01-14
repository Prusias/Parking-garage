package nl.hanze.experience.controllers;

import java.util.ArrayList;
import nl.hanze.experience.models.Model;
import nl.hanze.experience.views.View;

public abstract class Controller {

    private ArrayList<Model> models;
    private ArrayList<View> views;

    public Controller(Model model) {
        views = new ArrayList<>();
        models = new ArrayList<>();

        for(Model m : models) {
            m.addListener(this);
        }
        for(View v : views) {
            v.setController(this);
        }
    }

    public void listen(Model model) { }

    public void listen(View view, int event_id) { }

    protected abstract void update(Model model);

}
