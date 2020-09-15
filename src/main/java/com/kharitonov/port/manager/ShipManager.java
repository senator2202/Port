package com.kharitonov.port.manager;

import com.kharitonov.port.entity.Ship;

public class ShipManager extends Thread {
    private final Ship ship;

    public ShipManager(Ship ship) {
        this.ship = ship;
    }

    @Override
    public void run() {
        ship.getState().requestDock();
        ship.getState().moorToDock();
        ship.getState().unloadContainers();
        ship.getState().loadContainers();
        ship.getState().leaveDock();
    }
}
