/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrayIconDoubleMouseClickListener extends MouseAdapter {

    private Settings settingsWindow = Settings.getInstance();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            settingsWindow.setVisible(!settingsWindow.isVisible());
        }
    }
}
