package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.JPanelView;
import nl.hanze.experience.mvc.Model;

import javax.swing.*;
import java.awt.*;

/**
 * The view class for the graph tabs in simulation where graphical operations regarding graph tabs happen
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class TabbedView extends JPanelView {
    private JTabbedPane jTabbedPane;

    /**
     * Make new tabbed view
     */
    public TabbedView() {
        this.setLayout(new GridLayout(1, 1));
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setBackground(Color.WHITE);
        this.add(jTabbedPane);
        this.setBackground(Color.WHITE);
    }

    /**
     * Add a panel to the tab
     * @param panel A panel to add
     * @param name Name to give to the panel and tab together
     */
    public void addPanel(JPanel panel, String name) {
        jTabbedPane.addTab(name, null, panel);
    }

    @Override
    public void update(Model model) {
    }
}
