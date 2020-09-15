package com.kharitonov.port.parser;

import com.kharitonov.port.data_provider.StaticDataProvider;
import com.kharitonov.port.entity.Ship;
import org.testng.annotations.Test;

import java.util.List;

public class ShipParserTest {
    private final ShipParser parser = new ShipParser();

    @Test
    public void testParseShip() {
        String source = StaticDataProvider.SHIP_LINE;
        Ship ship = parser.parse(source);
    }

    @Test
    public void testParseShipList() {
        List<String> source = StaticDataProvider.SHIP_LINE_LIST;
        List<Ship> ships = parser.parse(source);
    }

}