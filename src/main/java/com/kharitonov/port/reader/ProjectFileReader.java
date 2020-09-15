package com.kharitonov.port.reader;

import com.kharitonov.port.exception.ProjectFileReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProjectFileReader {
    private static final Logger LOGGER =
            LogManager.getLogger(ProjectFileReader.class);

    public List<String> read(String fileName)
            throws ProjectFileReaderException {
        List<String> stringList;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            String message;
            stringList = stream.collect(Collectors.toList());
            message = String.format("File %s was successfully read!", fileName);
            LOGGER.info(message);
        } catch (IOException e) {
            throw new ProjectFileReaderException("Error while reading file!", e);
        }
        return stringList;
    }
}
