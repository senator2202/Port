package com.kharitonov.port.generator;

import com.kharitonov.port.entity.Ship;
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

    public void generateRequests(List<Ship> ships) {
        for (Ship ship : ships) {
            try {
                long duration = new Random().nextInt(DURATION) + MIN_DURATION;
                TimeUnit.SECONDS.sleep(duration);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
            ship.start();
        }
    }
}
