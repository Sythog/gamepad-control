package com.ghosty.gamepad.desktop.utils;

public class MouseMove {

    private Direction direction;
    private int speed;

    public MouseMove() {
    }

    public MouseMove(Direction direction, int speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "MouseMove{" +
                "direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}
