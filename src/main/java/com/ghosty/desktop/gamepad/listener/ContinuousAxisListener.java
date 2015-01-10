/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.listener;

import com.ghosty.desktop.gamepad.handler.ControllerEventHandler;
import com.google.common.collect.ImmutableMap;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Axis;

import java.util.Map;

import static com.ghosty.desktop.gamepad.handler.EventHandlers.*;

public class ContinuousAxisListener extends ControllerListener {

    private static final Map<Identifier, ControllerEventHandler> AXIS_HANDLER_MAP =
            ImmutableMap.<Identifier, ControllerEventHandler>builder()
                    .put(Axis.X, CURSOR_LEFT_RIGHT_MOVE_EMULATOR)
                    .put(Axis.Y, CURSOR_UP_DOWN_MOVE_EMULATOR)
                    .put(Axis.Z, WHEEL_SCROLL_EMULATOR)                 // RT & LT
                    .build();

    public ContinuousAxisListener(ControllerListenerContainer container) {
        super(container, 10);
    }

    @Override
    public void processPollData() {
        if (container.handlersEnabled()) {
            AXIS_HANDLER_MAP.forEach((axis, handler) -> handler.handle(controller.getComponent(axis)));
        }
    }
}
