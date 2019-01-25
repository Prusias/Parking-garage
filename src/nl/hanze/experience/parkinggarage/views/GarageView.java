package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.objects.ParkingSpot;
import nl.hanze.experience.parkinggarage.models.GarageModel;

import javax.swing.*;
import java.awt.*;

public class GarageView extends JPanelView {

    private Dimension size;
    private Image carParkImage;

    public GarageView() {
        size = new Dimension(0, 0);
        carParkImage = createImage(size.width, size.height);
    }

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

    private Color getColor(ParkingSpot parkingSpot) {
        // Is the spot reserved?
        if (parkingSpot.isReserved()) {
            // Has the spot got a vehicle on it?
            if (parkingSpot.getVehicle() == null) {
                return new Color(211, 255, 255);
            }
            return new Color(211, 124, 124);
        }
        // Is the spot a subscription spot?
        if (parkingSpot.isSubscriptionSpot()) {
            // Has the spot got a vehicle on it?
            if (parkingSpot.getVehicle() == null) {
                return new Color(150, 30, 30);
            }
            return new Color(255, 30, 30);
        }

        // Has the spot got a vehicle on it?
        if (parkingSpot.getVehicle() == null) {
            return new Color(188, 188, 188);
        } else {
            return new Color(68, 68, 68);
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
