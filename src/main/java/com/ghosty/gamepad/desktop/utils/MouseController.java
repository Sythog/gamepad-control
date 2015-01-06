package com.ghosty.gamepad.desktop.utils;

import java.awt.*;
import java.awt.event.InputEvent;

public final class MouseController {

    private static MouseController instance;

    private final Robot robot;

    private MouseController() throws AWTException {
        this.robot = new Robot();
    }

    public static synchronized MouseController getInstance() {
        if (instance == null) {
            try {
                instance = new MouseController();
            } catch (AWTException e) {
                System.out.println("FATAL error. Cannot create instance of MouseController");
                throw new RuntimeException("Cannot create instance of MouseController");
            }
        }
        return instance;
    }

    public void moveMouse(MouseMove move) {
        Point currentMousePosition = MouseInfo.getPointerInfo().getLocation();
        switch (move.getDirection()) {
            case UP:
                robot.mouseMove((int) currentMousePosition.getX(), ((int) currentMousePosition.getY() + move.getSpeed()));
                break;
            case DOWN:
                robot.mouseMove((int) currentMousePosition.getX(), ((int) currentMousePosition.getY() - move.getSpeed()));
                break;
            case LEFT:
                robot.mouseMove((int) currentMousePosition.getX() - move.getSpeed(), ((int) currentMousePosition.getY()));
                break;
            case RIGHT:
                robot.mouseMove((int) currentMousePosition.getX() + move.getSpeed(), ((int) currentMousePosition.getY()));
                break;
        }
    }

    public void mouseLeftPress() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void mouseLeftRelease() {
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void mouseRightPress() {
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
    }

    public void mouseRightRelease() {
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public void mouseScrollDown() {
        robot.mouseWheel(1);
    }

    public void mouseScrollUp() {
        robot.mouseWheel(-1);
    }
}
