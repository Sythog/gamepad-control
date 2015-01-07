package com.ghosty.desktop.gamepad.handler;

import com.ghosty.desktop.gamepad.listener.ControllerListener;
import com.ghosty.desktop.gamepad.utils.MouseController;
import com.ghosty.desktop.gamepad.utils.MouseMove;
import net.java.games.input.Component;

import static com.ghosty.desktop.gamepad.utils.ApplicationUtils.extractMouseMove;
import static java.lang.Math.abs;

public abstract class EventHandlers {

    private static final MouseController MOUSE_CONTROLLER = MouseController.getInstance();
    private static final float ANALOG_STICK_DEAD_ZONE = .1F;
    private static final float PRESSED = 1;
    private static final float RELEASED = 0;

    public static final ControllerEventHandler LEFT_MOUSE_CLICK_EMULATOR =
            event -> buttonHandler(event, MOUSE_CONTROLLER::mouseLeftPress, MOUSE_CONTROLLER::mouseLeftRelease);

    public static final ControllerEventHandler RIGHT_MOUSE_CLICK_EMULATOR =
            event -> buttonHandler(event, MOUSE_CONTROLLER::mouseRightPress, MOUSE_CONTROLLER::mouseRightRelease);

    public static final ControllerEventHandler MOUSE_MOVE_EMULATOR = event -> {
        if (abs(event.getPollData()) < ANALOG_STICK_DEAD_ZONE) {
            return;
        }
        MouseMove move = extractMouseMove(event);
        if (move != null) {
            MOUSE_CONTROLLER.moveMouse(move);
        }
    };

    public static final ControllerEventHandler WHEEL_SCROLL_EMULATOR = event -> {
        if (abs(event.getPollData()) < ANALOG_STICK_DEAD_ZONE) {
            return;
        }
        if (event.getPollData() > 0) {
            MOUSE_CONTROLLER.mouseScrollUp();
        } else {
            MOUSE_CONTROLLER.mouseScrollDown();
        }
    };

    public static final ControllerEventHandler START_BUTTON_HANDLER = event -> {
        if (event.getPollData() == RELEASED) {
            ControllerListener.toggleHandlers();
        }
    };

    private static void buttonHandler(Component button, Action onPress, Action onRelease) {
        if (button.getPollData() == PRESSED) {
            onPress.perform();
        } else {
            onRelease.perform();
        }
    }

    @FunctionalInterface
    private interface Action {
        void perform();
    }
}
