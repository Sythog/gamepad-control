package com.ghosty.desktop.gamepad.handler;

import net.java.games.input.Component;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface ControllerEventHandler {

    void handle(Component event);
}
