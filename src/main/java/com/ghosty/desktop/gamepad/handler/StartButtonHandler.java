package com.ghosty.desktop.gamepad.handler;

import com.ghosty.desktop.gamepad.listener.ControllerListener;
import com.ghosty.desktop.gamepad.utils.ApplicationUtils;
import net.java.games.input.Component;

public class StartButtonHandler implements ControllerEventHandler {

    @Override
    public void handle(Component event) {
        if (event.getPollData() == ApplicationUtils.RELEASED) {
            ControllerListener.toggleHandlers();
        }
    }
}
