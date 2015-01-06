package com.ghosty.gamepad.desktop.listener;

import com.ghosty.gamepad.desktop.handler.ControllerEventHandler;
import com.ghosty.gamepad.desktop.handler.LeftMouseClickEmulator;
import com.ghosty.gamepad.desktop.handler.RightMouseClickEmulator;
import com.ghosty.gamepad.desktop.handler.StartButtonHandler;
import com.google.common.collect.ImmutableMap;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller;
import net.java.games.input.Event;

import java.util.Map;

public class EventBasedListener extends ControllerListener {

    private static final Map<Identifier, ControllerEventHandler> BUTTONS_HANDLER_MAP =
            ImmutableMap.<Identifier, ControllerEventHandler>builder()
                    .put(Button._0, new LeftMouseClickEmulator())       // A
                    .put(Button._1, new RightMouseClickEmulator())      // B
                    .put(Button._7, new StartButtonHandler())           // Start button
                    .build();

    public EventBasedListener(Controller controller) {
        super(controller);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            controller.poll();
            Event event = new Event();
            while (controller.getEventQueue().getNextEvent(event)) {
                Identifier id = event.getComponent().getIdentifier();
                if (id instanceof Button && (id.equals(Button._7) || handlersEnabled)) {
                    ControllerEventHandler handler = BUTTONS_HANDLER_MAP.get(id);
                    if (handler != null) {
                        handler.handle(event.getComponent());
                    }
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
