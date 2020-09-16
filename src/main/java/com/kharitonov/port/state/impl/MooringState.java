package com.kharitonov.port.state.impl;

import com.kharitonov.port.entity.Ship;
import com.kharitonov.port.state.AbstractState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class MooringState implements AbstractState {
    private static final MooringState INSTANCE = new MooringState();
    private static final Logger LOGGER = LogManager.getLogger(MooringState.class);
    private static final int MOORING_DURATION = 4;

    private MooringState() {
    }

    public static MooringState getInstance() {
        return INSTANCE;
    }

    @Override
    public void loadContainers(Ship ship) {
        LOGGER.warn("Invalid action, ship {}: unable to load containers in mooring state!", ship.getShipId());
    }

    @Override
    public void unloadContainers(Ship ship) {
        LOGGER.warn("Invalid action, ship {}: unable to unload containers in mooring state!", ship.getShipId());
    }

    @Override
    public void requestDock(Ship ship) {
        LOGGER.warn("Invalid action, ship {}: dock was already requested!", ship.getShipId());
    }

    @Override
    public void leaveDock(Ship ship) {
        LOGGER.warn("Invalid action, ship {} is in mooring state!", ship.getShipId());
    }

    @Override
    public void moorToDock(Ship ship) {
        LOGGER.info("Ship {} is mooring to dock № {}", ship.getShipId(), ship.getDockId());
        try {
            TimeUnit.SECONDS.sleep(MOORING_DURATION);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ship {} arrived to dock № {}", ship.getShipId(), ship.getDockId());
        ship.setCurrentState(LoadingState.getInstance());
    }
}
