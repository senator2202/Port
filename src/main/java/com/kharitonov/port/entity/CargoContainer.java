package com.kharitonov.port.entity;

public class CargoContainer {
    private final int containerId;
    private final double weight;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CargoContainer that = (CargoContainer) o;

        if (containerId != that.containerId) return false;
        return Double.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = containerId;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
