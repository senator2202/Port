package com.kharitonov.port.entity;

import com.kharitonov.port.exception.ResourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Port {
    private static final Logger LOGGER = LogManager.getLogger(Port.class);
    private static final int DOCKS_NUMBER = 5;
    private static final int WAREHOUSE_CAPACITY = 100;
    private static final Port INSTANCE = new Port();
    private final Semaphore dockSemaphore = new Semaphore(DOCKS_NUMBER, true);
    private List<CargoContainer> warehouse = new ArrayList<>();
    private List<Dock> freeDocks;
    private List<Dock> usedDocks;

    private Port() {
        usedDocks = new ArrayList<>();
        freeDocks = new ArrayList<>();
        for (int i = 0; i < DOCKS_NUMBER; i++) {
            freeDocks.add(new Dock(i+1));
        }
    }

    public static Port getInstance() {
        return INSTANCE;
    }

    public PortDispatcher getDispatcher() {
        return new PortDispatcher();
    }

    public class PortDispatcher {
        public Dock requestDock(Ship ship) throws ResourceException {
            Dock dock;
            try {
                LOGGER.info("Ship № {} is requesting a dock.", ship.getShipId());
                dockSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new ResourceException(e);
            }
            dock = freeDocks.remove(0);
            dock.setShip(ship);
            usedDocks.add(dock);
            LOGGER.info("Ship № " + ship.getShipId() + " got dock № " + dock.getDockId());
            return dock;
        }

        public void requestLeaving(Ship ship) {
            synchronized (usedDocks) {
                for (Dock dock : usedDocks) {
                    if (dock.getShip().get().getId() == ship.getId()) {
                        releaseDock(dock);
                    }
                }
            }
        }
        public void releaseDock(Dock dock) {
            if (usedDocks.remove(dock)) {
                Ship ship = dock.getShip().get();
                dock.removeShip();
                freeDocks.add(dock);
                LOGGER.info("Ship № " + ship.getId() + " went from dock № " + dock.getDockId());
                dockSemaphore.release();
            }
        }
    }
}
