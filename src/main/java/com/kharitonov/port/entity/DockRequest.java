package com.kharitonov.port.entity;

import com.kharitonov.port.exception.ResourceException;

import java.util.Random;

public class DockRequest extends Thread {
    private static final Port PORT = Port.getInstance();
    private Ship ship;

    public DockRequest(Ship ship) {
        this.ship = ship;
    }

    @Override
    public void run() {
        Port.PortDispatcher dispatcher = PORT.getDispatcher();
        Dock dock = null;
        try {
            dock = dispatcher.requestDock(ship);
            Thread.sleep((new Random().nextInt(5) + 5) * 1000);
        } catch (ResourceException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            dispatcher.requestLeaving(dock);
        }
    }
}
