package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.JPanelView;
import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.parkinggarage.models.GarageModel;

import javax.swing.*;
import java.awt.*;

/**
 * The view for the vehicle graph
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class GarageView extends JPanelView {

    private JTabbedPane jTabbedPane;
    private JPanelView[] floorViews;
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

        for(int floor = 0; floor < garageModel.getNumberOfFloors() + 1; floor++) {
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
        floorViews = new JPanelView[numberOfFloors + 1];
        floorViews[numberOfFloors] = new AllFloorView();
        JScrollPane scrollPane = new JScrollPane(floorViews[numberOfFloors],
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        jTabbedPane.addTab(("All Floors"), null, scrollPane);
//        jTabbedPane.addTab(("All Floors"), null, floorViews[numberOfFloors]);
        for(int floor = 0; floor < numberOfFloors; floor++) {
            floorViews[floor] = new FloorView(floor);
            jTabbedPane.addTab(("Floor: " + (floor + 1)), null, floorViews[floor]);
        }
    }

}
