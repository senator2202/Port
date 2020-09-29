package com.kharitonov.port.entity.state;

import com.kharitonov.port.entity.Ship;

public interface AbstractState {
    void requestDock(Ship ship);

    void moorToDock(Ship ship);

    void unloadContainers(Ship ship);

    void loadContainers(Ship ship);

    void leaveDock(Ship ship);
}
