package com.ghosty.gamepad.desktop.utils;

import com.ghosty.gamepad.desktop.listener.ContinuousListener;
import com.ghosty.gamepad.desktop.listener.EventBasedListener;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.stream.Stream;

public abstract class ControllerUtils {

    public static Controller getController(Controller.Type type) throws InterruptedException {
        Controller instance;
        do {
            Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
            instance = Stream.of(controllers)
                    .filter(controller -> controller.getType() == type)
                    .findAny()
                    .orElseGet(() -> {
                        System.out.println("No controllers of type " + type + " found! Will retry in 5 sec");
                        return null;
                    });
            if (instance == null) {
                Thread.sleep(5000);
            }
        } while (instance == null);
        System.out.println("Found a controller of type " + type + ": " + instance);
        return instance;
    }

    public static void waitForKeyPress(Controller controller, Component component) {
        boolean keyPressed = false;
        while (!keyPressed) {
            controller.poll();
            if (component.getPollData() == 1.0) {
                keyPressed = true;
            }
        }
    }

    public static void startListeners(Controller controller) throws InterruptedException {
        new Thread(new ContinuousListener(controller)).start();
        new Thread(new EventBasedListener(controller)).start();
    }

    public static boolean isXAxis(Component component) {
        return component.getIdentifier().equals(Axis.X);
    }

    public static boolean isYAxis(Component component) {
        return component.getIdentifier().equals(Axis.Y);
    }
}
