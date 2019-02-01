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

    public String getReservationPrice() {
        return Double.toString(
                (double)simulation.getGarageModel().getGarageSetting("reservationPrice")
        );
    }

    public void setReservationPrice(String value) {
        simulation.getGarageModel().setGarageSetting("reservationPrice", Double.valueOf(value));
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

    public String getSubscriptionSpots() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("subscriptionSpots")
        );
    }
    public void setSubscriptionSpots(String value) {
        simulation.getGarageModel().setGarageSetting("subscriptionSpots", Integer.valueOf(value));
    }

    public String getElectricSpots() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("electricSpots")
        );
    }
    public void setElectricSpots(String value) {
        simulation.getGarageModel().setGarageSetting("electricSpots", Integer.valueOf(value));
    }

    public String getMotorcycleSpots() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("motorcycleSpots")
        );
    }
    public void setMotorcycleSpots(String value) {
        simulation.getGarageModel().setGarageSetting("motorcycleSpots", Integer.valueOf(value));
    }

    public String getFloorWeight() {
        return Double.toString(
                (double)simulation.getGarageModel().getGarageSetting("floorWeight")
        );
    }
    public void setFloorWeight(String value) {
        simulation.getGarageModel().setGarageSetting("floorWeight", Double.valueOf(value));
    }

    public String getRowWeight() {
        return Double.toString(
                (double)simulation.getGarageModel().getGarageSetting("rowWeight")
        );
    }
    public void setRowWeight(String value) {
        simulation.getGarageModel().setGarageSetting("rowWeight", Double.valueOf(value));
    }

    public String getSpotWeight() {
        return Double.toString(
                (double)simulation.getGarageModel().getGarageSetting("spotWeight")
        );
    }
    public void setSpotWeight(String value) {
        simulation.getGarageModel().setGarageSetting("spotWeight", Double.valueOf(value));
    }

    public String getTicketQueueSpeed() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("ticketQueueSpeed")
        );
    }
    public void setTicketQueueSpeed(String value) {
        simulation.getGarageModel().setGarageSetting("ticketQueueSpeed", Integer.valueOf(value));
    }

    public String getSubscriptionQueueSpeed() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("subscriptionQueueSpeed")
        );
    }
    public void setSubscriptionQueueSpeed(String value) {
        simulation.getGarageModel().setGarageSetting("ticketQueueSpeed", Integer.valueOf(value));
    }

    public String getExitQueueSpeed() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("exitQueueSpeed")
        );
    }
    public void setExitQueueSpeed(String value) {
        simulation.getGarageModel().setGarageSetting("exitQueueSpeed", Integer.valueOf(value));
    }

    public String getAverageVehicleDurationInMinutes() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("averageVehicleDurationInMinutes")
        );
    }
    public void setAverageVehicleDurationInMinutes(String value) {
        simulation.getGarageModel().setGarageSetting("averageVehicleDurationInMinutes", Integer.valueOf(value));
    }

    public String getMaxVehicleDurationInMinutes() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("maxVehicleDurationInMinutes")
        );
    }
    public void setMaxVehicleDurationInMinutes(String value) {
        simulation.getGarageModel().setGarageSetting("maxVehicleDurationInMinutes", Integer.valueOf(value));
    }

    public String getMinVehicleDurationInMinutes() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("minVehicleDurationInMinutes")
        );
    }
    public void setMinVehicleDurationInMinutes(String value) {
        simulation.getGarageModel().setGarageSetting("minVehicleDurationInMinutes", Integer.valueOf(value));
    }

    public String getAverageReservationDurationInMinutes() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("averageReservationDurationInMinutes")
        );
    }
    public void setAverageReservationDurationInMinutes(String value) {
        simulation.getGarageModel().setGarageSetting("averageReservationDurationInMinutes", Integer.valueOf(value));
    }

    public String getMaxReservationDurationInMinutes() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("maxReservationDurationInMinutes")
        );
    }
    public void setMaxReservationDurationInMinutes(String value) {
        simulation.getGarageModel().setGarageSetting("maxReservationDurationInMinutes", Integer.valueOf(value));
    }

    public String getMinReservationDurationInMinutes() {
        return Integer.toString(
                (int)simulation.getGarageModel().getGarageSetting("minReservationDurationInMinutes")
        );
    }
    public void setMinReservationDurationInMinutes(String value) {
        simulation.getGarageModel().setGarageSetting("minReservationDurationInMinutes", Integer.valueOf(value));
    }



    /**
     * Tests if the if the simulation has started
     * @return Boolean that is true if simulation is running
     */
    public boolean simulationHasStarted() {
        return simulation.hasStarted();
    }
}
