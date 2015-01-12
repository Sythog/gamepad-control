/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.utils;

import com.ghosty.desktop.gamepad.listener.ControllerListenerContainer;
import com.ghosty.desktop.gamepad.prop.AutoStartupPropertyListener;
import com.ghosty.desktop.gamepad.prop.ButtonSettingsPropertyListener;
import com.ghosty.desktop.gamepad.prop.PropertyManager;
import com.google.common.collect.Lists;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Stream;

import static net.java.games.input.Controller.Type.GAMEPAD;

public abstract class ControllerUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerUtils.class);

    public static final List<ControllerListenerContainer> LISTENER_CONTAINERS = Lists.newArrayList();
    private static final PropertyManager PROPERTY_MANAGER = PropertyManager.getInstance();

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

    public static void startControllerListeners(Controller controller) {
        ControllerListenerContainer container = new ControllerListenerContainer(controller);
        LISTENER_CONTAINERS.add(container);
        container.startListeners();
    }

    public static ControllerListenerContainer getActiveControllerListenerContainer() {
        return LISTENER_CONTAINERS.stream()
                .filter(ControllerListenerContainer::handlersEnabled)
                .findAny()
                .orElseGet(() -> {
                    LOG.warn("No active controllers found");
                    return null;
                });
    }

    public static void registerPropertyListeners() {
        ButtonSettingsPropertyListener buttonSettingsPropertyListener = new ButtonSettingsPropertyListener();
        PROPERTY_MANAGER.addListener("gdc.auto.startup", new AutoStartupPropertyListener());
        PROPERTY_MANAGER.addListener("gdc.left.click", buttonSettingsPropertyListener);
        PROPERTY_MANAGER.addListener("gdc.right.click", buttonSettingsPropertyListener);
    }
}
