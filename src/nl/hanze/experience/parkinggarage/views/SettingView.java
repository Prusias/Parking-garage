package nl.hanze.experience.parkinggarage.views;

import static nl.hanze.experience.parkinggarage.controllers.SettingController.EventId;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SettingModel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class SettingView extends JFrameView {

    private Container container;
    private JPanel jPanel;
    private JTextField amountOfFloorsInput;
    private JTextField amountOfRowsInput;
    private JTextField amountOfSpotsInput;
    private JTextField priceInEuroInput;

    public SettingView() {
        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        container = this.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        jPanel = new JPanel();
        container.add(jPanel);

        addFields();

        JButton saveButton = new JButton("Save");
        jPanel.add(saveButton);

        saveButton.addActionListener(e -> notifyController(EventId.SAVE));

        this.pack();
        this.setSize(400, 600);
    }

    @Override
    public void update(Model model) {
        if (!(model instanceof SettingModel)) {
            throw new IllegalArgumentException("Wrong model given to view.update");
        }
        SettingModel settingModel = (SettingModel) model;
        amountOfFloorsInput.setText(settingModel.getAmountOfFloors());
        amountOfRowsInput.setText(settingModel.getAmountOfRows());
        amountOfSpotsInput.setText(settingModel.getAmountOfSpots());
        priceInEuroInput.setText(settingModel.getPriceInEuro());
        if (settingModel.simulationHasStarted()) {
            amountOfFloorsInput.setEnabled(false);
            amountOfRowsInput.setEnabled(false);
            amountOfSpotsInput.setEnabled(false);
            priceInEuroInput.setEnabled(false);
        }
    }

    private void addFields() {
        jPanel.add(new JLabel("Amount of floors the garage has."));
        amountOfFloorsInput = new JTextField(30);
        jPanel.add(amountOfFloorsInput);

        jPanel.add(new JLabel("Amount of rows each floor has."));
        amountOfRowsInput = new JTextField(30);
        jPanel.add(amountOfRowsInput);

        jPanel.add(new JLabel("Amount of parking spots each row has."));
        amountOfSpotsInput = new JTextField(30);
        jPanel.add(amountOfSpotsInput);

        jPanel.add(new JLabel("The price of parking per 10 minutes in €."));
        priceInEuroInput = new JTextField(30);
        jPanel.add(priceInEuroInput);
    }

}