package nl.hanze.experience.parkinggarage.views;

import static nl.hanze.experience.parkinggarage.controllers.SettingController.EventId;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SettingModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class SettingView extends JFrameView {

    private Container container;
    private JPanel simulationSettings;
    private JPanel buttons;
    private GridLayout gridLayout;
    private JTextField amountOfFloorsInput;
    private JTextField amountOfRowsInput;
    private JTextField amountOfSpotsInput;
    private JTextField priceInEuroInput;
    private JTextField seedInput;

    public SettingView() {
        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(true);

        container = this.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        gridLayout = new GridLayout(0, 2);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);

        simulationSettings = new JPanel();
        simulationSettings.setLayout(gridLayout);
        simulationSettings.setSize(600, 160);
        simulationSettings.setBorder(new EmptyBorder(10, 10, 10, 10));
        container.add(simulationSettings);

        container.add(new JSeparator());

        buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(buttons);

        addFields();

        JButton saveButton = new JButton("Save");
        buttons.add(saveButton);

        saveButton.addActionListener(e -> notifyController(EventId.SAVE));

        this.pack();

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
        seedInput.setText(settingModel.getSeed());
        if (settingModel.simulationHasStarted()) {
            amountOfFloorsInput.setEnabled(false);
            amountOfRowsInput.setEnabled(false);
            amountOfSpotsInput.setEnabled(false);
            priceInEuroInput.setEnabled(false);
            seedInput.setEnabled(false);
        }
    }

    private void addFields() {
        simulationSettings.add(new JLabel("Amount of floors the garage has."));
        amountOfFloorsInput = new JTextField(30);
        simulationSettings.add(amountOfFloorsInput);

        simulationSettings.add(new JLabel("Amount of rows each floor has."));
        amountOfRowsInput = new JTextField(30);
        simulationSettings.add(amountOfRowsInput);

        simulationSettings.add(new JLabel("Amount of parking spots each row has."));
        amountOfSpotsInput = new JTextField(30);
        simulationSettings.add(amountOfSpotsInput);

        simulationSettings.add(new JLabel("The price of parking per 10 minutes in €."));
        priceInEuroInput = new JTextField(30);
        simulationSettings.add(priceInEuroInput);

        simulationSettings.add(new JSeparator());
        simulationSettings.add(new JSeparator());

        simulationSettings.add(new JLabel("Seed for the simulation"));
        seedInput = new JTextField(30);
        simulationSettings.add(seedInput);
    }

    public String getAmountOfFloors() {return amountOfFloorsInput.getText();}
    public String getamountOfRows() {return amountOfRowsInput.getText();}
    public String getamountOfSpots() {return amountOfSpotsInput.getText();}
    public String getpriceInEuro() {return priceInEuroInput.getText();}
    public String getSeed() {return seedInput.getText();}

}