package com.kharitonov.port.entity;

import com.kharitonov.port.state.AbstractState;
import com.kharitonov.port.state.ArrivingState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Ship {
    private static final Logger LOGGER = LogManager.getLogger(Ship.class);
    private final int shipId;
    private final int capacity;
    private AbstractState state;
    private int dockId = -1;
    private List<CargoContainer> containers = new ArrayList<>();

    public Ship(int shipId, int capacity) {
        this.shipId = shipId;
        this.capacity = capacity;
        state = new ArrivingState(this);
    }

    public Ship(int shipId, int capacity, List<CargoContainer> containers) {
        this.shipId = shipId;
        this.capacity = capacity;
        this.containers = containers;
        state = new ArrivingState(this);
    }

    public AbstractState getState() {
        return state;
    }

    public void setState(AbstractState state) {
        this.state = state;
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

    public int getSize() {
        return containers.size();
    }

    public void loadContainer(CargoContainer container) {
        if (containers.size() < capacity) {
            containers.add(container);
        } else {
            LOGGER.warn("Unable to load cargo container, ship is full!");
        }
    }

    public Optional<CargoContainer> unloadContainer(int index) {
        CargoContainer container = containers.remove(index);
        return container == null
                ? Optional.empty()
                : Optional.of(container);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        if (shipId != ship.shipId) {
            return false;
        }
        if (capacity != ship.capacity) {
            return false;
        }
        if (dockId != ship.dockId) {
            return false;
        }
        return containers != null
                ? containers.equals(ship.containers)
                : ship.containers == null;
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
