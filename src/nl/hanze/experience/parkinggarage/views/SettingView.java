package nl.hanze.experience.parkinggarage.views;

import static nl.hanze.experience.parkinggarage.controllers.SettingController.EventId;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SettingModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.Flow;

/**
 * The class for the settings view
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public class SettingView extends JFrameView {

    private Container container;
    private JPanel simulationSettings;

    private JPanel weekdayModifiers;
    private JTextField[] weekdayModifiersInput;

    private JPanel hourModifiers;
    private JTextField[] hourModifiersInput;

    private JPanel buttons;

    private JTextField amountOfFloorsInput;
    private JTextField amountOfRowsInput;
    private JTextField amountOfSpotsInput;
    private JTextField priceInEuroInput;
    private JTextField seedInput;

    /**
     *
     */
    public SettingView() {
        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(true);

        container = this.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Add the JPanel for the simulation settings
        GridLayout simulationSettingsGridLayout = new GridLayout(0, 2);
        simulationSettingsGridLayout.setHgap(10);
        simulationSettingsGridLayout.setVgap(10);

        simulationSettings = new JPanel();
        simulationSettings.setLayout(simulationSettingsGridLayout);
       // simulationSettings.setSize(600, 160);
        simulationSettings.setBorder(new EmptyBorder(10, 10, 10, 10));
        container.add(simulationSettings);

        container.add(new JSeparator());

        // Add the JPanel for weekday modifiers
        GridLayout weekdayModifiersGridLayout = new GridLayout(0, 7);
        weekdayModifiersGridLayout.setHgap(2);
        weekdayModifiersGridLayout.setVgap(2);

        weekdayModifiers = new JPanel();
        weekdayModifiers.setLayout(weekdayModifiersGridLayout);
        //weekdayModifiers.setSize(600, 40);
        weekdayModifiers.setBorder(new EmptyBorder(10, 10, 10, 10));
        //TODO: Why won't this left-align?
        container.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(
                new JLabel("Modifiers for each day of the week from monday till sunday:", SwingConstants.LEFT)
        ));
        container.add(weekdayModifiers);

        container.add(new JSeparator());

        // Add the JPanel for hour modifiers
        GridLayout hourModifiersGridLayout = new GridLayout(0, 8);
        hourModifiersGridLayout.setHgap(2);
        hourModifiersGridLayout.setVgap(2);

        hourModifiers = new JPanel();
        hourModifiers.setLayout(hourModifiersGridLayout);
        //hourModifiers.setSize(600, 40);
        hourModifiers.setBorder(new EmptyBorder(10, 10, 10, 10));
        //TODO: Why won't this left-align?
        container.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(
                new JLabel("Modifiers for each hour of the day from 0:00 till 7:00, 8:00 till 16:00, 17:00 till 23:00:", SwingConstants.LEFT)
        ));
        container.add(hourModifiers);

        container.add(new JSeparator());

        // Add the Save button
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
        for (int i = 0; i < 7; i++) {
            weekdayModifiersInput[i].setText(settingModel.getWeekdayModifier(i));
        }
        for (int i = 0; i < 24; i++) {
            hourModifiersInput[i].setText(settingModel.getHourModifier(i));
        }
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

        weekdayModifiersInput = new JTextField[7];
        for (int i = 0; i < 7; i++) {
            weekdayModifiersInput[i] = new JTextField(5);
            weekdayModifiers.add(weekdayModifiersInput[i]);
        }
        hourModifiersInput = new JTextField[24];
        for (int i = 0; i < 24; i++) {
            hourModifiersInput[i] = new JTextField(5);
            hourModifiers.add(hourModifiersInput[i]);
        }
    }

    public String getAmountOfFloors() {return amountOfFloorsInput.getText();}
    public String getAmountOfRows() {return amountOfRowsInput.getText();}
    public String getAmountOfSpots() {return amountOfSpotsInput.getText();}
    public String getPriceInEuro() {return priceInEuroInput.getText();}
    public String getSeed() {return seedInput.getText();}
    public String[] getWeekdayModifiers() {
        String[] output = new String[7];
        for (int i = 0; i < 7; i++) {
            output[i] = weekdayModifiersInput[i].getText();
        }
        return output;
    }
    public String[] getHourModifiers() {
        String[] output = new String[24];
        for (int i = 0; i < 24; i++) {
            output[i] = hourModifiersInput[i].getText();
        }
        return output;
    }

}