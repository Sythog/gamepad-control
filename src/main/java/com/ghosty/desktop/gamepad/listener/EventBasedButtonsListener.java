/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.listener;

import com.ghosty.desktop.gamepad.handler.ControllerEventHandler;
import com.ghosty.desktop.gamepad.handler.StartButtonHandler;
import com.google.common.collect.ImmutableMap;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Event;

import java.util.Map;

import static com.ghosty.desktop.gamepad.handler.EventHandlers.LEFT_MOUSE_CLICK_EMULATOR;
import static com.ghosty.desktop.gamepad.handler.EventHandlers.RIGHT_MOUSE_CLICK_EMULATOR;

public class EventBasedButtonsListener extends ControllerListener {

    private static final Map<Identifier, ControllerEventHandler> BUTTONS_HANDLER_MAP =
            ImmutableMap.<Identifier, ControllerEventHandler>builder()
                    .put(Button._0, LEFT_MOUSE_CLICK_EMULATOR)       // A
                    .put(Button._1, RIGHT_MOUSE_CLICK_EMULATOR)      // B
                    .build();

    private StartButtonHandler startButtonHandler;

    public EventBasedButtonsListener(ControllerListenerContainer container) {
        super(container, 50);
        startButtonHandler = new StartButtonHandler(container);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void processPollData() {
        Event event = new Event();
        while (controller.getEventQueue().getNextEvent(event)) {
            Identifier id = event.getComponent().getIdentifier();
            if (id.equals(Button._7)) {
                startButtonHandler.handle(event.getComponent());
            } else if (id instanceof Button && container.handlersEnabled()) {
                ControllerEventHandler handler = BUTTONS_HANDLER_MAP.get(id);
                if (handler != null) {
                    handler.handle(event.getComponent());
                }
            }
        }
    }
}
