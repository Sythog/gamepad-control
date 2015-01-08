package com.ghosty.desktop.gamepad.model;

public class MouseMove {

    private Direction direction;
    private int speed;

    public MouseMove(Direction direction, int speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "MouseMove{" +
                "direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}
