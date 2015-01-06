package com.ghosty.gamepad.desktop.handler;

import com.ghosty.gamepad.desktop.utils.MouseController;
import com.ghosty.gamepad.desktop.utils.MouseMove;
import net.java.games.input.Component;

import static com.ghosty.gamepad.desktop.utils.ControllerUtils.isXAxis;
import static com.ghosty.gamepad.desktop.utils.ControllerUtils.isYAxis;
import static com.ghosty.gamepad.desktop.utils.Direction.*;
import static java.lang.Math.abs;

public class AxisEventHandler implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        MouseMove move = extractMouseMove(event);
        if (move != null) {
            MouseController.getInstance().moveMouse(move);
        }
    }

    private MouseMove extractMouseMove(Component component) {
        float axisMove = component.getPollData();
        if (abs(axisMove) < .1) {
            return null;
        }
        int speed = (int) (10 * abs(axisMove));
        if (isXAxis(component)) {
            if (axisMove > 0) {
                return new MouseMove(RIGHT, speed);
            } else {
                return new MouseMove(LEFT, speed);
            }
        }
        if (isYAxis(component)) {
            if (axisMove > 0) {
                return new MouseMove(UP, speed);
            } else {
                return new MouseMove(DOWN, speed);
            }
        }
        return null;
    }
}
