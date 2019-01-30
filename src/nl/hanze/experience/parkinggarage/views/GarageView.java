package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.objects.ParkingSpot;
import nl.hanze.experience.objects.Vehicle;
import nl.hanze.experience.parkinggarage.models.GarageModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Class to create a garage view
 */
public class GarageView extends JPanelView {

    private Dimension size;
    private Image carParkImage;

    /**
     * Making new garage view
     */
    public GarageView() {
        size = new Dimension(0, 0);
        carParkImage = createImage(size.width, size.height);
        this.setBorder(new LineBorder(Color.RED ));
    }

    /**
     * Updating the information in the garage view
     * @param model Model that contains the new information
     */
    @Override
    public void update(Model model) {
        GarageModel garageModel = (GarageModel) model;
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        //TODO: Letter for ParkingSpot type
        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < garageModel.getNumberOfFloors(); floor++) {
            for(int row = 0; row < garageModel.getNumberOfRows(); row++) {
                for(int spot = 0; spot < garageModel.getNumberOfSpots(); spot++) {

                    ParkingSpot parkingSpot = garageModel.getParkingSpot(floor, row, spot);
                    Color color = getColor(parkingSpot);
                    drawPlace(graphics, color, floor, row, spot);

                }
            }
        }
        repaint();
    }

    // TODO: Handle SUB Type etc;

    /**
     * Get the color of the parking spot
     * @param parkingSpot to know its color
     * @return Parking spot color
     */
    private Color getColor(ParkingSpot parkingSpot) {
        // Is the spot reserved?
        if (parkingSpot.getPaymentType() == Vehicle.PaymentType.RESERVATION) {
            // Has the spot got a vehicle on it?
            if (parkingSpot.getVehicle() == null) {
                return new Color(211, 255, 255);
            }
            return new Color(100, 255, 255);
        }
        // Is the spot a subscription spot?
        else if (parkingSpot.getPaymentType() == Vehicle.PaymentType.SUBSCRIPTION) {
            // Has the spot got a vehicle on it?
            if (parkingSpot.getVehicle() == null) {
                return new Color(255, 30, 30);
            }
            //return new Color(0, 0, 0);
            return new Color(100, 30, 30);
        }
        // Is the spot for electric cars?
        else if (parkingSpot.getType() == Vehicle.Type.ELECTRIC_CAR) {
            if (parkingSpot.getVehicle() == null) {
                return new Color(100, 255, 100);

            } else {
                return new Color(0, 100, 0);
            }
        }
        // Is the spot for Motorcycles
        else if (parkingSpot.getType() == Vehicle.Type.MOTORCYCLE){
            if (parkingSpot.getVehicle() == null) {
                return new Color(255, 255, 100);

            } else {
                return new Color(255, 155, 50);
            }
        } else {
            // Has the spot got a vehicle on it?
            if (parkingSpot.getVehicle() == null) {
                return new Color(168, 168, 168);

            } else {
                return new Color(68, 68, 68);
            }
        }


    }

    protected void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }
    private void drawPlace(Graphics graphics, Color color, int floor, int row, int spot) {
        graphics.setColor(color);
        graphics.fillRect(
            floor * 260 + (1 + (int)Math.floor(row * 0.5)) * 75 + (row % 2) * 20,
            60 + spot * 10,
            20 - 1,
            10 - 1
        ); // TODO use dynamic size or constants
    }
}
