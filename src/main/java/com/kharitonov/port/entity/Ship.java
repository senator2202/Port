package com.kharitonov.port.entity;

import com.kharitonov.port.exception.ResourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Ship extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(Ship.class);
    private int shipId;
    private int capacity;
    private List<CargoContainer> containers = new ArrayList<>();

    public Ship(int shipId, int capacity) {
        this.shipId = shipId;
        this.capacity = capacity;
    }

    public int getShipId() {
        return shipId;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public void run() {
        LOGGER.info("Ship {} started.", shipId);
        Port port = Port.getInstance();
        Port.PortDispatcher dispatcher = port.getDispatcher();
        Random random = new Random();
        try {
            Dock dock = dispatcher.requestDock(this);
            //Thread.sleep(1000 * (random.nextInt(10) + 2));
        } catch (/*InterruptedException | */ResourceException e) {
            LOGGER.error(e);
        }
        dispatcher.requestLeaving(this);
    }

    public boolean loadContainer(CargoContainer container) {
        boolean result;
        if (containers.size() < capacity) {
            containers.add(container);
            result = true;
        } else {
            LOGGER.warn("Unable to load cargo container, ship is full!");
            result = false;
        }
        return result;
    }

    public Optional<CargoContainer> unloadContainer() {
        Optional<CargoContainer> optional = Optional.empty();
        if (!containers.isEmpty()) {
            optional = Optional.of(containers.remove(0));
        }
        return optional;
    }
}
