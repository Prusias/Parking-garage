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
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public class SettingView extends JFrameView {

    private Container container;
    private JPanel simulationSettings;

    private JTabbedPane tabbedPane;

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
     * Making a new settings view
     */
    public SettingView() {
        this.setBackground(Color.WHITE);
        new GridLayout(1, 1);
        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = makeTextPanel("Settings");
        tabbedPane.addTab("Settings", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeTextPanel("Advanced");
        tabbedPane.addTab("Advanced", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        add(tabbedPane);

//        private static void createAndShowGUI(){
//            JFrame frame = new JFrame("Settings");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            frame.add(new SettingView(), BorderLayout.CENTER);
//
//            frame.pack();
//            frame.setVisible(true);
//        }


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
                new JLabel("Modifiers for each day of the week:", SwingConstants.LEFT)
        ));
        container.add(weekdayModifiers);

        container.add(new JSeparator());

        // Add the JPanel for hour modifiers
        GridLayout hourModifiersGridLayout = new GridLayout(0, 16);
        hourModifiersGridLayout.setHgap(2);
        hourModifiersGridLayout.setVgap(2);

        hourModifiers = new JPanel();
        hourModifiers.setLayout(hourModifiersGridLayout);
        //hourModifiers.setSize(600, 40);
        hourModifiers.setBorder(new EmptyBorder(10, 10, 10, 10));
        //TODO: Why won't this left-align?
        container.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(
                new JLabel("Modifiers for each hour of the day:", SwingConstants.LEFT)
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




        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

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

    protected JComponent makeTextPanel(String text){
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1,1));
        panel.add(filler);
        return panel;
    }
}