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
        if (dockId != dock.dockId) {
            return false;
        }
        return ship != null ? ship.equals(dock.ship) : dock.ship == null;
    }

    @Override
    public int hashCode() {
        int result = dockId;
        result = 31 * result + (ship != null ? ship.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dock{");
        sb.append("dockId=").append(dockId);
        sb.append(", ship=").append(ship);
        sb.append('}');
        return sb.toString();
    }
}
