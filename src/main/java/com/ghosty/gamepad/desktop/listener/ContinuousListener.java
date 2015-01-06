package com.ghosty.gamepad.desktop.listener;

import com.ghosty.gamepad.desktop.handler.ControllerEventHandler;
import com.ghosty.gamepad.desktop.handler.MouseMoveEmulator;
import com.ghosty.gamepad.desktop.handler.WheelScrollEmulator;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Controller;

public class ContinuousListener extends ControllerListener {

    public ContinuousListener(Controller controller) {
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
            ControllerEventHandler moveEmulator = new MouseMoveEmulator();
            ControllerEventHandler scrollEmulator = new WheelScrollEmulator();
            if (handlersEnabled) {
                moveEmulator.handle(xAxis);
                moveEmulator.handle(yAxis);
                scrollEmulator.handle(zAxis);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
