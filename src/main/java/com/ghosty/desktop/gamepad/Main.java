package com.ghosty.desktop.gamepad;

import com.ghosty.desktop.gamepad.utils.ApplicationUtils;
import net.java.games.input.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException, AWTException {
        Controller gamepad = ApplicationUtils.findGamepad();
        LOG.info("Press Start to use your controller");
        ApplicationUtils.startListeners(gamepad);
    }
}
