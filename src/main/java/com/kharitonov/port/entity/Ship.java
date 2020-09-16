package com.kharitonov.port.entity;

import com.kharitonov.port.state.AbstractState;
import com.kharitonov.port.state.impl.ArrivingState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Ship extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(Ship.class);
    private static final int INVALID_DOCK_ID = -1;
    private final int shipId;
    private final int capacity;
    private AbstractState currentState;
    private int dockId;
    private List<CargoContainer> containers = new ArrayList<>();

    public Ship(int shipId, int capacity) {
        this.shipId = shipId;
        this.capacity = capacity;
        dockId = INVALID_DOCK_ID;
        currentState = ArrivingState.getInstance();
    }

    public Ship(int shipId, int capacity, List<CargoContainer> containers) {
        this.shipId = shipId;
        this.capacity = capacity;
        this.containers = containers;
        dockId = INVALID_DOCK_ID;
        currentState = ArrivingState.getInstance();
    }

    public Object getCurrentState() {
        return currentState;
    }

    public void setCurrentState(AbstractState currentState) {
        this.currentState = currentState;
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

    public void resetDockId() {
        dockId = INVALID_DOCK_ID;
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
        return container == null ? Optional.empty() : Optional.of(container);
    }

    @Override
    public void run() {
        currentState.requestDock(this);
        currentState.moorToDock(this);
        currentState.unloadContainers(this);
        currentState.loadContainers(this);
        currentState.leaveDock(this);
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
        if (!currentState.equals(ship.currentState)) {
            return false;
        }
        return containers.equals(ship.containers);
    }

    @Override
    public int hashCode() {
        int result = shipId;
        result = 31 * result + capacity;
        result = 31 * result + currentState.hashCode();
        result = 31 * result + dockId;
        result = 31 * result + containers.hashCode();
        return result;
    }
}
