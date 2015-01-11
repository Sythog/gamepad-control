/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad;

import net.java.games.input.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.AWTException;

import static com.ghosty.desktop.gamepad.utils.ControllerUtils.*;
import static com.ghosty.desktop.gamepad.utils.UIUtils.initializeTrayMenu;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException, AWTException {
        initializeTrayMenu();
        Controller gamepad = findGamepad();
        LOG.info("Press Start to use your controller");
        registerPropertyListeners();
        startControllerListeners(gamepad);
    }
}
