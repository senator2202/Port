package com.kharitonov.port.entity;

import com.kharitonov.port.exception.ResourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SeaPort {
    private static final Logger LOGGER = LogManager.getLogger(SeaPort.class);
    private static final SeaPort INSTANCE = new SeaPort();
    private static final Warehouse WAREHOUSE = Warehouse.getInstance();
    private static final int DOCKS_NUMBER = 3;
    private final PortDispatcher dispatcher = new PortDispatcher();
    private final Semaphore dockSemaphore = new Semaphore(DOCKS_NUMBER, true);
    private final Lock warehouseLock = new ReentrantLock();
    private final Queue<Dock> freeDocks;
    private final List<Dock> usedDocks;


    private SeaPort() {
        freeDocks = new LinkedList<>();
        usedDocks = new ArrayList<>();
        for (int i = 1; i <= DOCKS_NUMBER; i++) {
            Dock dock = new Dock(i);
            freeDocks.add(dock);
        }
    }

    public static SeaPort getInstance() {
        return INSTANCE;
    }

    public PortDispatcher getDispatcher() {
        return dispatcher;
    }

    public Optional<CargoContainer> getContainer() {
        warehouseLock.lock();
        Optional<CargoContainer> optional = WAREHOUSE.getContainer();
        warehouseLock.unlock();
        return optional;
    }

    public void addContainer(CargoContainer container) {
        warehouseLock.lock();
        WAREHOUSE.addContainer(container);
        warehouseLock.unlock();
    }

    public int warehouseSize() {
        return WAREHOUSE.size();
    }

    public Dock getUsingDock(int dockId) {
        return usedDocks.stream().filter(d -> d.getDockId() == dockId).findFirst().get();
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
            usedDocks.add(dock);
            dock.setShip(ship);
            ship.setDockId(dock.getDockId());
            LOGGER.info("Ship № {} got a dock № {}, {} docks available, {} ships in queue",
                    ship.getShipId(), dock.getDockId(),
                    freeDocks.size(),
                    dockSemaphore.getQueueLength());
        }

        public void leaveDock(Dock dock) {
            Optional<Ship> optionalShip = dock.getShip();
            if (optionalShip.isPresent()) {
                Ship ship = optionalShip.get();
                int shipId = ship.getShipId();
                dock.removeShip();
                ship.resetDockId();
                if (usedDocks.remove(dock)) {
                    freeDocks.offer(dock);
                    LOGGER.info("Ship № {} left dock № {}", shipId, dock.getDockId());
                    dockSemaphore.release();
                }
            }
        }
    }
}
