/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.prop;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public final class PropertyManager {

    private static final String APP_PROPERTIES_FILE = "conf/gdc.properties";
    private static final Logger LOG = LoggerFactory.getLogger(PropertyManager.class);

    private static PropertyManager instance;

    private Properties properties = new Properties();
    private Map<String, List<PropertyChangeListener>> listeners = Maps.newHashMap();

    private PropertyManager() {
    }

    public synchronized static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
            instance.loadProperties();
        }
        return (instance);
    }

    private void loadProperties() {
        try {
            FileInputStream in = new FileInputStream(APP_PROPERTIES_FILE);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            LOG.error("{}", e);
        }
    }

    public void storeProperties() {
        try {
            FileOutputStream out = new FileOutputStream(APP_PROPERTIES_FILE);
            properties.store(out, "---Gamepad Desktop Control properties---");
            out.close();
        } catch (IOException e) {
            LOG.error("{}", e);
        }
    }

    public String getProperty(String key) {
        if (key != null && properties != null) {
            return properties.getProperty(key);
        }
        return null;
    }

    /**
     * Sets Application/User String properties; default property values cannot be set.
     */
    public void setProperty(String key, String val) {
        String oldValue = getProperty(key);
        properties.setProperty(key, val);
        if (listeners.containsKey(key)) {
            List<PropertyChangeListener> propListeners = listeners.get(key);
            if (propListeners.size() > 0) {
                PropertyChangeEvent event = new PropertyChangeEvent(this, key, oldValue, val);
                propListeners.stream().forEach(listener -> listener.propertyChange(event));
            }
        }
    }

    public void addListener(String key, PropertyChangeListener listener) {
        List<PropertyChangeListener> propListeners;
        if (!listeners.containsKey(key)) {
            propListeners = Lists.newArrayList();
            propListeners.add(listener);
            listeners.put(key, propListeners);
        } else {
            propListeners = listeners.get(key);
            propListeners.add(listener);
        }
    }
}