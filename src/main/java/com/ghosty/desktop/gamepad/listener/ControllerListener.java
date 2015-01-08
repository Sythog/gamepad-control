package com.ghosty.desktop.gamepad.listener;

import net.java.games.input.Controller;

public abstract class ControllerListener implements Runnable {

    protected Controller controller;
    protected int delay;

    protected static boolean handlersEnabled;

    public ControllerListener(Controller controller, int delay) {
        this.controller = controller;
        this.delay = delay;
        handlersEnabled = false;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            controller.poll();
            processPollData();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public static void toggleHandlers() {
        handlersEnabled ^= true;
        System.out.println("Handlers have been " + (handlersEnabled ? "enabled" : "disabled"));
    }

    protected abstract void processPollData();
}
