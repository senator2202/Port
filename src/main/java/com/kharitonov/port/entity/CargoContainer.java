package com.kharitonov.port.entity;

public class CargoContainer {
    private int containerId;
    private double weight;

    public CargoContainer(int containerId, double weight) {
        this.containerId = containerId;
        this.weight = weight;
    }

    public int getContainerId() {
        return containerId;
    }

    public double getWeight() {
        return weight;
    }
}
