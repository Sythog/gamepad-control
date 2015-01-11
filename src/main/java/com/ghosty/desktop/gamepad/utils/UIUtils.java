/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.utils;

import com.ghosty.desktop.gamepad.ui.Settings;
import com.ghosty.desktop.gamepad.ui.TrayIconDoubleMouseClickListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public abstract class UIUtils {

    private static final Logger LOG = LoggerFactory.getLogger(UIUtils.class);

    public static final String APP_NAME = "Gamepad Desktop Control";

    public static TrayIcon TRAY_ICON;

    public static void initializeTrayMenu() throws AWTException {
        if (!SystemTray.isSupported()) {
            LOG.warn("System tray is not supported. Tray icon will not be created");
            return;
        }
        PopupMenu menu = new PopupMenu(APP_NAME);
        Image img = Toolkit.getDefaultToolkit().getImage("img/TrayIcon.PNG");
        TRAY_ICON = new TrayIcon(img, APP_NAME + System.lineSeparator() + "All controllers are disabled", menu);
        MenuItem item = new MenuItem("Settings");
        item.addActionListener(arg -> Settings.getInstance().setVisible(true));
        menu.add(item);
        item = new MenuItem("Exit");
        item.addActionListener(arg -> {
            SystemTray.getSystemTray().remove(TRAY_ICON);
            System.exit(0);
        });
        menu.add(item);
        TRAY_ICON.addMouseListener(new TrayIconDoubleMouseClickListener());
        SystemTray.getSystemTray().add(TRAY_ICON);
    }
}
