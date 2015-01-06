package com.ghosty.gamepad.desktop.handler;

import com.ghosty.gamepad.desktop.listener.ControllerListener;
import net.java.games.input.Component;

import static com.ghosty.gamepad.desktop.utils.ApplicationUtils.RELEASED;

public class StartButtonHandler implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        if (event.getPollData() == RELEASED) {
            ControllerListener.toggleHandlers();
        }
    }
}
