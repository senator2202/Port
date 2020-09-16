package com.kharitonov.port.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Warehouse {
    private static final Logger LOGGER = LogManager.getLogger(Warehouse.class);
    private static final Warehouse INSTANCE = new Warehouse();
    private static final int WAREHOUSE_CAPACITY = 40;
    private final List<CargoContainer> containers = new ArrayList<>();

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public Optional<CargoContainer> getContainer() {
        Optional<CargoContainer> optional = Optional.empty();
        if (!containers.isEmpty()) {
            optional = Optional.of(containers.remove(0));
        }
        return optional;
    }

    public void addContainer(CargoContainer container) {
        if (containers.size() < WAREHOUSE_CAPACITY) {
            containers.add(container);
        } else {
            LOGGER.warn("Unable to add cargo container, warehouse is full!");
        }
    }

    public int size() {
        return containers.size();
    }
}
