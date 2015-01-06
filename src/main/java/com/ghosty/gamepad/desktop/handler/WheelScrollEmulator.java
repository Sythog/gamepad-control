package com.ghosty.gamepad.desktop.handler;

import com.ghosty.gamepad.desktop.utils.MouseController;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.Button;

public class WheelScrollEmulator implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        if (event.getPollData() == 1.0) {
            if (event.getIdentifier().equals(Button._2)) {
                MouseController.getInstance().mouseScrollDown();
            } else if (event.getIdentifier().equals(Button._3)) {
                MouseController.getInstance().mouseScrollUp();
            }
        }
    }
}
