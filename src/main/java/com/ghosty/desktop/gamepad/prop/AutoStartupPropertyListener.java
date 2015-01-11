/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.prop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AutoStartupPropertyListener implements PropertyChangeListener {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    // TODO Implement
    public void propertyChange(PropertyChangeEvent evt) {
        LOG.info("Property value changes");
    }
}
