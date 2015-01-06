package com.ghosty.gamepad.desktop.listener;

import com.ghosty.gamepad.desktop.handler.MouseMoveEmulator;
import com.ghosty.gamepad.desktop.handler.WheelScrollEmulator;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Controller;

import java.util.stream.Stream;

public class ContinuousListener implements ControllerListener {

    private Controller controller;

    private Component xAxis;
    private Component yAxis;
    private Component zAxis;
    private MouseMoveEmulator eventHandler;
    private WheelScrollEmulator scrollEmulator;

    public ContinuousListener(Controller controller) {
        this.controller = controller;
        this.xAxis = controller.getComponent(Axis.X);
        this.yAxis = controller.getComponent(Axis.Y);
        this.zAxis = controller.getComponent(Axis.Z);
        this.eventHandler = new MouseMoveEmulator();
        this.scrollEmulator = new WheelScrollEmulator();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            controller.poll();
            eventHandler.handle(xAxis);
            eventHandler.handle(yAxis);
            scrollEmulator.handle(zAxis);
            try {
                float maxPollData = Stream.of(xAxis, yAxis, zAxis)
                        .map(Component::getPollData)
                        .map(Math::abs)
                        .max(Float::compareTo)
                        .orElse(0F);
                long delay = (long) (maxPollData * 50);
                Thread.sleep(delay);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
