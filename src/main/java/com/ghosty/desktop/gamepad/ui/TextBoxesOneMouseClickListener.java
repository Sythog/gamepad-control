/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.ui;

import com.ghosty.desktop.gamepad.listener.ControllerListenerContainer;
import com.ghosty.desktop.gamepad.prop.PropertyManager;
import com.ghosty.desktop.gamepad.utils.UIUtils;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller;
import net.java.games.input.Event;

import javax.swing.JTextField;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.ghosty.desktop.gamepad.utils.ControllerUtils.LISTENER_CONTAINERS;

public class TextBoxesOneMouseClickListener extends MouseAdapter {

    private final PropertyManager propertyManager = PropertyManager.getInstance();

    private JTextField textField;
    private String propName;

    public TextBoxesOneMouseClickListener(JTextField textField, String propName) {
        this.textField = textField;
        this.propName = propName;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        LISTENER_CONTAINERS.stream()
                .filter(ControllerListenerContainer::handlersEnabled)
                .findAny()
                .ifPresent(container -> {
                    container.toggleHandlers(true);
                    UIUtils.TRAY_ICON.displayMessage(container.getController().getName(),
                            "Press button to change bind", TrayIcon.MessageType.INFO);
                    listenToFirstButtonPressed(container);
                    textField.setText(propertyManager.getProperty(propName));
                    container.toggleHandlers(true);
                });
    }

    private void listenToFirstButtonPressed(ControllerListenerContainer container) {
        Controller controller = container.getController();
        boolean buttonPressed = false;
        while (!buttonPressed) {
            controller.poll();
            Event event = new Event();
            while (controller.getEventQueue().getNextEvent(event)) {
                Identifier id = event.getComponent().getIdentifier();
                if (id instanceof Button) {
                    propertyManager.setProperty(propName, id.getName());
                    propertyManager.storeProperties();
                    buttonPressed = true;
                }
            }
        }
    }
}
