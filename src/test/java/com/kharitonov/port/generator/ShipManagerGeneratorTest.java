package com.kharitonov.port.generator;

import com.kharitonov.port.entity.Ship;
import com.kharitonov.port.exception.ProjectFileReaderException;
import com.kharitonov.port.parser.ShipParser;
import com.kharitonov.port.reader.ProjectFileReader;
import org.testng.annotations.Test;

import java.util.List;

public class ShipManagerGeneratorTest {
    private final DockRequestGenerator generator = new DockRequestGenerator();

    @Test
    public void testGenerateRequests() throws ProjectFileReaderException {
        ProjectFileReader reader = new ProjectFileReader();
        List<String> data = reader.read("input\\ships.txt");
        ShipParser parser = new ShipParser();
        List<Ship> ships = parser.parse(data);
        generator.generateRequests(ships);
    }
}