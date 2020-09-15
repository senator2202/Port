package com.kharitonov.port.state;

import com.kharitonov.port.entity.SeaPort;
import com.kharitonov.port.entity.Ship;
import com.kharitonov.port.exception.ResourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrivingState extends AbstractState {
    private static final Logger LOGGER = LogManager.getLogger(ArrivingState.class);
    private static final SeaPort.PortDispatcher DISPATCHER =
            SeaPort.getInstance().getDispatcher();

    public ArrivingState(Ship ship) {
        super(ship);
    }

    @Override
    public void loadContainers() {
        LOGGER.error("Unable to load containers in arriving state!");
    }

    @Override
    public void unloadContainers() {
        LOGGER.error("Unable to unload containers in arriving state!");
    }

    @Override
    public void requestDock() {
        try {
            DISPATCHER.requestDock(ship);
        } catch (ResourceException e) {
            LOGGER.error(e);
        }
        ship.setState(new MooringState(ship));
    }

    @Override
    public void leaveDock() {
        LOGGER.error("Invalid action, ship {} is in arriving state!", ship.getShipId());
    }

    @Override
    public void moorToDock() {
        LOGGER.error("Invalid action, ship {} is in arriving state!", ship.getShipId());
    }
}
