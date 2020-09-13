package com.kharitonov.port.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Random;

public class PortTest {
    private static final Logger LOGGER = LogManager.getLogger(PortTest.class);

    @Test
    public void testGetInstance() {
        for (int i = 1; i < 30; i++) {
            DockRequest request;
            Ship ship = new Ship(i, 20);
            ship.loadContainer(new CargoContainer(1 * i, 100));
            ship.loadContainer(new CargoContainer(2 * i, 100));
            ship.loadContainer(new CargoContainer(3 * i, 200));
            request = new DockRequest(ship);
            try {
                Thread.sleep((new Random().nextInt(5) + 1) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            request.start();
        }
        while (Thread.activeCount() > 1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}