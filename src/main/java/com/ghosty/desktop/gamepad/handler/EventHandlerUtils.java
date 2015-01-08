package com.ghosty.desktop.gamepad.handler;

import com.ghosty.desktop.gamepad.utils.Action;
import com.ghosty.desktop.gamepad.utils.Direction;
import com.ghosty.desktop.gamepad.utils.MouseController;
import com.ghosty.desktop.gamepad.utils.MouseMove;
import net.java.games.input.Component;

import java.util.function.Consumer;

import static java.lang.Math.abs;

public abstract class EventHandlerUtils {

    public static final MouseController MOUSE_CONTROLLER = MouseController.getInstance();
    public static final float ANALOG_STICK_DEAD_ZONE = .1F;
    public static final float PRESSED = 1;
    public static final float RELEASED = 0;

    public static ControllerEventHandler buttonHandler(Action onPress, Action onRelease) {
        return button -> {
            if (button.getPollData() == PRESSED) {
                onPress.perform();
            } else {
                onRelease.perform();
            }
        };
    }

    public static ControllerEventHandler axisHandler(Direction positiveDirection, Direction negativeDirection) {
        return analogComponentHandler(axis -> {
            float axisMove = axis.getPollData();
            int speed = (int) (10 * abs(axisMove));
            MouseMove move = new MouseMove(axisMove > 0 ? positiveDirection : negativeDirection, speed);
            MOUSE_CONTROLLER.moveMouse(move);
        });
    }

    public static ControllerEventHandler analogComponentHandler(Consumer<Component> onMove) {
        return component -> {
            if (abs(component.getPollData()) < ANALOG_STICK_DEAD_ZONE) {
                return;
            }
            onMove.accept(component);
        };
    }
}
