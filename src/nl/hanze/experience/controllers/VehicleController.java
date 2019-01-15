package nl.hanze.experience.controllers;

import nl.hanze.experience.Simulation;
import nl.hanze.experience.models.*;

import java.util.Random;

public class VehicleController extends Controller {




    private Simulation simulation;
    private Garage garage;



    public VehicleController(Simulation simulation) {

        this.simulation = simulation;
        garage = simulation.garage;
    }



//    public void update(Model model) {
//
//    }
}
