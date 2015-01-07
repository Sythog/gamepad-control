package com.ghosty.desktop.gamepad.handler;

import com.ghosty.desktop.gamepad.utils.ApplicationUtils;
import com.ghosty.desktop.gamepad.utils.MouseController;
import net.java.games.input.Component;

public class LeftMouseClickEmulator implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        MouseController mouseController = MouseController.getInstance();
        if (event.getPollData() == ApplicationUtils.PRESSED) {
            mouseController.mouseLeftPress();
        } else {
            mouseController.mouseLeftRelease();
        }
    }
}
