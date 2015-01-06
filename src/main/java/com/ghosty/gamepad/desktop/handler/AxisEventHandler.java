package com.ghosty.gamepad.desktop.handler;

import com.ghosty.gamepad.desktop.utils.MouseController;
import com.ghosty.gamepad.desktop.utils.MouseMove;
import net.java.games.input.Component;

import java.awt.*;

import static com.ghosty.gamepad.desktop.utils.ControllerUtils.extractMouseMove;

public class AxisEventHandler implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        try {
            MouseMove move = extractMouseMove(event);
            if (move != null) {
                MouseController.getInstance().moveMouse(move);
            }
        } catch (AWTException ignored) {
        }
    }
}
