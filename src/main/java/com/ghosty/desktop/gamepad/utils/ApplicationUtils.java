package com.ghosty.desktop.gamepad.utils;

import com.ghosty.desktop.gamepad.listener.ContinuousListener;
import com.ghosty.desktop.gamepad.listener.EventBasedListener;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.stream.Stream;

import static net.java.games.input.Controller.Type.GAMEPAD;

public abstract class ApplicationUtils {

    public static final float ANALOG_STICK_DEAD_ZONE = .1F;
    public static final float PRESSED = 1;
    public static final float RELEASED = 0;

    public static Controller findGamepad() throws InterruptedException {
        Controller instance;
        do {
            Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
            instance = Stream.of(controllers)
                    .filter(controller -> controller.getType() == GAMEPAD)
                    .findAny()
                    .orElseGet(() -> {
                        System.out.println("No controllers of type GAMEPAD found! Will retry in 5 sec");
                        return null;
                    });
            if (instance == null) {
                Thread.sleep(5000);
            }
        } while (instance == null);
        System.out.println("Found a controller of type GAMEPAD: " + instance);
        return instance;
    }

    public static void startListeners(Controller controller) {
        new Thread(new ContinuousListener(controller), "Axis Listener").start();
        new Thread(new EventBasedListener(controller), "Buttons Listener").start();
    }


    public static boolean isXAxis(Component component) {
        return component.getIdentifier().equals(Axis.X);
    }

    public static boolean isYAxis(Component component) {
        return component.getIdentifier().equals(Axis.Y);
    }
}
