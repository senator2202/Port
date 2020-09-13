package com.kharitonov.port.generator;

import org.testng.annotations.Test;

public class DockRequestGeneratorTest {
    private final DockRequestGenerator generator = new DockRequestGenerator();

    @Test
    public void testGenerateRequests() {
        generator.generateRequests();
    }
}