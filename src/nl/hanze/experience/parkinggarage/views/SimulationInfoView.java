package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.JPanelView;
import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.parkinggarage.models.SimulationInfoModel;

import javax.swing.*;
import java.awt.*;

/**
 * The view class the information over the simulator progress where the graphical operations regarding the simulator progress happens
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class SimulationInfoView extends JPanelView {

    private JLabel tickCountLabel;
    private JLabel dateTimeLabel;
    private JLabel subscriptionQueueSize;
    private JLabel ticketQueueSize;
    private JLabel vehiclesDrivenPast;
    private JLabel moneyMade;
    private JLabel revenue;

    /**
     * Make new simulation information view
     */
    public SimulationInfoView() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.WHITE);
        this.setMaximumSize( new Dimension(Integer.MAX_VALUE, 100));
        add(new JLabel("Amount of ticks: "));

        tickCountLabel = new JLabel("0");
        this.add(tickCountLabel);

        add(new JLabel("Time: "));
        dateTimeLabel = new JLabel("2019-00-00 00:00");
        this.add(dateTimeLabel);

        add(new JLabel("SubQueue Length: "));
        subscriptionQueueSize = new JLabel("0");
        this.add(subscriptionQueueSize);

        add(new JLabel("TicketQueue Length: "));
        ticketQueueSize = new JLabel("0");
        this.add(ticketQueueSize);

        add(new JLabel("Vehicles that drove past: "));
        vehiclesDrivenPast = new JLabel("0");
        this.add(vehiclesDrivenPast);

        add(new JLabel("Money made:  "));
        moneyMade = new JLabel("0");
        this.add(moneyMade);

        add(new JLabel("Revenue Yesterday:  "));
        revenue = new JLabel("0");
        this.add(revenue);



//        JButton pauseButton = new JButton("Pause");
//        this.add(pauseButton);
//        pauseButton.addActionListener(e ->  notifyController(EVENT_ID_PAUSE));

//        JButton resumeButton = new JButton("Resume");
//        this.add(resumeButton);
//        resumeButton.addActionListener(e ->  notifyController(EVENT_ID_RESUME));
    }

    /**
     * Update the information of the simulation progress
     * @param model Model with the new information
     */
    @Override
    public void update(Model model) {
        //TODO: Is this the right exception?
        if (!(model instanceof SimulationInfoModel)) {
            throw new IllegalArgumentException("Wrong model given to view.update");
        }
        SimulationInfoModel simulationInfoModel = (SimulationInfoModel) model;
        //tickCountLabel.setBackground(Color.getHSBColor(100f, 100f, 100f));
        tickCountLabel.setText("" + (simulationInfoModel).getTickCount());
        dateTimeLabel.setText(simulationInfoModel.getFormattedTime());
        subscriptionQueueSize.setText("" + simulationInfoModel.getSubscriptionQueueSize());
        ticketQueueSize.setText("" + simulationInfoModel.getTicketQueueSize());
        vehiclesDrivenPast.setText("" + simulationInfoModel.getVehiclesDrivenPast());
        moneyMade.setText(" €" + simulationInfoModel.getMoneyMade() + " (+€"  + simulationInfoModel.getPotentialMoney() + ")");
        revenue.setText("€" + simulationInfoModel.getRevenueYesterday());
    }

}
