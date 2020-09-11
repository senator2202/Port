package com.kharitonov.port.state;

import com.kharitonov.port.entity.CargoContainer;
import com.kharitonov.port.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class WaitingState extends AbstractState {
    private static final Logger LOGGER =
            LogManager.getLogger(WaitingState.class);

    WaitingState(Ship ship) {
        super(ship);
    }

    @Override
    public boolean loadContainer(CargoContainer container) {
        LOGGER.error("Unable to load containers while " +
                "ship is in waiting state!");
        return false;
    }

    @Override
    public Optional<CargoContainer> unloadContainer() {
        LOGGER.error("Unable to unload containers while " +
                "ship is in waiting state!");
        return Optional.empty();
    }

    @Override
    public void requestDock() {
    }
}
