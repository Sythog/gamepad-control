package com.ghosty.desktop.gamepad;

import com.ghosty.desktop.gamepad.utils.ApplicationUtils;
import net.java.games.input.Controller;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, AWTException {
        Controller gamepad = ApplicationUtils.findGamepad();
        System.out.println("Press Start to use your controller");
        ApplicationUtils.startListeners(gamepad);
    }
}
