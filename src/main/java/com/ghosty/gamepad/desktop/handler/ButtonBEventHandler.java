package com.ghosty.gamepad.desktop.handler;

import com.ghosty.gamepad.desktop.utils.MouseController;
import net.java.games.input.Component;

import java.awt.*;

public class ButtonBEventHandler implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        try {
            if (event.getPollData() == 1.0) {
                MouseController.getInstance().mouseRightPress();
            } else {
                MouseController.getInstance().mouseRightRelease();
            }
        } catch (AWTException ignored) {
        }
    }
}
