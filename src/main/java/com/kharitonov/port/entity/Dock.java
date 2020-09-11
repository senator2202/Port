package com.kharitonov.port.entity;

import java.util.Optional;

public class Dock {
    private int dockId;
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

    public void removeShip() {
        ship = null;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
