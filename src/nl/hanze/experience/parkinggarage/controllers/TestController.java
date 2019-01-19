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

    public enum EventId { TEST }

    public TestController(TestModel model) {super(model); }

    @Override
    protected <E extends Enum<E>> boolean event(View view, Enum<E> event_enum) {
        EventId eventId = (EventId) event_enum;
        TestModel testModel = (TestModel) model;
        TestView testView = (TestView) view;

        if (eventId == EventId.TEST) {
            testModel.setAmountOfCars(testView.getAmountOfCars());
            return true;
        }
        return false;
    }

}
