package nl.hanze.experience.controllers;

import nl.hanze.experience.models.Garage;
import nl.hanze.experience.models.Model;
import nl.hanze.experience.views.CarParkView;
//import nl.hanze.experience.views.View;

/**
 * @author Mike van der Velde
 * @version 0.0.3
 * @since 0.0.3
 */
public class CarParkController extends Controller{
    //TODO: Implement a ViewModel @Steven ;p
    private Garage garage;
    private CarParkView carParkView;

    public Garage getGarage() {
        return garage;
    }
    public CarParkView getCarParkView() {
        return carParkView;
    }

    public CarParkController(Garage garage) {
        super();
        this.garage = garage;
        models.add(garage);
        garage.addListener(this);
        carParkView = new CarParkView(this);
        views.add(carParkView);
    }

    @Override
    public void listen(Model model) {
        super.listen(model);
        if (model instanceof Garage) {
            this.garage = (Garage) model;
            updateView();
        }
    }

    public void updateView() {
        carParkView.updateView();
    }
}
