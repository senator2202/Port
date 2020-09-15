package com.kharitonov.port.state;

import com.kharitonov.port.entity.CargoContainer;
import com.kharitonov.port.entity.SeaPort;
import com.kharitonov.port.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class LoadingState extends AbstractState {
    private static final Logger LOGGER = LogManager.getLogger(LoadingState.class);
    private static final SeaPort PORT = SeaPort.getInstance();
    private static final int LOAD_NUMBER = 5;
    private static final int LOAD_DURATION = 1;

    public LoadingState(Ship ship) {
        super(ship);
    }

    @Override
    public void loadContainers() {
        for (int i = 0; i < LOAD_NUMBER; i++) {
            Optional<CargoContainer> optional = PORT.getContainer();
            if (optional.isPresent()) {
                CargoContainer container = optional.get();
                ship.loadContainer(container);
                LOGGER.info("Container {} was moved from warehouse to ship {}, warehouse size is {}",
                        container.getContainerId(), ship.getShipId(), PORT.warehouseSize());
                try {
                    TimeUnit.SECONDS.sleep(LOAD_DURATION);
                } catch (InterruptedException e) {
                    LOGGER.error(e);
                }
            }
        }
        ship.setState(new LeavingState(ship));
    }

    @Override
    public void unloadContainers() {
        while (ship.getSize() != 0) {
            Optional<CargoContainer> optional = ship.unloadContainer(0);
            if (optional.isPresent()) {
                CargoContainer container = optional.get();
                PORT.addContainer(container);
                LOGGER.info("Container {} was moved from ship {} to warehouse, warehouse size is {}",
                        container.getContainerId(), ship.getShipId(), PORT.warehouseSize());
                try {
                    TimeUnit.SECONDS.sleep(LOAD_DURATION);
                } catch (InterruptedException e) {
                    LOGGER.error(e);
                }
            }
        }
    }

    @Override
    public void requestDock() {
        LOGGER.error("Dock was already requested!");
    }

    @Override
    public void leaveDock() {
        LOGGER.error("Invalid action, ship {} is in loading state!", ship.getShipId());
    }

    @Override
    public void moorToDock() {
        LOGGER.error("Invalid action, ship {} is in loading state!", ship.getShipId());
    }
}
