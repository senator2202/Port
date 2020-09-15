package com.kharitonov.port.generator;

import com.kharitonov.port.entity.Ship;
import com.kharitonov.port.manager.ShipManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DockRequestGenerator {
    private static final Logger LOGGER =
            LogManager.getLogger(DockRequestGenerator.class);
    private static final int DURATION = 2;
    private static final long MIN_DURATION = 1;
    private static final int NORMAL_THREAD_COUNT = 2;

    public void generateRequests(List<Ship> ships) {
        for (Ship ship : ships) {
            ShipManager manager = new ShipManager(ship);
            try {
                long duration = new Random().nextInt(DURATION) + MIN_DURATION;
                TimeUnit.SECONDS.sleep(duration);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
            manager.start();
        }
        while (Thread.activeCount() > NORMAL_THREAD_COUNT) {
            try {
                TimeUnit.SECONDS.sleep(DURATION);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
    }
}
