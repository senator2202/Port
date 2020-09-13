package com.kharitonov.port.entity;

import com.kharitonov.port.exception.ResourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DockRequest extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(DockRequest.class);
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
            long duration = (long) new Random().nextInt(5) + 7;
            dock = dispatcher.requestDock(ship);
            TimeUnit.SECONDS.sleep(duration);
        } catch (ResourceException | InterruptedException e) {
            LOGGER.error(e);
        } finally {
            dispatcher.requestLeaving(dock);
        }
    }
}
