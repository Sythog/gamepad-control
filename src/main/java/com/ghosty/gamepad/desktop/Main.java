package com.ghosty.gamepad.desktop;

import net.java.games.input.Controller;

import java.awt.*;

import static com.ghosty.gamepad.desktop.utils.ControllerUtils.getController;
import static com.ghosty.gamepad.desktop.utils.ControllerUtils.startListeners;
import static net.java.games.input.Controller.Type.GAMEPAD;

public class Main {

    public static void main(String[] args) throws InterruptedException, AWTException {
        Controller gamepad = getController(GAMEPAD);

        startListeners(gamepad);
    }
}
