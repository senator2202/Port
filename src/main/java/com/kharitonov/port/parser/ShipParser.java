package com.kharitonov.port.parser;

import com.kharitonov.port.entity.CargoContainer;
import com.kharitonov.port.entity.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShipParser {
    private static final String REGEX_SHIP =
            "(?<shipId>\\d+)\\s(?<shipCapacity>\\d+):\\s(?<containers>.+)";
    private static final String REGEX_CONTAINER =
            "(?<containerId>\\d+)\\s(?<containerWeight>\\d+.\\d+);?";

    public List<Ship> parse(List<String> lines) {
        return lines.stream().map(this::parse).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public Ship parse(String line) {
        Pattern pattern = Pattern.compile(REGEX_SHIP);
        Matcher matcher = pattern.matcher(line);
        Ship ship = null;
        if (matcher.find()) {
            String stringShipId = matcher.group(RegexGroup.SHIP_ID);
            int shipId = Integer.parseInt(stringShipId);
            String stringShipCapacity = matcher.group(RegexGroup.SHIP_CAPACITY);
            int shipCapacity = Integer.parseInt(stringShipCapacity);
            String stringContainers = matcher.group(RegexGroup.CONTAINERS);
            List<CargoContainer> containers = parseContainers(stringContainers);
            ship = new Ship(shipId, shipCapacity, containers);
        }
        return ship;
    }

    private List<CargoContainer> parseContainers(String data) {
        List<CargoContainer> containers = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX_CONTAINER);
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            String stringContainerId = matcher.group(RegexGroup.CONTAINER_ID);
            int containerId = Integer.parseInt(stringContainerId);
            String stringContainerWeight = matcher.group(RegexGroup.CONTAINER_WEIGHT);
            double containerWeight = Double.parseDouble(stringContainerWeight);
            CargoContainer container = new CargoContainer(containerId, containerWeight);
            containers.add(container);
        }
        return containers;
    }
}
