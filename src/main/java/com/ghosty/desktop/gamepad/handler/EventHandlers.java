/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.handler;

import static com.ghosty.desktop.gamepad.model.Direction.*;
import static com.ghosty.desktop.gamepad.utils.EventHandlerUtils.*;

public abstract class EventHandlers {

    public static final ControllerEventHandler LEFT_MOUSE_CLICK_EMULATOR =
            buttonHandler(MOUSE_CONTROLLER::mouseLeftPress, MOUSE_CONTROLLER::mouseLeftRelease);

    public static final ControllerEventHandler RIGHT_MOUSE_CLICK_EMULATOR =
            buttonHandler(MOUSE_CONTROLLER::mouseRightPress, MOUSE_CONTROLLER::mouseRightRelease);

    public static final ControllerEventHandler CURSOR_LEFT_RIGHT_MOVE_EMULATOR = axisHandler(RIGHT, LEFT);

    public static final ControllerEventHandler CURSOR_UP_DOWN_MOVE_EMULATOR = axisHandler(UP, DOWN);

    public static final ControllerEventHandler WHEEL_SCROLL_EMULATOR =
            analogComponentHandler(event -> {
                if (event.getPollData() > 0) {
                    MOUSE_CONTROLLER.mouseScrollUp();
                } else {
                    MOUSE_CONTROLLER.mouseScrollDown();
                }
            });
}
