package com.ghosty.gamepad.desktop.listener;

import com.ghosty.gamepad.desktop.handler.AxisEventHandler;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Controller;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class AxisListener implements Runnable {

    private Controller controller;

    private Component xAxis;
    private Component yAxis;
    private AxisEventHandler eventHandler;

    public AxisListener(Controller controller) {
        this.controller = controller;
        this.xAxis = controller.getComponent(Axis.X);
        this.yAxis = controller.getComponent(Axis.Y);
        this.eventHandler = new AxisEventHandler();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            controller.poll();
            eventHandler.handle(xAxis);
            eventHandler.handle(yAxis);
            try {
                long delay = (long) (max(abs(xAxis.getPollData()), abs(yAxis.getPollData())) * 50);
                Thread.sleep(delay);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
