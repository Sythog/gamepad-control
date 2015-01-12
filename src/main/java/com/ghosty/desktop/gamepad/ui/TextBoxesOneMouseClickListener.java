/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.ui;

import com.ghosty.desktop.gamepad.listener.ControllerListenerContainer;
import com.ghosty.desktop.gamepad.prop.PropertyManager;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller;
import net.java.games.input.Event;

import javax.swing.JTextField;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.ghosty.desktop.gamepad.utils.ControllerUtils.getActiveControllerListenerContainer;
import static com.ghosty.desktop.gamepad.utils.UIUtils.TRAY_ICON;

public class TextBoxesOneMouseClickListener extends MouseAdapter {

    private final PropertyManager propertyManager = PropertyManager.getInstance();

    private JTextField textField;
    private String propName;
    private String propDesc;

    public TextBoxesOneMouseClickListener(JTextField textField, String propName, String propDesc) {
        this.textField = textField;
        this.propName = propName;
        this.propDesc = propDesc;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ControllerListenerContainer container = getActiveControllerListenerContainer();
        if (container == null) {
            return;
        }
        container.toggleHandlers(true);
        TRAY_ICON.displayMessage(container.getController().getName(),
                "Press button to change bind for " + propDesc, TrayIcon.MessageType.INFO);
        listenToFirstButtonPressed(container);
        textField.setText(propertyManager.getProperty(propName));
        container.toggleHandlers(true);
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
