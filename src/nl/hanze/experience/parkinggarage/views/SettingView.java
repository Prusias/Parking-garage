package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;
import javax.swing.*;

/**
 * @author Mike van der Velde
 * @version 0.0.4
 * @since 0.0.4
 */
public class SettingView extends JFrameView {

    public SettingView() {
        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.pack();
        this.setSize(400, 600);
    }

    @Override
    public void update(Model model) {

    }

}