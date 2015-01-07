package com.ghosty.desktop.gamepad.handler;

import com.ghosty.desktop.gamepad.utils.ApplicationUtils;
import com.ghosty.desktop.gamepad.utils.MouseController;
import net.java.games.input.Component;

import static java.lang.Math.abs;

public class WheelScrollEmulator implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        MouseController mouseController = MouseController.getInstance();
        if (abs(event.getPollData()) < ApplicationUtils.ANALOG_STICK_DEAD_ZONE) {
            return;
        }
        if (event.getPollData() > 0) {
            mouseController.mouseScrollUp();
        } else {
            mouseController.mouseScrollDown();
        }
    }
}
