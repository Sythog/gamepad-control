package com.ghosty.desktop.gamepad.listener;

import net.java.games.input.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.TrayIcon;

import static com.ghosty.desktop.gamepad.Main.getTrayIcon;

public class ControllerListenerContainer {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private Controller controller;
    private boolean handlersEnabled;

    private EventBasedButtonsListener buttonsListener;
    private ContinuousAxisListener axisListener;

    public ControllerListenerContainer(Controller controller) {
        this.controller = controller;
        this.buttonsListener = new EventBasedButtonsListener(this);
        this.axisListener = new ContinuousAxisListener(this);
        this.handlersEnabled = false;
    }

    public void startListeners() {
        new Thread(buttonsListener, controller.getName() + " Buttons Listener").start();
        new Thread(axisListener, controller.getName() + " Axis Listener").start();
    }

    public void toggleHandlers() {
        handlersEnabled ^= true;
        LOG.info("{} - Handlers have been " + (handlersEnabled ? "enabled" : "disabled"), controller.getName());
        getTrayIcon().displayMessage(controller.getName(),
                "Gamepad " + (handlersEnabled ? "enabled" : "disabled"),
                TrayIcon.MessageType.INFO);
    }

    public Controller getController() {
        return controller;
    }

    public boolean handlersEnabled() {
        return handlersEnabled;
    }
}
