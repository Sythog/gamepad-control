package com.ghosty.desktop.gamepad.listener;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Controller;

import static com.ghosty.desktop.gamepad.handler.EventHandlers.MOUSE_MOVE_EMULATOR;
import static com.ghosty.desktop.gamepad.handler.EventHandlers.WHEEL_SCROLL_EMULATOR;

public class ContinuousAxisListener extends ControllerListener {

    public ContinuousAxisListener(Controller controller) {
        super(controller);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            controller.poll();
            Component xAxis = controller.getComponent(Axis.X);
            Component yAxis = controller.getComponent(Axis.Y);
            Component zAxis = controller.getComponent(Axis.Z);      // RT & LT
            if (handlersEnabled) {
                MOUSE_MOVE_EMULATOR.handle(xAxis);
                MOUSE_MOVE_EMULATOR.handle(yAxis);
                WHEEL_SCROLL_EMULATOR.handle(zAxis);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
