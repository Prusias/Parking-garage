package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.JPanelView;
import nl.hanze.experience.mvc.Model;

import javax.swing.*;
import java.awt.*;

public class TabbedView extends JPanelView {
    private JTabbedPane jTabbedPane;

    public TabbedView() {
        this.setLayout(new GridLayout(1, 1));
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setBackground(Color.WHITE);
        this.add(jTabbedPane);
        this.setBackground(Color.WHITE);
    }

    public void addPanel(JPanel panel, String name) {
        jTabbedPane.addTab(name, null, panel);
    }

    @Override
    public void update(Model model) {
    }
}
