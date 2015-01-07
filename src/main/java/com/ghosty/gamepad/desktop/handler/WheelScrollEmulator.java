package com.ghosty.gamepad.desktop.handler;

import com.ghosty.gamepad.desktop.utils.MouseController;
import net.java.games.input.Component;

import static com.ghosty.gamepad.desktop.utils.ApplicationUtils.ANALOG_STICK_DEAD_ZONE;
import static java.lang.Math.abs;

public class WheelScrollEmulator implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        MouseController mouseController = MouseController.getInstance();
        if (abs(event.getPollData()) < ANALOG_STICK_DEAD_ZONE) {
            return;
        }
        if (event.getPollData() > 0) {
            mouseController.mouseScrollUp();
        } else {
            mouseController.mouseScrollDown();
        }
    }
}
