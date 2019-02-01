package nl.hanze.experience.parkinggarage.views;

import static nl.hanze.experience.parkinggarage.controllers.SettingController.EventId;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.SettingModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.Flow;

/**
 * The class for the settings view where the graphical operations for regarding the settings menu happens
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class SettingView extends JFrameView {

    private Container container;
    private JPanel simulationSettings;
    private JPanel modifierSettings;
    private JPanel advancedSettings;

    private JPanel panel;
    private JPanel modifierPanel;
    private JPanel advancedPanel;

    private JTabbedPane tabbedPane;

    private JPanel weekdayModifiers;
    private JTextField[] weekdayModifiersInput;

    private JPanel hourModifiers;
    private JTextField[] hourModifiersInput;

    private JTextField baseVehicleModifier;
    private JTextField ticketVehicleModifier;
    private JTextField subscriptionVehicleModifier;
    private JTextField reservationVehicleModifier;
    private JTextField regularCarModifier;
    private JTextField electricCarModifier;
    private JTextField motorcyleModifier;
    private JTextField parkingDurationModifier;
    private JTextField reservationDurationModifier;
    private JTextField ticketQueueSizeModifier;
    private JTextField ticketQueueMaxSize;
    private JTextField subscriptionQueueSizeModifier;
    private JTextField subscriptionQueueMaxSize;
    private JTextField neighbouringParkingSPotWeightModifier;

    private JTextField averageVehicleDurationInMinutes;
    private JTextField maxVehicleDurationInMinutes;
    private JTextField minVehicleDurationInMinutes;
    private JTextField averageReservationDurationInMinutes;
    private JTextField maxReservationDurationInMinutes;
    private JTextField minReservationDurationInMinutes;

    private JTextField ticketQueueSpeed;
    private JTextField subscriptionQueueSpeed;
    private JTextField exitQueueSpeed;

    private JTextField floorWeight;
    private JTextField rowWeight;
    private JTextField spotWeight;

    private JTextField subscriptionSpots;
    private JTextField electricSpots;
    private JTextField motorcycleSpots;

    private JPanel buttons;
    private JPanel modifierButtons;
    private JPanel advancedButtons;

    private JTextField amountOfFloorsInput;
    private JTextField amountOfRowsInput;
    private JTextField amountOfSpotsInput;
    private JTextField priceInEuroInput;
    private JTextField reservationPrice;
    private JTextField seedInput;

    /**
     * Making a new settings view
     */
    public SettingView() {
        this.setBackground(Color.WHITE);
        new GridLayout(1, 1);
        panel = new JPanel();
        modifierPanel = new JPanel();
        advancedPanel = new JPanel();
        tabbedPane = new JTabbedPane();


        tabbedPane.addTab("Settings", panel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Modifiers", modifierPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Advanced", advancedPanel);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);


        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(true);

        container = this.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        modifierPanel.setLayout(new BoxLayout(modifierPanel, BoxLayout.Y_AXIS));
        advancedPanel.setLayout(new BoxLayout(advancedPanel, BoxLayout.Y_AXIS));

        container.add(tabbedPane);

        // Add the JPanel for the simulation settings
        GridLayout simulationSettingsGridLayout = new GridLayout(0, 2);
        GridLayout modifierSettingsGridLayout = new GridLayout(0,2);
        GridLayout advancedSettingsGridLayout = new GridLayout(0,2);
        simulationSettingsGridLayout.setHgap(10);
        simulationSettingsGridLayout.setVgap(10);
        modifierSettingsGridLayout.setHgap(10);
        modifierSettingsGridLayout.setVgap(10);
        advancedSettingsGridLayout.setHgap(10);
        advancedSettingsGridLayout.setVgap(10);

        simulationSettings = new JPanel();
        modifierSettings = new JPanel();
        advancedSettings = new JPanel();

        simulationSettings.setLayout(simulationSettingsGridLayout);
        modifierSettings.setLayout(modifierSettingsGridLayout);
        advancedSettings.setLayout(advancedSettingsGridLayout);

       // simulationSettings.setSize(600, 160);
        simulationSettings.setBorder(new EmptyBorder(10, 10, 10, 10));
        modifierSettings.setBorder(new EmptyBorder(10, 10, 10, 10));
        advancedSettings.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel.add(simulationSettings);
        modifierPanel.add(modifierSettings);
        advancedPanel.add(advancedSettings);

        panel.add(new JSeparator());
        modifierPanel.add(new JSeparator());
        advancedPanel.add(new JSeparator());

        // Add the JPanel for weekday modifiers
        GridLayout weekdayModifiersGridLayout = new GridLayout(0, 7);
        weekdayModifiersGridLayout.setHgap(2);
        weekdayModifiersGridLayout.setVgap(2);

        weekdayModifiers = new JPanel();
        weekdayModifiers.setLayout(weekdayModifiersGridLayout);
        //weekdayModifiers.setSize(600, 40);
        weekdayModifiers.setBorder(new EmptyBorder(10, 10, 10, 10));
        //TODO: Why won't this left-align?
        panel.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(
                new JLabel("Modifiers for each day of the week:", SwingConstants.LEFT)
        ));
        panel.add(weekdayModifiers);

        panel.add(new JSeparator());

        // Add the JPanel for hour modifiers
        GridLayout hourModifiersGridLayout = new GridLayout(0, 16);
        hourModifiersGridLayout.setHgap(2);
        hourModifiersGridLayout.setVgap(2);

        hourModifiers = new JPanel();
        hourModifiers.setLayout(hourModifiersGridLayout);
        //hourModifiers.setSize(600, 40);
        hourModifiers.setBorder(new EmptyBorder(10, 10, 10, 10));
        //TODO: Why won't this left-align?
        panel.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(
                new JLabel("Modifiers for each hour of the day:", SwingConstants.LEFT)
        ));
        panel.add(hourModifiers);

        panel.add(new JSeparator());

        // Add the Save button
        buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(buttons);

        modifierButtons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
        modifierPanel.add(modifierButtons);

        advancedButtons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
        advancedPanel.add(advancedButtons);

        addFields();

        JButton saveButton = new JButton("Save");
        buttons.add(saveButton);

        JButton advSaveButton = new JButton("Save");
        modifierButtons.add(advSaveButton);

        JButton initSaveButton = new JButton("Save");
        advancedButtons.add(initSaveButton);

        saveButton.addActionListener(e -> notifyController(EventId.SAVE));
        advSaveButton.addActionListener(e -> notifyController(EventId.SAVE));
        initSaveButton.addActionListener(e -> notifyController(EventId.SAVE));

        this.pack();

    }

    /**
     * Update the information in the settings view
     * @param model Model That contains new information
     */
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
        reservationPrice.setText(settingModel.getReservationPrice());
        seedInput.setText(settingModel.getSeed());

        baseVehicleModifier.setText(settingModel.getBaseVehicleModifier());
        ticketVehicleModifier.setText(settingModel.getTicketVehicleModifier());
        subscriptionVehicleModifier.setText(settingModel.getSubscriptionVehicleModifier());
        reservationVehicleModifier.setText(settingModel.getReservationVehicleModifier());
        regularCarModifier.setText(settingModel.getRegularCarModifier());
        electricCarModifier.setText(settingModel.getElectricCarModifier());
        motorcyleModifier.setText(settingModel.getMotorcycleModifier());
        parkingDurationModifier.setText(settingModel.getParkingDurationModifier());
        reservationDurationModifier.setText(settingModel.getReservationDurationModifier());
        ticketQueueSizeModifier.setText(settingModel.getTicketQueueSizeModifier());
        ticketQueueMaxSize.setText(settingModel.getTicketQueueMaxSize());
        subscriptionQueueSizeModifier.setText(settingModel.getSubscriptionQueueSizeModifier());
        subscriptionQueueMaxSize.setText(settingModel.getSubscriptionQueueMaxSize());
        neighbouringParkingSPotWeightModifier.setText(settingModel.getNeighbouringParkingSpotWeightModifier());

        subscriptionSpots.setText(settingModel.getSubscriptionSpots());
        electricSpots.setText(settingModel.getElectricSpots());
        motorcycleSpots.setText(settingModel.getMotorcycleSpots());
        floorWeight.setText(settingModel.getFloorWeight());
        rowWeight.setText(settingModel.getRowWeight());
        spotWeight.setText(settingModel.getSpotWeight());
        ticketQueueSpeed.setText(settingModel.getTicketQueueSpeed());
        subscriptionQueueSpeed.setText(settingModel.getSubscriptionQueueSpeed());
        exitQueueSpeed.setText(settingModel.getExitQueueSpeed());
        averageVehicleDurationInMinutes.setText(settingModel.getAverageVehicleDurationInMinutes());
        maxVehicleDurationInMinutes.setText(settingModel.getMaxVehicleDurationInMinutes());
        minVehicleDurationInMinutes.setText(settingModel.getMinVehicleDurationInMinutes());
        averageReservationDurationInMinutes.setText(settingModel.getAverageReservationDurationInMinutes());
        maxReservationDurationInMinutes.setText(settingModel.getMaxReservationDurationInMinutes());
        minReservationDurationInMinutes.setText(settingModel.getMinReservationDurationInMinutes());

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
            subscriptionSpots.setEnabled(false);
            electricSpots.setEnabled(false);
            motorcycleSpots.setEnabled(false);
            floorWeight.setEnabled(false);
            rowWeight.setEnabled(false);
            spotWeight.setEnabled(false);
            ticketQueueSpeed.setEnabled(false);
            subscriptionQueueSpeed.setEnabled(false);
            exitQueueSpeed.setEnabled(false);
        }
    }

    /**
     * Adding fields to the settings view
     */
    private void addFields() {
        String[] weekday = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        simulationSettings.add(new JLabel("Simulation seed:"));
        seedInput = new JTextField(30);
        simulationSettings.add(seedInput);

        simulationSettings.add(new JSeparator());
        simulationSettings.add(new JSeparator());

        simulationSettings.add(new JLabel("Number of floors:"));
        amountOfFloorsInput = new JTextField(8);
        simulationSettings.add(amountOfFloorsInput);

        simulationSettings.add(new JLabel("Number of rows:"));
        amountOfRowsInput = new JTextField(8);
        simulationSettings.add(amountOfRowsInput);

        simulationSettings.add(new JLabel("Number of parking spots:"));
        amountOfSpotsInput = new JTextField(8);
        simulationSettings.add(amountOfSpotsInput);

        simulationSettings.add(new JLabel("The price per 10 minutes in €:"));
        priceInEuroInput = new JTextField(8);
        simulationSettings.add(priceInEuroInput);

        simulationSettings.add(new JLabel("Reservation cost in €:"));
        reservationPrice = new JTextField(8);
        simulationSettings.add(reservationPrice);

        weekdayModifiersInput = new JTextField[7];
        for (int i = 0; i < 7; i++) {
            weekdayModifiers.add(new JLabel(weekday[i], SwingConstants.LEFT));
        }
        for (int i = 0; i < 7; i++) {
            weekdayModifiersInput[i] = new JTextField(5);
            weekdayModifiers.add(weekdayModifiersInput[i]);
        }
        hourModifiersInput = new JTextField[24];
        for (int i = 0; i < 24; i++) {
            hourModifiers.add(new JLabel(i+":00", SwingConstants.RIGHT));
            hourModifiersInput[i] = new JTextField(5);
            hourModifiers.add(hourModifiersInput[i]);
        }

        modifierSettings.add(new JLabel("Modifier for all vehicles:"));
        baseVehicleModifier = new JTextField(8);
        modifierSettings.add(baseVehicleModifier);

        modifierSettings.add(new JLabel("Modifier for any ticket vehicles:"));
        ticketVehicleModifier = new JTextField(8);
        modifierSettings.add(ticketVehicleModifier);

        modifierSettings.add(new JLabel("Modifier for vehicles with a subscription:"));
        subscriptionVehicleModifier = new JTextField(8);
        modifierSettings.add(subscriptionVehicleModifier);

        modifierSettings.add(new JLabel("Modifier for vehicles with a reservation:"));
        reservationVehicleModifier = new JTextField(8);
        modifierSettings.add(reservationVehicleModifier);

        modifierSettings.add(new JLabel("Modifier for cars:"));
        regularCarModifier = new JTextField(8);
        modifierSettings.add(regularCarModifier);

        modifierSettings.add(new JLabel("Modifier for electric cars:"));
        electricCarModifier = new JTextField(8);
        modifierSettings.add(electricCarModifier);

        modifierSettings.add(new JLabel("Modifier for motorcylces:"));
        motorcyleModifier = new JTextField(8);
        modifierSettings.add(motorcyleModifier);

        modifierSettings.add(new JLabel("Parking duration modifier for normal tickets:"));
        parkingDurationModifier = new JTextField(8);
        modifierSettings.add(parkingDurationModifier);

        modifierSettings.add(new JLabel("Parking duration modifier for reservation tickets"));
        reservationDurationModifier = new JTextField(8);
        modifierSettings.add(reservationDurationModifier);

        modifierSettings.add(new JLabel("Cars start leaving is queue is this long:"));
        ticketQueueSizeModifier = new JTextField(8);
        modifierSettings.add(ticketQueueSizeModifier);

        modifierSettings.add(new JLabel("Maximum ticket queue size:"));
        ticketQueueMaxSize = new JTextField(8);
        modifierSettings.add(ticketQueueMaxSize);

        modifierSettings.add(new JLabel("Cars with a subscription start leaving if queue size is this long:"));
        subscriptionQueueSizeModifier = new JTextField(8);
        modifierSettings.add(subscriptionQueueSizeModifier);

        modifierSettings.add(new JLabel("Maximum subscription queue size:"));
        subscriptionQueueMaxSize = new JTextField(8);
        modifierSettings.add(subscriptionQueueMaxSize);

        modifierSettings.add(new JLabel("Weight of cars skipping spots between two cars:"));
        neighbouringParkingSPotWeightModifier = new JTextField(8);
        modifierSettings.add(neighbouringParkingSPotWeightModifier);

        advancedSettings.add(new JLabel("Amount of spots available for subscriptions:"));
        subscriptionSpots = new JTextField(8);
        advancedSettings.add(subscriptionSpots);

        advancedSettings.add(new JLabel("Amount of spots available for electric vehicles:"));
        electricSpots = new JTextField(8);
        advancedSettings.add(electricSpots);

        advancedSettings.add(new JLabel("Amount of spots available for motorcylces:"));
        motorcycleSpots = new JTextField(8);
        advancedSettings.add(motorcycleSpots);

        advancedSettings.add(new JSeparator());
        advancedSettings.add(new JSeparator());

        advancedSettings.add(new JLabel("Desirability to go up a floor, lower values are more desirable:"));
        floorWeight = new JTextField(8);
        advancedSettings.add(floorWeight);

        advancedSettings.add(new JLabel("Desirability to the next row, lower values are more desirable:"));
        rowWeight = new JTextField(8);
        advancedSettings.add(rowWeight);

        advancedSettings.add(new JLabel("Desirability to park inbetween two cars, higher values are more desirable:"));
        spotWeight = new JTextField(8);
        advancedSettings.add(spotWeight);

        advancedSettings.add(new JSeparator());
        advancedSettings.add(new JSeparator());

        advancedSettings.add(new JLabel("Speed normal queues are handled:"));
        ticketQueueSpeed = new JTextField(8);
        advancedSettings.add(ticketQueueSpeed);

        advancedSettings.add(new JLabel("Speed subscription queues are handled:"));
        subscriptionQueueSpeed = new JTextField(8);
        advancedSettings.add(subscriptionQueueSpeed);

        advancedSettings.add(new JLabel("Speed exit queues are handled:"));
        exitQueueSpeed = new JTextField(8);
        advancedSettings.add(exitQueueSpeed);

        advancedSettings.add(new JSeparator());
        advancedSettings.add(new JSeparator());

        advancedSettings.add(new JLabel("Average stay of a vehicle:"));
        averageVehicleDurationInMinutes = new JTextField(8);
        advancedSettings.add(averageVehicleDurationInMinutes);

        advancedSettings.add(new JLabel("Maximum stay of a vehicle:"));
        maxVehicleDurationInMinutes = new JTextField(8);
        advancedSettings.add(maxVehicleDurationInMinutes);

        advancedSettings.add(new JLabel("Minimum stay of a vehicle:"));
        minVehicleDurationInMinutes = new JTextField(8);
        advancedSettings.add(minVehicleDurationInMinutes);

        advancedSettings.add(new JLabel("Average stay of a vehicle with a reservation:"));
        averageReservationDurationInMinutes = new JTextField(8);
        advancedSettings.add(averageReservationDurationInMinutes);

        advancedSettings.add(new JLabel("Maximum stay of a vehicle with a reservation:"));
        maxReservationDurationInMinutes = new JTextField(8);
        advancedSettings.add(maxReservationDurationInMinutes);

        advancedSettings.add(new JLabel("Minimum stay of a vehicle with a reservation:"));
        minReservationDurationInMinutes = new JTextField(8);
        advancedSettings.add(minReservationDurationInMinutes);
    }

    public String getAmountOfFloors() {return amountOfFloorsInput.getText();}
    public String getAmountOfRows() {return amountOfRowsInput.getText();}
    public String getAmountOfSpots() {return amountOfSpotsInput.getText();}
    public String getPriceInEuro() {return priceInEuroInput.getText();}
    public String getReservationPrice() {return reservationPrice.getText();}
    public String getSeed() {return seedInput.getText();}

    public String getBaseVehicleModifier(){return baseVehicleModifier.getText();}
    public String getTicketVehicleModifier(){return ticketVehicleModifier.getText();}
    public String getSubscriptionVehicleModifier() {return subscriptionVehicleModifier.getText();}
    public String getReservationVehicleModifier() {return reservationVehicleModifier.getText();}
    public String getRegularCarModifier() {return regularCarModifier.getText();}
    public String getElectricCarModifier() {return electricCarModifier.getText();}
    public String getMotorcycleModifier() {return motorcyleModifier.getText();}
    public String getParkingDurationModifier() {return parkingDurationModifier.getText();}
    public String getReservationDurationModifier() {return reservationDurationModifier.getText();}
    public String getTicketQueueSizeModifier() {return ticketQueueSizeModifier.getText();}
    public String getTicketQueueMaxSize() {return ticketQueueMaxSize.getText();}
    public String getSubscriptionQueueSizeModifier() {return subscriptionQueueSizeModifier.getText();}
    public String getSubscriptionQueueMaxSize() {return subscriptionQueueMaxSize.getText();}
    public String getNeighbouringParkingSpotWeightModifier() {return neighbouringParkingSPotWeightModifier.getText();}

    public String getSubscriptionSpots() {return subscriptionSpots.getText();}
    public String getElectricSpots() {return electricSpots.getText();}
    public String getMotorcycleSpots() {return motorcycleSpots.getText();}
    public String getFloorWeight() {return floorWeight.getText();}
    public String getRowWeight() {return rowWeight.getText();}
    public String getSpotWeight() {return spotWeight.getText();}
    public String getTicketQueueSpeed() {return ticketQueueSpeed.getText();}
    public String getSubscriptionQueueSpeed() {return subscriptionQueueSpeed.getText();}
    public String getExitQueueSpeed() {return exitQueueSpeed.getText();}
    public String getAverageVehicleDurationInMinutes() {return averageVehicleDurationInMinutes.getText();}
    public String getMaxVehicleDurationInMinutes() {return maxVehicleDurationInMinutes.getText();}
    public String getMinVehicleDurationInMinutes() {return minVehicleDurationInMinutes.getText();}
    public String getAverageReservationDurationInMinutes() {return averageReservationDurationInMinutes.getText();}
    public String getMaxReservationDurationInMinutes() {return maxReservationDurationInMinutes.getText();}
    public String getMinReservationDurationInMinutes() {return minReservationDurationInMinutes.getText();}

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