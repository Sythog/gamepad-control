/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.handler;

import com.ghosty.desktop.gamepad.listener.ControllerListenerContainer;
import net.java.games.input.Component;

import static com.ghosty.desktop.gamepad.utils.EventHandlerUtils.RELEASED;

public class StartButtonHandler implements ControllerEventHandler {

    private ControllerListenerContainer controllerListenerContainer;

    public StartButtonHandler(ControllerListenerContainer controllerListenerContainer) {
        this.controllerListenerContainer = controllerListenerContainer;
    }

    @Override
    public void handle(Component event) {
        if (event.getPollData() == RELEASED) {
            controllerListenerContainer.toggleHandlers();
        }
    }
}
