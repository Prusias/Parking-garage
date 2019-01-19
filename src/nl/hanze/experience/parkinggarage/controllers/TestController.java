package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.TestModel;
import nl.hanze.experience.parkinggarage.views.TestView;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class TestController extends Controller {

    public static final int EVENT_ID_TEST = 1;

    public TestController(TestModel model) {super(model); }

    @Override
    protected boolean event(View view, int event_id) {
        if (event_id == EVENT_ID_TEST) {
            TestModel testModel = (TestModel) model;
            TestView testView = (TestView) view;
            testModel.setAmountOfCars(testView.getAmountOfCars());
            return true;
        }
        return false;
    }

}
