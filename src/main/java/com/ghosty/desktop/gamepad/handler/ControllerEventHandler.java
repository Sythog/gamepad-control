/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.handler;

import net.java.games.input.Component;

@FunctionalInterface
public interface ControllerEventHandler {

    void handle(Component event);
}
