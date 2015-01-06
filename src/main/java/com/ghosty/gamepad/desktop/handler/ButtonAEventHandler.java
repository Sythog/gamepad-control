package com.ghosty.gamepad.desktop.handler;

import com.ghosty.gamepad.desktop.utils.MouseController;
import net.java.games.input.Component;

import java.awt.*;

public class ButtonAEventHandler implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        try {
            MouseController.getInstance().mouseLeftClick();
        } catch (AWTException ignored) {
        }
    }
}
