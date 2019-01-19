package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;
import nl.hanze.experience.parkinggarage.models.TestModel;

import javax.swing.*;
import java.awt.*;

import static nl.hanze.experience.parkinggarage.controllers.TestController.EVENT_ID_TEST;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class TestView extends View {

    private JTextField input;
    private JLabel output;

    public TestView() {
        this.setLayout(new FlowLayout());

        add(new JLabel("Amount of vehicles "));
        input = new JTextField(30);
        add(input);
        JButton button = new JButton("Show");
        output = new JLabel();
        this.add(button);
        this.add(output);

        button.addActionListener(e -> notifyController(EVENT_ID_TEST));

    }

    @Override
    protected void update(Model model) {
        //TODO: Is this the right exception?
        if (!(model instanceof TestModel)) {
            throw new IllegalArgumentException("Wrong model given to view.update");
        }
        TestModel testModel = (TestModel) model;
        output.setBackground(Color.getHSBColor(100f, 100f, 100f));
        output.setText(Integer.toString((testModel).getAmountOfCars()));

    }

    public int getAmountOfCars() {return Integer.parseInt(input.getText());}


}
