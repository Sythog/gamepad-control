package com.ghosty.gamepad.desktop;

import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller;

import java.awt.*;

import static com.ghosty.gamepad.desktop.utils.ControllerUtils.*;
import static net.java.games.input.Controller.Type.GAMEPAD;

public class Main {

    public static void main(String[] args) throws InterruptedException, AWTException {
        Controller gamepad = getController(GAMEPAD);

        System.out.println("Press Start to use your controller");
        waitForKeyPress(gamepad, gamepad.getComponent(Button._7));
//        while (true) {
//            gamepad.poll();
//            Event event = new Event();
//            while (gamepad.getEventQueue().getNextEvent(event)) {
//                System.out.println(event.getComponent() + ": " + event.getComponent().getPollData());
//            }
//        }
        startListeners(gamepad);
    }
}
