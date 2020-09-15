package com.kharitonov.port.entity;

import com.kharitonov.port.exception.ResourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.Semaphore;

public class SeaPort {
    private static final Logger LOGGER = LogManager.getLogger(SeaPort.class);
    private static final SeaPort INSTANCE = new SeaPort();
    private static final int DOCKS_NUMBER = 3;
    private static final int WAREHOUSE_CAPACITY = 40;
    private final Semaphore dockSemaphore = new Semaphore(DOCKS_NUMBER, true);
    private final List<Dock> allDocks;
    private final Queue<Dock> freeDocks;
    private final List<CargoContainer> warehouse;

    private SeaPort() {
        warehouse = new ArrayList<>();
        freeDocks = new LinkedList<>();
        allDocks = new ArrayList<>();
        for (int i = 1; i <= DOCKS_NUMBER; i++) {
            Dock dock = new Dock(i);
            freeDocks.add(dock);
            allDocks.add(dock);
        }
    }

    public static SeaPort getInstance() {
        return INSTANCE;
    }

    public PortDispatcher getDispatcher() {
        return new PortDispatcher();
    }

    public Optional<CargoContainer> getContainer() {
        Optional<CargoContainer> optional = Optional.empty();
        if (!warehouse.isEmpty()) {
            optional = Optional.of(warehouse.remove(0));
        }
        return optional;
    }

    public void addContainer(CargoContainer container) {
        if (warehouse.size() < WAREHOUSE_CAPACITY) {
            warehouse.add(container);
        } else {
            LOGGER.warn("Unable to add cargo container, warehouse is full!");
        }
    }

    public int warehouseSize() {
        return warehouse.size();
    }

    public Dock getDock(int dockId) {
        return allDocks.stream().filter(d -> d.getDockId() == dockId).findFirst().get();
    }

    public class PortDispatcher {
        public void requestDock(Ship ship) throws ResourceException {
            Dock dock;
            try {
                LOGGER.info("Ship № {} is requesting a dock, {} docks available, {} ships in queue",
                        ship.getShipId(), freeDocks.size(),
                        dockSemaphore.getQueueLength());
                dockSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new ResourceException(e);
            }
            dock = freeDocks.poll();
            dock.setShip(ship);
            ship.setDockId(dock.getDockId());
            LOGGER.info("Ship № {} got a dock № {}, {} docks available, {} ships in queue",
                    ship.getShipId(), dock.getDockId(),
                    freeDocks.size(),
                    dockSemaphore.getQueueLength());
        }

        public void leaveDock(Dock dock) {
            int shipId = dock.getShip().get().getShipId();
            freeDocks.offer(dock);
            LOGGER.info("Ship № {} left dock № {}", shipId, dock.getDockId());
            dockSemaphore.release();
        }
    }
}
