package com.kharitonov.port.entity;

import java.util.Optional;

public class Dock {
    private final int dockId;
    private Ship ship;

    public Dock(int dockId) {
        this.dockId = dockId;
    }

    public int getDockId() {
        return dockId;
    }

    public Optional<Ship> getShip() {
        return ship == null
                ? Optional.empty()
                : Optional.of(ship);
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void removeShip() {
        ship = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dock dock = (Dock) o;
        return dockId == dock.dockId;
    }

    @Override
    public int hashCode() {
        return dockId;
    }
}
