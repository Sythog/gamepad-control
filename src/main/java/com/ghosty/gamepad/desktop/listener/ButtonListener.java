package com.ghosty.gamepad.desktop.listener;

import com.ghosty.gamepad.desktop.handler.ButtonAEventHandler;
import com.ghosty.gamepad.desktop.handler.ButtonBEventHandler;
import com.ghosty.gamepad.desktop.handler.ControllerEventHandler;
import com.google.common.collect.ImmutableMap;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller;
import net.java.games.input.Event;

import java.util.Map;

public class ButtonListener implements Runnable {

    private static final Map<Identifier, ControllerEventHandler> BUTTONS_HANDLER_MAP =
            ImmutableMap.<Identifier, ControllerEventHandler>builder()
                    .put(Button._0, new ButtonAEventHandler())
                    .put(Button._1, new ButtonBEventHandler())
                    .build();

    private Controller controller;

    public ButtonListener(Controller controller) {
        this.controller = controller;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            controller.poll();
            Event event = new Event();
            while (controller.getEventQueue().getNextEvent(event)) {
                ControllerEventHandler handler = BUTTONS_HANDLER_MAP.get(event.getComponent().getIdentifier());
                if (handler != null) {
                    handler.handle(event.getComponent());
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
