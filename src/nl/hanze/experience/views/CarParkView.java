package nl.hanze.experience.views;

import nl.hanze.experience.controllers.SimulatorController;
import nl.hanze.experience.controllers.VehicleController;
import nl.hanze.experience.models.Location;
import nl.hanze.experience.models.Vehicle;

import javax.swing.*;
import java.awt.*;

public class CarParkView extends JPanel {
    private SimulatorView simView;
    private SimulatorController simController;
    private VehicleController vehController;
    private Dimension size;
    private Image carParkImage;

    /**
     * Constructor for objects of class CarPark
     */
    public CarParkView(SimulatorView simulatorView) {
        this.simView = simulatorView;
        simController = simView.getSimulatorController();
        vehController = simController.getVehicleController();
        size = new Dimension(0, 0);
    }

    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }

    /**
     * Overriden. The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
     */
    public void paintComponent(Graphics g) {
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

    public void updateView() {
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < simController.getNumberOfFloors(); floor++) {
            for(int row = 0; row < simController.getNumberOfRows(); row++) {
                for(int place = 0; place < simController.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Vehicle vehicle = vehController.getVehicleAt(location);
                    Color color = vehicle == null ? Color.white : vehicle.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();
    }

    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}