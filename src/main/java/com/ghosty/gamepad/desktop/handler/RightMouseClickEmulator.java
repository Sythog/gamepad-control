package com.ghosty.gamepad.desktop.handler;

import com.ghosty.gamepad.desktop.utils.MouseController;
import net.java.games.input.Component;

import static com.ghosty.gamepad.desktop.utils.ApplicationUtils.PRESSED;

public class RightMouseClickEmulator implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        MouseController mouseController = MouseController.getInstance();
        if (event.getPollData() == PRESSED) {
            mouseController.mouseRightPress();
        } else {
            mouseController.mouseRightRelease();
        }
    }
}
