package com.ghosty.desktop.gamepad.utils;

import com.ghosty.desktop.gamepad.listener.ContinuousAxisListener;
import com.ghosty.desktop.gamepad.listener.EventBasedButtonsListener;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.stream.Stream;

import static com.ghosty.desktop.gamepad.utils.Direction.*;
import static java.lang.Math.abs;
import static net.java.games.input.Controller.Type.GAMEPAD;

public abstract class ApplicationUtils {

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

    public static MouseMove extractMouseMove(Component component) {
        float axisMove = component.getPollData();
        int speed = (int) (10 * abs(axisMove));
        if (isXAxis(component)) {
            return new MouseMove(axisMove > 0 ? RIGHT : LEFT, speed);
        }
        if (isYAxis(component)) {
            return new MouseMove(axisMove > 0 ? UP : DOWN, speed);
        }
        return null;
    }

    public static void startListeners(Controller controller) {
        new Thread(new ContinuousAxisListener(controller), "Axis Listener").start();
        new Thread(new EventBasedButtonsListener(controller), "Buttons Listener").start();
    }


    private static boolean isXAxis(Component component) {
        return component.getIdentifier().equals(Axis.X);
    }

    private static boolean isYAxis(Component component) {
        return component.getIdentifier().equals(Axis.Y);
    }
}
