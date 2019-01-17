package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;

import javax.swing.*;
import java.awt.*;


public class SimulationInfoView extends View {

    private JLabel tickCountLabel;

    public SimulationInfoView() {
        this.setLayout(new FlowLayout());
        add(new JLabel("Amount of ticks: "));
        tickCountLabel = new JLabel();
        this.add(tickCountLabel);
    }

    @Override
    protected void update(Model model) {
        //TODO: Is this the right exception?
        if (!(model instanceof SimulationInfoModel)) {
            throw new IllegalArgumentException("Wrong model given to view.update");
        }
        SimulationInfoModel simulationInfoModel = (SimulationInfoModel) model;
        tickCountLabel.setBackground(Color.getHSBColor(100f, 100f, 100f));
        tickCountLabel.setText(Integer.toString((simulationInfoModel).getTickCount()));
    }

}
