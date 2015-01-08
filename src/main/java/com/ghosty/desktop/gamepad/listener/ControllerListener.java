package com.ghosty.desktop.gamepad.listener;

import net.java.games.input.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ControllerListener implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerListener.class);

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
        LOG.info("Handlers have been " + (handlersEnabled ? "enabled" : "disabled"));
    }

    protected abstract void processPollData();
}
