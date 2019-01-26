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
            settingModel.setAmountOfRows(settingView.getAmountOfRows());
            settingModel.setAmountOfSpots(settingView.getAmountOfSpots());
            settingModel.setPriceInEuro(settingView.getPriceInEuro());
            settingModel.setSeed(settingView.getSeed());
            String[] weekDayModifiers = settingView.getWeekdayModifiers();
            for (int i = 0; i < 7; i++) {
                settingModel.setWeekdayModifier(i, Double.valueOf(weekDayModifiers[i]));
            }
            String[] hourModifiers = settingView.getHourModifiers();
            for (int i = 0; i < 24; i++) {
                settingModel.setHourModifier(i, Double.valueOf(hourModifiers[i]));
            }


            return true;
        }
        return false;
    }
}
