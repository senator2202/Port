package com.kharitonov.port.state.impl;

import com.kharitonov.port.entity.SeaPort;
import com.kharitonov.port.entity.Ship;
import com.kharitonov.port.exception.ResourceException;
import com.kharitonov.port.state.AbstractState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrivingState implements AbstractState {
    private static final ArrivingState INSTANCE = new ArrivingState();
    private static final Logger LOGGER = LogManager.getLogger(ArrivingState.class);
    private static final SeaPort.PortDispatcher DISPATCHER =
            SeaPort.getInstance().getDispatcher();

    private ArrivingState() {

    }

    public static ArrivingState getInstance() {
        return INSTANCE;
    }

    @Override
    public void loadContainers(Ship ship) {
        LOGGER.warn("Unable to load containers in arriving state!");
    }

    @Override
    public void unloadContainers(Ship ship) {
        LOGGER.warn("Unable to unload containers in arriving state!");
    }

    @Override
    public void requestDock(Ship ship) {
        try {
            DISPATCHER.requestDock(ship);
        } catch (ResourceException e) {
            throw new RuntimeException("Unable to get dock!", e);
        }
        ship.setCurrentState(MooringState.getInstance());
    }

    @Override
    public void leaveDock(Ship ship) {
        LOGGER.warn("Invalid action, ship {} is in arriving state!", ship.getShipId());
    }

    @Override
    public void moorToDock(Ship ship) {
        LOGGER.warn("Invalid action, ship {} is in arriving state!", ship.getShipId());
    }
}
