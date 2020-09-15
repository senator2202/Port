package com.kharitonov.port.state;

import com.kharitonov.port.entity.Ship;

public abstract class AbstractState {
    protected final Ship ship;

    protected AbstractState(Ship ship) {
        this.ship = ship;
    }

    public abstract void loadContainers();

    public abstract void unloadContainers();

    public abstract void requestDock();

    public abstract void leaveDock();

    public abstract void moorToDock();

}
