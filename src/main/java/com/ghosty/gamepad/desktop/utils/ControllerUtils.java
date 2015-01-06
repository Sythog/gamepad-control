package com.ghosty.gamepad.desktop.utils;

import com.ghosty.gamepad.desktop.handler.AxisEventHandler;
import com.ghosty.gamepad.desktop.handler.ButtonAEventHandler;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;

import java.util.stream.Stream;

import static com.ghosty.gamepad.desktop.utils.Direction.*;
import static java.lang.Math.abs;

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

    @SuppressWarnings("InfiniteLoopStatement")
    public static void listenToControllerEvents(Controller controller) throws InterruptedException {
        while (true) {
            controller.poll();
            Stream.of(controller.getComponents())
                    .filter(component -> isXAxis(component) || isYAxis(component))
                    .forEach(axis -> new AxisEventHandler().handle(axis));
            Event event = new Event();
            while (controller.getEventQueue().getNextEvent(event)) {
                Component component = event.getComponent();
                if (isButtonA(component) && component.getPollData() == 1.0) {
                    new ButtonAEventHandler().handle(component);
                }
            }
            Thread.sleep(100);
        }
    }

    public static MouseMove extractMouseMove(Component component) {
        float axisMove = component.getPollData();
        if (abs(axisMove) < .1) {
            return null;
        }
        int speed = (int) (100 * abs(axisMove));
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

    public static boolean isXAxis(Component component) {
        return isAxis(component) && "x".equalsIgnoreCase(component.getIdentifier().getName());
    }

    public static boolean isYAxis(Component component) {
        return isAxis(component) && "y".equalsIgnoreCase(component.getIdentifier().getName());
    }

    public static boolean isButtonA(Component component) {
        return component.getIdentifier() instanceof Button && "0".equals(component.getIdentifier().getName());
    }

    private static boolean isAxis(Component component) {
        return component.getIdentifier() instanceof Axis;
    }
}
