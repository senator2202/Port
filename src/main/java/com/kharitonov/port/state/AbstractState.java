package com.kharitonov.port.state;

import com.kharitonov.port.entity.CargoContainer;
import com.kharitonov.port.entity.Ship;

import java.util.Optional;

public abstract class AbstractState {
    private Ship ship;

    AbstractState(Ship ship) {
        this.ship = ship;
    }

    public abstract boolean loadContainer(CargoContainer container);

    public abstract Optional<CargoContainer> unloadContainer();

    public abstract void requestDock();
}
