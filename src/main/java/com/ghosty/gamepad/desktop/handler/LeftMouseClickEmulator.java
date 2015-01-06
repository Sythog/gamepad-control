package com.ghosty.gamepad.desktop.handler;

import com.ghosty.gamepad.desktop.utils.MouseController;
import net.java.games.input.Component;

public class LeftMouseClickEmulator implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        if (event.getPollData() == 1.0) {
            MouseController.getInstance().mouseLeftPress();
        } else {
            MouseController.getInstance().mouseLeftRelease();
        }
    }
}
