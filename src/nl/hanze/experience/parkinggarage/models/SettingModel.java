package nl.hanze.experience.parkinggarage.models;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.simulation.Simulation;

import java.util.Arrays;

/**
 * The model class for the settings where all the logical operations for the settings are handled
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class SettingModel extends Model {
    private Simulation simulation;

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public String getAmountOfFloors() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("amountOfFloors")
        );
    }
    public void setAmountOfFloors(String value) {
        simulation.getGarageModel().setGarageSetting("amountOfFloors", Integer.valueOf(value));
    }

    public String getAmountOfRows() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("amountOfRows")
        );
    }
    public void setAmountOfRows(String value) {
        simulation.getGarageModel().setGarageSetting("amountOfRows", Integer.valueOf(value));
    }

    public String getAmountOfSpots() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("amountOfSpots")
        );
    }
    public void setAmountOfSpots(String value) {
        simulation.getGarageModel().setGarageSetting("amountOfSpots", Integer.valueOf(value));
    }

    public String getPriceInEuro() {
        return Double.toString(
                (double)simulation.getGarageModel().getGarageSetting("priceInEuro")
        );
    }
    public void setPriceInEuro(String value) {
        simulation.getGarageModel().setGarageSetting("priceInEuro", Double.valueOf(value));
    }

    public String getSeed() {
        return Long.toString(simulation.getSeed());
    }
    public void setSeed(String value) {
        simulation.setSeed(Long.valueOf(value));
    }

    public String getWeekdayModifier(int day) {
        return Double.toString(simulation.getModifier().getWeekdayModifier(day));
    }
    public void setWeekdayModifier(int day, double modifier) {
        simulation.getModifier().setWeekdayModifier(day, modifier);
    }

    public String getHourModifier(int hour) {
        return Double.toString(simulation.getModifier().getHourModifier(hour));
    }
    public void setHourModifier(int hour, double modifier) {
        simulation.getModifier().setHourModifier(hour, modifier);
    }

    public String getBaseVehicleModifier(){
        return Double.toString(
                simulation.getModifier().getBaseVehicleModifier());
    }

    public void setBaseVehicleModifier(String modifier){
        simulation.getModifier().setBaseVehicleModifier(Double.valueOf(modifier));
    }

    public String getTicketVehicleModifier(){
        return Double.toString(
                simulation.getModifier().getTicketVehicleModifier());
    }

    public void setTicketVehicleModifier(String modifier){
        simulation.getModifier().setTicketVehicleModifier(Double.valueOf(modifier));
    }

    public String getSubscriptionVehicleModifier(){
        return Double.toString(
                simulation.getModifier().getSubscriptionVehicleModifier());
    }

    public void setSubscriptionVehicleModifier(String modifier){
        simulation.getModifier().setSubscriptionVehicleModifier(Double.valueOf(modifier));
    }

    public String getReservationVehicleModifier(){
        return Double.toString(
                simulation.getModifier().getReservationVehicleModifier());
    }

    public void setReservationVehicleModifier(String modifier){
        simulation.getModifier().setReservationVehicleModifier(Double.valueOf(modifier));
    }

    public String getRegularCarModifier(){
        return Double.toString(
                simulation.getModifier().getRegularCarModifier());
    }

    public void setRegularCarModifier(String modifier){
        simulation.getModifier().setRegularCarModifier(Double.valueOf(modifier));
    }

    public String getElectricCarModifier(){
        return Double.toString(
                simulation.getModifier().getElectricCarModifier());
    }

    public void setElectricCarModifier(String modifier){
        simulation.getModifier().setElectricCarModifier(Double.valueOf(modifier));
    }

    public String getMotorcycleModifier(){
        return Double.toString(
                simulation.getModifier().getMotorcycleModifier());
    }

    public void setMotorcycleModifier(String modifier){
        simulation.getModifier().setMotorcycleModifier(Double.valueOf(modifier));
    }

    public String getParkingDurationModifier(){
        return Double.toString(
                simulation.getModifier().getParkingDurationModifier());
    }

    public void setParkingDurationModifier(String modifier){
        simulation.getModifier().setParkingDurationModifier(Double.valueOf(modifier));
    }

    public String getReservationDurationModifier(){
        return Double.toString(
                simulation.getModifier().getReservationDurationModifier());
    }

    public void setReservationDurationModifier(String modifier){
        simulation.getModifier().setReservationDurationModifier(Double.valueOf(modifier));
    }

    public String getTicketQueueSizeModifier(){
        return Double.toString(
                simulation.getModifier().getTicketQueueSizeModifier());
    }

    public void setTicketQueueSizeModifier(String modifier){
        simulation.getModifier().setTicketQueueSizeModifier(Double.valueOf(modifier));
    }

    public String getTicketQueueMaxSize(){
        return Double.toString(
                simulation.getModifier().getTicketQueueMaxSize());
    }

    public void setTicketQueueMaxSize(String modifier){
        simulation.getModifier().setTicketQueueMaxSize(Double.valueOf(modifier));
    }

    public String getSubscriptionQueueSizeModifier(){
        return Double.toString(
                simulation.getModifier().getSubscriptionQueueSizeModifier());
    }

    public void setSubscriptionQueueSizeModifier(String modifier){
        simulation.getModifier().setSubscriptionQueueSizeModifier(Double.valueOf(modifier));
    }

    public String getSubscriptionQueueMaxSize(){
        return Double.toString(
                simulation.getModifier().getSubscriptionQueueMaxSize());
    }

    public void setSubscriptionQueueMaxSize(String modifier){
        simulation.getModifier().setSubscriptionQueueMaxSize(Double.valueOf(modifier));
    }

    public String getNeighbouringParkingSpotWeightModifier(){
        return Double.toString(
                simulation.getModifier().getNeighbouringParkingSpotWeightModifier());
    }

    public void setNeighbouringParkingSpotWeightModifier(String modifier){
        simulation.getModifier().setNeighbouringParkingSpotWeightModifier(Double.valueOf(modifier));
    }
    /**
     * Tests if the if the simulation has started
     * @return Boolean that is true if simulation is running
     */
    public boolean simulationHasStarted() {
        return simulation.hasStarted();
    }
}
