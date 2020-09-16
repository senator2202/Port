package com.kharitonov.port.state.impl;

import com.kharitonov.port.entity.Dock;
import com.kharitonov.port.entity.SeaPort;
import com.kharitonov.port.entity.Ship;
import com.kharitonov.port.state.AbstractState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class LeavingState implements AbstractState {
    private static final LeavingState INSTANCE = new LeavingState();
    private static final Logger LOGGER = LogManager.getLogger(LeavingState.class);
    private static final SeaPort PORT = SeaPort.getInstance();
    private static final int LEAVING_DURATION = 4;

    private LeavingState() {
    }

    public static LeavingState getInstance() {
        return INSTANCE;
    }

    @Override
    public void loadContainers(Ship ship) {
        LOGGER.warn("Invalid action, ship {} is in leaving state!", ship.getShipId());
    }

    @Override
    public void unloadContainers(Ship ship) {
        LOGGER.warn("Invalid action, ship {} is in leaving state!", ship.getShipId());
    }

    @Override
    public void requestDock(Ship ship) {
        LOGGER.warn("Invalid action, ship {} is in leaving state!", ship.getShipId());
    }

    @Override
    public void leaveDock(Ship ship) {
        LOGGER.info("Ship {} is leaving dock № {}", ship.getShipId(), ship.getDockId());
        SeaPort.PortDispatcher dispatcher = PORT.getDispatcher();
        Dock dock = PORT.getUsingDock(ship.getDockId());
        try {
            TimeUnit.SECONDS.sleep(LEAVING_DURATION);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ship {} has left dock № {}!", ship.getShipId(), ship.getDockId());
        dispatcher.leaveDock(dock);
    }

    @Override
    public void moorToDock(Ship ship) {
        LOGGER.warn("Invalid action, ship {} is in leaving state!", ship.getShipId());
    }
}
