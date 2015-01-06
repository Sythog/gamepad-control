package com.ghosty.gamepad.desktop.listener;

import net.java.games.input.Controller;

public abstract class ControllerListener implements Runnable {

    protected Controller controller;

    protected static boolean handlersEnabled;

    public ControllerListener(Controller controller) {
        this.controller = controller;
        handlersEnabled = false;
    }

    public static void toggleHandlers() {
        handlersEnabled ^= true;
    }
}