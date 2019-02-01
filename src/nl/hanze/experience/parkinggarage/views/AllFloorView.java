package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.JPanelView;
import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.objects.ParkingSpot;
import nl.hanze.experience.objects.Vehicle;
import nl.hanze.experience.parkinggarage.models.GarageModel;

import java.awt.*;

/**
 * The view for all floors tab where all the graphical operations regarding the all floors tab happens
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class AllFloorView extends JPanelView {
    private Image floorImage;
    private Dimension size;

    /**
     * Make new view for all floors
     */
    public AllFloorView() {
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(Color.WHITE);
    }

    /**
     * Update the information in the view for all floors
     * @param model A Model with the new information
     */
    @Override
    public void update(Model model) {
        GarageModel garageModel = (GarageModel) model;
        size = new Dimension(this.getWidth(), this.getHeight());
        if (this.getWidth() <= 0) {
            size = new Dimension(1, 1);
        }
        floorImage = createImage(size.width, size.height);
        Graphics graphics = floorImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        int numberOfSpots = garageModel.getNumberOfSpots();
        for(int floor = 0; floor < garageModel.getNumberOfFloors(); floor++) {
            for(int row = 0; row < garageModel.getNumberOfRows(); row++) {
                for(int spot = 0; spot < numberOfSpots; spot++) {
                    ParkingSpot parkingSpot = garageModel.getParkingSpot(floor, row, spot);
                    Color color = getColor(parkingSpot);
                    drawPlace(graphics, color, floor, row, spot, numberOfSpots);
                }
            }
        }
        repaint();
        this.setPreferredSize(size);
    }

    /**
     * Overriden. The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
     */
    protected void paintComponent(Graphics g) {
        if (floorImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(floorImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(floorImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    // TODO: Handle SUB Type etc;
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
                if (parkingSpot.getVehicle().getType() == Vehicle.Type.ELECTRIC_CAR) {
                    return new Color(0, 100, 0);
                } else if (parkingSpot.getVehicle().getType() == Vehicle.Type.MOTORCYCLE) {
                    return new Color(255, 155, 50);
                } else {
                    return new Color(68, 68, 68);
                }
            }
        }
    }

    // TODO: Dynamic size?
    private void drawPlace(Graphics graphics, Color color, int floor, int row, int spot, int numberOfSpots) {
        int paddingX = 20;
        int paddingY = 20;
        int spotWidth = 20;
        int spotHeight = 10;
        int spacing = 80;
        // 2 * 11 * 20 + 20
        int floorHeight = floor * ((numberOfSpots + 1) * spotHeight);

        int x = (1 + (int)Math.floor(row * 0.5)) * spacing + (row % 2) * spotWidth - spacing + paddingX;
        int y = (floor >= 1 ? floorHeight : 0) + spot * spotHeight + paddingY;
        int width = spotWidth - 1;
        int height = spotHeight - 1;

        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
    }
}
