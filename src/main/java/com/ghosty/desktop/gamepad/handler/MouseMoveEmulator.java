package com.ghosty.desktop.gamepad.handler;

import com.ghosty.desktop.gamepad.utils.ApplicationUtils;
import com.ghosty.desktop.gamepad.utils.MouseController;
import com.ghosty.desktop.gamepad.utils.MouseMove;
import net.java.games.input.Component;

import static java.lang.Math.abs;

public class MouseMoveEmulator implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        MouseMove move = extractMouseMove(event);
        if (move != null) {
            MouseController.getInstance().moveMouse(move);
        }
    }

    private MouseMove extractMouseMove(Component component) {
        float axisMove = component.getPollData();
        if (abs(axisMove) < ApplicationUtils.ANALOG_STICK_DEAD_ZONE) {
            return null;
        }
        int speed = (int) (10 * abs(axisMove));
        if (ApplicationUtils.isXAxis(component)) {
            if (axisMove > 0) {
                return new MouseMove(RIGHT, speed);
            } else {
                return new MouseMove(LEFT, speed);
            }
        }
        if (ApplicationUtils.isYAxis(component)) {
            if (axisMove > 0) {
                return new MouseMove(UP, speed);
            } else {
                return new MouseMove(DOWN, speed);
            }
        }
        return null;
    }
}
