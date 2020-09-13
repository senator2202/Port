package com.kharitonov.port.entity;

import com.kharitonov.port.exception.ResourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Port {
    private static final Logger LOGGER = LogManager.getLogger(Port.class);
    private static final int DOCKS_NUMBER = 5;
    private static final int WAREHOUSE_CAPACITY = 100;
    private static final Port INSTANCE = new Port();
    private final Semaphore dockSemaphore = new Semaphore(DOCKS_NUMBER, true);
    private List<CargoContainer> warehouse = new ArrayList<>();
    private Queue<Dock> freeDocks;
    private List<Dock> usedDocks;

    private Port() {
        //usedDocks = new ArrayList<>();
        freeDocks = new LinkedList<>();
        for (int i = 0; i < DOCKS_NUMBER; i++) {
            freeDocks.add(new Dock(i + 1));
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
                LOGGER.info("Ship № {} is requesting a dock, {} docks available, {} ships in queue",
                        ship.getShipId(), dockSemaphore.availablePermits(),
                        dockSemaphore.getQueueLength());
                dockSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new ResourceException(e);
            }
            dock = freeDocks.poll();
            //usedDocks.add(dock);
            dock.setShip(ship);
            LOGGER.info("Ship № {} got a dock № {}, {} docks available, {} ships in queue",
                    ship.getShipId(), dock.getDockId(),
                    dockSemaphore.getQueueLength(),
                    dockSemaphore.availablePermits());
            return dock;
        }

        public void requestLeaving(Dock dock) {
            int shipId = dock.getShip().get().getShipId();
            freeDocks.offer(dock);
            dockSemaphore.release();
            LOGGER.info("Ship № {} left dock № {}, {} docks available",
                    shipId, dock.getDockId(), dockSemaphore.availablePermits());
        }
    }
}
