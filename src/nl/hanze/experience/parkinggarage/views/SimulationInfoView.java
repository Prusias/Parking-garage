package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;
import static nl.hanze.experience.parkinggarage.controllers.SimulationInfoController.*;


import javax.swing.*;
import java.awt.*;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class SimulationInfoView extends JPanelView {

    private JLabel tickCountLabel;
    private JLabel dateTimeLabel;

    public SimulationInfoView() {
        this.setLayout(new FlowLayout());
        add(new JLabel("Amount of ticks: "));
        tickCountLabel = new JLabel("0");
        this.add(tickCountLabel);
        add(new JLabel("Time: "));
        dateTimeLabel = new JLabel("00-00 00:00");
        this.add(dateTimeLabel);

//        JButton pauseButton = new JButton("Pause");
//        this.add(pauseButton);
//        pauseButton.addActionListener(e ->  notifyController(EVENT_ID_PAUSE));

//        JButton resumeButton = new JButton("Resume");
//        this.add(resumeButton);
//        resumeButton.addActionListener(e ->  notifyController(EVENT_ID_RESUME));
    }

    @Override
    public void update(Model model) {
        //TODO: Is this the right exception?
        if (!(model instanceof SimulationInfoModel)) {
            throw new IllegalArgumentException("Wrong model given to view.update");
        }
        SimulationInfoModel simulationInfoModel = (SimulationInfoModel) model;
        //tickCountLabel.setBackground(Color.getHSBColor(100f, 100f, 100f));
        tickCountLabel.setText(Integer.toString((simulationInfoModel).getTickCount()));
        dateTimeLabel.setText(simulationInfoModel.getFormattedTime());
    }

}
