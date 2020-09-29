package com.kharitonov.port.main;

import com.kharitonov.port.entity.Ship;
import com.kharitonov.port.exception.ProjectFileReaderException;
import com.kharitonov.port.generator.DockRequestGenerator;
import com.kharitonov.port.parser.ShipParser;
import com.kharitonov.port.reader.ProjectFileReader;

import java.util.List;

public class PortDemonstration {
    private static final String FILE_PATH = "input\\ships.txt";

    public static void main(String[] args) throws ProjectFileReaderException {
        ProjectFileReader reader = new ProjectFileReader();
        List<String> data = reader.read(FILE_PATH);
        ShipParser parser = new ShipParser();
        List<Ship> ships = parser.parse(data);
        DockRequestGenerator generator = new DockRequestGenerator();
        generator.generateRequests(ships);
    }
}
