package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SettingModel;
import nl.hanze.experience.parkinggarage.views.SettingView;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */



public class SettingController extends Controller {
    public enum EventId { SAVE }
    public SettingController(SettingModel model) {
        super(model);
    }

    @Override
    protected <E extends Enum<E>> boolean event(View view, Enum<E> event_enum) {
        if (event_enum == SettingController.EventId.SAVE) {
            SettingModel settingModel = (SettingModel) model;
            SettingView settingView = (SettingView) view;
            settingModel.setAmountOfFloors(settingView.getAmountOfFloors());
            settingModel.setamountOfRows(settingView.getamountOfRows());
            settingModel.setAmountOfSpots(settingView.getamountOfSpots());
            settingModel.setPriceInEuro(settingView.getpriceInEuro());
            settingModel.setSeed(settingView.getSeed());
            return true;
        }
        return false;
    }
}
