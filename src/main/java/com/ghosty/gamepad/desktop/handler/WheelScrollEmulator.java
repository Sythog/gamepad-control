package com.ghosty.gamepad.desktop.handler;

import com.ghosty.gamepad.desktop.utils.MouseController;
import net.java.games.input.Component;

import static java.lang.Math.abs;

public class WheelScrollEmulator implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        //MouseController.getInstance().mouseScroll(-(int) (event.getPollData() * 3));
        if (abs(event.getPollData()) > .1) {
            MouseController.getInstance().mouseScroll(event.getPollData() > 0 ? 1 : -1);
        }
    }
}
