/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.prop;

import com.ghosty.desktop.gamepad.handler.ControllerEventHandler;
import net.java.games.input.Component.Identifier.Button;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static com.ghosty.desktop.gamepad.utils.ControllerUtils.LISTENER_CONTAINERS;

public class ButtonSettingsPropertyListener implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LISTENER_CONTAINERS.forEach(container -> {
            ControllerEventHandler handler =
                    container.getButtonsListener().getHandlerForComponent(new Button(evt.getOldValue().toString()));
            container.getButtonsListener().changeComponentToHandle(handler, new Button(evt.getNewValue().toString()));
        });
    }
}
