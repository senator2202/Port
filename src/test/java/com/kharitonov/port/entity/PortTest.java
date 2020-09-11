package com.kharitonov.port.entity;

import com.kharitonov.port.exception.ResourceException;
import org.testng.annotations.Test;

public class PortTest {

    @Test
    public void testGetInstance() throws ResourceException {
        for (int i = 1; i < 10; i++) {
            Ship ship = new Ship(i, 20);
            ship.start();
        }
    }
}