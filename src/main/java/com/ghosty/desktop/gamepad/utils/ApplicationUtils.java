/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.utils;

import com.ghosty.desktop.gamepad.listener.ContinuousAxisListener;
import com.ghosty.desktop.gamepad.listener.EventBasedButtonsListener;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static net.java.games.input.Controller.Type.GAMEPAD;

public abstract class ApplicationUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationUtils.class);

    public static Controller findGamepad() throws InterruptedException {
        Controller instance;
        do {
            Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
            instance = Stream.of(controllers)
                    .filter(controller -> controller.getType() == GAMEPAD)
                    .findAny()
                    .orElseGet(() -> {
                        LOG.error("No controllers of type GAMEPAD found! Will retry in 5 sec");
                        return null;
                    });
            if (instance == null) {
                Thread.sleep(5000);
            }
        } while (instance == null);
        LOG.info("Found a controller of type GAMEPAD: {}", instance);
        return instance;
    }

    public static void startListeners(Controller controller) {
        new Thread(new ContinuousAxisListener(controller), "Axis Listener").start();
        new Thread(new EventBasedButtonsListener(controller), "Buttons Listener").start();
    }
}
