package com.kharitonov.port.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Ship {
    private static final Logger LOGGER = LogManager.getLogger(Ship.class);
    private int shipId;
    private int capacity;
    private int dockId = -1;
    private List<CargoContainer> containers = new ArrayList<>();

    public Ship(int shipId, int capacity) {
        this.shipId = shipId;
        this.capacity = capacity;
    }

    public int getShipId() {
        return shipId;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDockId() {
        return dockId;
    }

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    /*@Override
    public void run() {
        LOGGER.info("Ship {} started.", shipId);
        Port port = Port.getInstance();
        Port.PortDispatcher dispatcher = port.getDispatcher();
        Random random = new Random();
        try {
            Dock dock = dispatcher.requestDock(this);
            Thread.sleep(1000 * (random.nextInt(10) + 2));
        } catch (InterruptedException | ResourceException e) {
            LOGGER.error(e);
        }
        dispatcher.requestLeaving(this);
    }*/

    public boolean loadContainer(CargoContainer container) {
        boolean result;
        if (containers.size() < capacity) {
            containers.add(container);
            result = true;
        } else {
            LOGGER.warn("Unable to load cargo container, ship is full!");
            result = false;
        }
        return result;
    }

    public Optional<CargoContainer> unloadContainer() {
        Optional<CargoContainer> optional = Optional.empty();
        if (!containers.isEmpty()) {
            optional = Optional.of(containers.remove(0));
        }
        return optional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (shipId != ship.shipId) return false;
        if (capacity != ship.capacity) return false;
        if (dockId != ship.dockId) return false;
        return containers != null ? containers.equals(ship.containers) : ship.containers == null;
    }

    @Override
    public int hashCode() {
        int result = shipId;
        result = 31 * result + capacity;
        result = 31 * result + dockId;
        result = 31 * result + (containers != null ? containers.hashCode() : 0);
        return result;
    }
}
