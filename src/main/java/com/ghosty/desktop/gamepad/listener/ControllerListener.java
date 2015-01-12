/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.listener;

import com.ghosty.desktop.gamepad.handler.ControllerEventHandler;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;

public abstract class ControllerListener implements Runnable {

    protected ControllerListenerContainer container;
    protected Controller controller;
    protected int delay;

    public ControllerListener(ControllerListenerContainer container, int delay) {
        this.container = container;
        this.controller = container.getController();
        this.delay = delay;
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

    protected abstract void processPollData();

    public abstract ControllerEventHandler getHandlerForComponent(Identifier component);

    public abstract void changeComponentToHandle(ControllerEventHandler handler, Identifier component);
}
