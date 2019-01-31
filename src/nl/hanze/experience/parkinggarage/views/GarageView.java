package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.objects.ParkingSpot;
import nl.hanze.experience.objects.Vehicle;
import nl.hanze.experience.parkinggarage.models.GarageModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.TabbedPaneUI;
import java.awt.*;

public class GarageView extends JPanelView {

    private JTabbedPane jTabbedPane;
    private FloorView[] floorViews;
    private Dimension size;

    public GarageView() {
        this.setLayout(new GridLayout(1, 1));
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setBackground(Color.WHITE);
        this.add(jTabbedPane);
        size = new Dimension(600, 400);
        this.setBackground(Color.WHITE);
    }

    @Override
    public void update(Model model) {
        GarageModel garageModel = (GarageModel) model;

        if (floorViews == null) {
            initPanels(garageModel.getNumberOfFloors());
        }

        for(int floor = 0; floor < garageModel.getNumberOfFloors(); floor++) {
            floorViews[floor].update(garageModel);
        }
        // Create a new car park image if the size has changed.
//        if (!size.equals(getSize())) {
//            size = getSize();
//            carParkImage = createImage(size.width, size.height);
//        }
       // carParkImage = createImage(size.width, size.height);
        //TODO: Letter for ParkingSpot type
    }

    private void initPanels(int numberOfFloors) {
        floorViews = new FloorView[numberOfFloors];
        for(int floor = 0; floor < numberOfFloors; floor++) {
            floorViews[floor] = new FloorView(floor);
            jTabbedPane.addTab(("Floor: " + (floor + 1)), null, floorViews[floor]);
        }
    }

}
