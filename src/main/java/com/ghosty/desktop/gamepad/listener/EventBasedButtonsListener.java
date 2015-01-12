/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.listener;

import com.ghosty.desktop.gamepad.handler.ControllerEventHandler;
import com.ghosty.desktop.gamepad.handler.StartButtonHandler;
import com.ghosty.desktop.gamepad.prop.PropertyManager;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Event;

import static com.ghosty.desktop.gamepad.handler.EventHandlers.LEFT_MOUSE_CLICK_EMULATOR;
import static com.ghosty.desktop.gamepad.handler.EventHandlers.RIGHT_MOUSE_CLICK_EMULATOR;

public class EventBasedButtonsListener extends ControllerListener {

    private BiMap<String, ControllerEventHandler> buttonsHandlerMap = HashBiMap.create();
    private StartButtonHandler startButtonHandler;

    public EventBasedButtonsListener(ControllerListenerContainer container) {
        super(container, 50);
        PropertyManager propertyManager = PropertyManager.getInstance();
        buttonsHandlerMap.put(propertyManager.getProperty("gdc.left.click"), LEFT_MOUSE_CLICK_EMULATOR);
        buttonsHandlerMap.put(propertyManager.getProperty("gdc.right.click"), RIGHT_MOUSE_CLICK_EMULATOR);
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
                ControllerEventHandler handler = buttonsHandlerMap.get(id.getName());
                if (handler != null) {
                    handler.handle(event.getComponent());
                }
            }
        }
    }

    @Override
    public ControllerEventHandler getHandlerForComponent(Identifier component) {
        return buttonsHandlerMap.get(component.getName());
    }

    @Override
    public void changeComponentToHandle(ControllerEventHandler handler, Identifier component) {
        if (buttonsHandlerMap.containsValue(handler)) {
            BiMap<ControllerEventHandler, String> inverse = buttonsHandlerMap.inverse();
            if (!inverse.containsValue(component.getName())) {
                inverse.remove(handler);
                inverse.put(handler, component.getName());
                buttonsHandlerMap = inverse.inverse();
            }
        }
    }
}
