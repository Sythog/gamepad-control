package com.ghosty.desktop.gamepad.listener;

import com.ghosty.desktop.gamepad.handler.ControllerEventHandler;
import com.google.common.collect.ImmutableMap;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller;
import net.java.games.input.Event;

import java.util.Map;

import static com.ghosty.desktop.gamepad.handler.EventHandlers.*;

public class EventBasedButtonsListener extends ControllerListener {

    private static final Map<Identifier, ControllerEventHandler> BUTTONS_HANDLER_MAP =
            ImmutableMap.<Identifier, ControllerEventHandler>builder()
                    .put(Button._0, LEFT_MOUSE_CLICK_EMULATOR)       // A
                    .put(Button._1, RIGHT_MOUSE_CLICK_EMULATOR)      // B
                    .put(Button._7, START_BUTTON_HANDLER)            // Start button
                    .build();

    public EventBasedButtonsListener(Controller controller) {
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
