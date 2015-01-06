package com.ghosty.gamepad.desktop;

import net.java.games.input.Controller;

import java.awt.*;

import static com.ghosty.gamepad.desktop.utils.ApplicationUtils.findGamepad;
import static com.ghosty.gamepad.desktop.utils.ApplicationUtils.startListeners;

public class Main {

    public static void main(String[] args) throws InterruptedException, AWTException {
        Controller gamepad = findGamepad();
        System.out.println("Press Start to use your controller");
        startListeners(gamepad);
    }
}
