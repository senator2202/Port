package com.kharitonov.port.generator;

import com.kharitonov.port.entity.CargoContainer;
import com.kharitonov.port.entity.DockRequest;
import com.kharitonov.port.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DockRequestGenerator{
    private static final Logger LOGGER =
            LogManager.getLogger(DockRequestGenerator.class);

    public void generateRequests() {
        for (int i = 1; i < 30; i++) {
            DockRequest request;
            Ship ship = new Ship(i, 20);
            ship.loadContainer(new CargoContainer(1 * i, 100));
            ship.loadContainer(new CargoContainer(2 * i, 100));
            ship.loadContainer(new CargoContainer(3 * i, 200));
            request = new DockRequest(ship);
            try {
                long duration = (long) new Random().nextInt(2) + 1;
                TimeUnit.SECONDS.sleep(duration);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
            request.start();
        }
        while (Thread.activeCount() > 2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
    }
}
