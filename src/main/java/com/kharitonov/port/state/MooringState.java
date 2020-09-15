package com.kharitonov.port.state;

import com.kharitonov.port.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class MooringState extends AbstractState {
    private static final Logger LOGGER =
            LogManager.getLogger(MooringState.class);
    private static final int MOORING_DURATION = 4;

    public MooringState(Ship ship) {
        super(ship);
    }

    @Override
    public void loadContainers() {
        LOGGER.error("Invalid action, ship {}: unable to load containers in mooring state!", ship.getShipId());
    }

    @Override
    public void unloadContainers() {
        LOGGER.error("Invalid action, ship {}: unable to unload containers in mooring state!", ship.getShipId());
    }

    @Override
    public void requestDock() {
        LOGGER.error("Invalid action, ship {}: dock was already requested!", ship.getShipId());
    }

    @Override
    public void leaveDock() {
        LOGGER.error("Invalid action, ship {} is in mooring state!", ship.getShipId());
    }

    @Override
    public void moorToDock() {
        LOGGER.info("Ship {} is mooring to dock № {}", ship.getShipId(), ship.getDockId());
        try {
            TimeUnit.SECONDS.sleep(MOORING_DURATION);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ship {} arrived to the dock № {}", ship.getShipId(), ship.getDockId());
        ship.setState(new LoadingState(ship));

    }

}
