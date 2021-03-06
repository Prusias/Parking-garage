package nl.hanze.experience.parkinggarage.controllers;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SettingModel;
import nl.hanze.experience.parkinggarage.views.SettingView;

/**
 * The control class for the simulator settings where all the central control happens
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */



public class SettingController extends Controller {
    public enum EventId { SAVE }

    /**
     * Make new settings controller
     * @param model A model to control
     */
    public SettingController(SettingModel model) {
        super(model);
    }

    /**
     * Event handler to desice which event to execute
     * @param view View where event comes from
     * @param event_enum enum of event
     * @param <E>
     * @return
     */
    @Override
    protected <E extends Enum<E>> boolean event(View view, Enum<E> event_enum) {
        if (event_enum == SettingController.EventId.SAVE) {
            SettingModel settingModel = (SettingModel) model;
            SettingView settingView = (SettingView) view;
            settingModel.setAmountOfFloors(settingView.getAmountOfFloors());
            settingModel.setAmountOfRows(settingView.getAmountOfRows());
            settingModel.setAmountOfSpots(settingView.getAmountOfSpots());
            settingModel.setPriceInEuro(settingView.getPriceInEuro());
            settingModel.setReservationPrice(settingView.getReservationPrice());
            settingModel.setSeed(settingView.getSeed());

            settingModel.setBaseVehicleModifier(settingView.getBaseVehicleModifier());
            settingModel.setTicketVehicleModifier(settingView.getTicketVehicleModifier());
            settingModel.setSubscriptionVehicleModifier(settingView.getSubscriptionVehicleModifier());
            settingModel.setReservationVehicleModifier(settingView.getReservationVehicleModifier());
            settingModel.setRegularCarModifier(settingView.getRegularCarModifier());
            settingModel.setElectricCarModifier(settingView.getElectricCarModifier());
            settingModel.setMotorcycleModifier(settingView.getMotorcycleModifier());
            settingModel.setParkingDurationModifier(settingView.getParkingDurationModifier());
            settingModel.setReservationDurationModifier(settingView.getReservationDurationModifier());
            settingModel.setTicketQueueSizeModifier(settingView.getTicketQueueSizeModifier());
            settingModel.setTicketQueueMaxSize(settingView.getTicketQueueMaxSize());
            settingModel.setSubscriptionQueueSizeModifier(settingView.getSubscriptionQueueSizeModifier());
            settingModel.setSubscriptionQueueMaxSize(settingView.getSubscriptionQueueMaxSize());
            settingModel.setNeighbouringParkingSpotWeightModifier(settingView.getNeighbouringParkingSpotWeightModifier());
            settingModel.setSimulationSleepTime(settingView.getSimulationSleepTime());

            settingModel.setSubscriptionSpots(settingView.getSubscriptionSpots());
            settingModel.setElectricSpots(settingView.getElectricSpots());
            settingModel.setMotorcycleSpots(settingView.getMotorcycleSpots());
            settingModel.setFloorWeight(settingView.getFloorWeight());
            settingModel.setRowWeight(settingView.getRowWeight());
            settingModel.setSpotWeight(settingView.getSpotWeight());
            settingModel.setTicketQueueSpeed(settingView.getTicketQueueSpeed());
            settingModel.setSubscriptionQueueSpeed(settingView.getSubscriptionQueueSpeed());
            settingModel.setExitQueueSpeed(settingView.getExitQueueSpeed());
            settingModel.setAverageVehicleDurationInMinutes(settingView.getAverageVehicleDurationInMinutes());
            settingModel.setMaxVehicleDurationInMinutes(settingView.getMaxVehicleDurationInMinutes());
            settingModel.setMinVehicleDurationInMinutes(settingView.getMinVehicleDurationInMinutes());
            settingModel.setAverageReservationDurationInMinutes(settingView.getAverageReservationDurationInMinutes());
            settingModel.setMaxReservationDurationInMinutes(settingView.getMaxReservationDurationInMinutes());
            settingModel.setMinReservationDurationInMinutes(settingView.getMinReservationDurationInMinutes());

            settingModel.setReservationReservedMinutes(settingView.getReservationReservedMinutes());
            settingModel.setReservationKeptReservedMinutes(settingView.getReservationKeptReservedMinutes());
            settingModel.setAmountOfTicketQueues(settingView.getAmountOfTicketQueues());
            settingModel.setAmountOfSubscriptionQueues(settingView.getAmountOfSubscriptionQueues());
            settingModel.setAmountOfExitQueues(settingView.getAmountOfExitQueues());

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
