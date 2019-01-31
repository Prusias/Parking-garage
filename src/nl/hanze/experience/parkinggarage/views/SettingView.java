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
 * The class for the settings view
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
    private JPanel advancedSettings;
    private JPanel initSettings;

    private JPanel panel;
    private JPanel advPanel;
    private JPanel initPanel;

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
    private JTextField maxVehicleDUrationinMinutes;
    private JTextField minVehicleDUrationInMinutes;
    private JTextField averageReservationDurationInMinutes;
    private JTextField minReservationDurationInMinutes;
    private JTextField maxReservationDurationInMinutes;

    private JTextField ticketQueueSPeed;
    private JTextField subscriptionQueueSpeed;
    private JTextField exitQueueSpeed;

    private JTextField floorWeight;
    private JTextField rowWeight;
    private JTextField spotWeight;

    private JTextField subscriptionSpots;
    private JTextField reservedSpots;
    private JTextField electricSpots;
    private JTextField motorcycleSpots;

    private JPanel buttons;
    private JPanel advButtons;
    private JPanel initButtons;

    private JTextField amountOfFloorsInput;
    private JTextField amountOfRowsInput;
    private JTextField amountOfSpotsInput;
    private JTextField priceInEuroInput;
    private JTextField seedInput;

    /**
     * Making a new settings view
     */
    public SettingView() {
        this.setBackground(Color.WHITE);
        new GridLayout(1, 1);
        panel = new JPanel();
        advPanel = new JPanel();
        initPanel = new JPanel();
        tabbedPane = new JTabbedPane();


        tabbedPane.addTab("Settings", panel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Advanced", advPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Initial Values", initPanel);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);


        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(true);

        container = this.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        advPanel.setLayout(new BoxLayout(advPanel, BoxLayout.Y_AXIS));

        container.add(tabbedPane);

        // Add the JPanel for the simulation settings
        GridLayout simulationSettingsGridLayout = new GridLayout(0, 2);
        GridLayout advancedSettingsGridLayout = new GridLayout(0,2);
        simulationSettingsGridLayout.setHgap(10);
        simulationSettingsGridLayout.setVgap(10);
        advancedSettingsGridLayout.setHgap(10);
        advancedSettingsGridLayout.setVgap(10);

        simulationSettings = new JPanel();
        advancedSettings = new JPanel();
        initSettings = new JPanel();

        simulationSettings.setLayout(simulationSettingsGridLayout);
        advancedSettings.setLayout(advancedSettingsGridLayout);

       // simulationSettings.setSize(600, 160);
        simulationSettings.setBorder(new EmptyBorder(10, 10, 10, 10));
        advancedSettings.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(simulationSettings);
        advPanel.add(advancedSettings);

        panel.add(new JSeparator());
        advPanel.add(new JSeparator());

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

        advButtons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
        advPanel.add(advButtons);

        addFields();

        JButton saveButton = new JButton("Save");
        buttons.add(saveButton);

        JButton advSaveButton = new JButton("Save");
        advButtons.add(advSaveButton);

        saveButton.addActionListener(e -> notifyController(EventId.SAVE));
        advSaveButton.addActionListener(e -> notifyController(EventId.SAVE));

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

        advancedSettings.add(new JLabel("Modifier for all vehicles:"));
        baseVehicleModifier = new JTextField(8);
        advancedSettings.add(baseVehicleModifier);

        advancedSettings.add(new JLabel("Modifier for any ticket vehicles:"));
        ticketVehicleModifier = new JTextField(8);
        advancedSettings.add(ticketVehicleModifier);

        advancedSettings.add(new JLabel("Modifier for vehicles with a subscription:"));
        subscriptionVehicleModifier = new JTextField(8);
        advancedSettings.add(subscriptionVehicleModifier);

        advancedSettings.add(new JLabel("Modifier for vehicles with a reservation:"));
        reservationVehicleModifier = new JTextField(8);
        advancedSettings.add(reservationVehicleModifier);

        advancedSettings.add(new JLabel("Modifier for cars:"));
        regularCarModifier = new JTextField(8);
        advancedSettings.add(regularCarModifier);

        advancedSettings.add(new JLabel("Modifier for electric cars:"));
        electricCarModifier = new JTextField(8);
        advancedSettings.add(electricCarModifier);

        advancedSettings.add(new JLabel("Modifier for motorcylces:"));
        motorcyleModifier = new JTextField(8);
        advancedSettings.add(motorcyleModifier);

        advancedSettings.add(new JLabel("Parking duration modifier for normal tickets:"));
        parkingDurationModifier = new JTextField(8);
        advancedSettings.add(parkingDurationModifier);

        advancedSettings.add(new JLabel("Parking duration modifier for reservation tickets"));
        reservationDurationModifier = new JTextField(8);
        advancedSettings.add(reservationDurationModifier);

        advancedSettings.add(new JLabel("Cars start leaving is queue is this long:"));
        ticketQueueSizeModifier = new JTextField(8);
        advancedSettings.add(ticketQueueSizeModifier);

        advancedSettings.add(new JLabel("Maximum ticket queue size:"));
        ticketQueueMaxSize = new JTextField(8);
        advancedSettings.add(ticketQueueMaxSize);

        advancedSettings.add(new JLabel("Cars with a subscription start leaving if queue size is this long:"));
        subscriptionQueueSizeModifier = new JTextField(8);
        advancedSettings.add(subscriptionQueueSizeModifier);

        advancedSettings.add(new JLabel("Maximum subscription queue size:"));
        subscriptionQueueMaxSize = new JTextField(8);
        advancedSettings.add(subscriptionQueueMaxSize);

        advancedSettings.add(new JLabel("Weight of cars skipping spots between two cars:"));
        neighbouringParkingSPotWeightModifier = new JTextField(8);
        advancedSettings.add(neighbouringParkingSPotWeightModifier);




    }

    public String getAmountOfFloors() {return amountOfFloorsInput.getText();}
    public String getAmountOfRows() {return amountOfRowsInput.getText();}
    public String getAmountOfSpots() {return amountOfSpotsInput.getText();}
    public String getPriceInEuro() {return priceInEuroInput.getText();}
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