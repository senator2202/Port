package com.kharitonov.port.state;

import com.kharitonov.port.entity.Dock;
import com.kharitonov.port.entity.SeaPort;
import com.kharitonov.port.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class LeavingState extends AbstractState {
    private static final Logger LOGGER = LogManager.getLogger(LeavingState.class);
    private static final SeaPort PORT = SeaPort.getInstance();
    private static final int LEAVING_DURATION = 4;

    public LeavingState(Ship ship) {
        super(ship);
    }

    @Override
    public void loadContainers() {
        LOGGER.error("Invalid action, ship {} is in leaving state!", ship.getShipId());
    }

    @Override
    public void unloadContainers() {
        LOGGER.error("Invalid action, ship {} is in leaving state!", ship.getShipId());
    }

    @Override
    public void requestDock() {
        LOGGER.error("Invalid action, ship {} is in leaving state!", ship.getShipId());
    }

    @Override
    public void leaveDock() {
        LOGGER.info("Ship {} is leaving dock № {}", ship.getShipId(), ship.getDockId());
        SeaPort.PortDispatcher dispatcher = PORT.getDispatcher();
        Dock dock = PORT.getDock(ship.getDockId());
        try {
            TimeUnit.SECONDS.sleep(LEAVING_DURATION);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ship {} has left dock № {}!", ship.getShipId(), ship.getDockId());
        dispatcher.leaveDock(dock);
    }

    @Override
    public void moorToDock() {
        LOGGER.error("Invalid action, ship {} is in leaving state!", ship.getShipId());
    }
}
